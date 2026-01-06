package com.pawject.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.pawject.service.PetChaApi.PetChaApiService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AiPetChaController {

    private final PetChaApiService petChaApiService;

    /**
     * 저장된 이미지(또는 캐시 이미지) 제공용 디렉토리
     * - 기본값: uploads/petcha (프로젝트 실행 경로 기준 상대경로)
     * - 운영에서는 application.properties로 절대경로 지정 권장
     */
    @Value("${petcha.image-dir:uploads/petcha}")
    private String imageDir;

    /**
     * 화면 이동(GET)
     */
    @GetMapping("/api/pet/character")
    public String page() {
        // thymeleaf: src/main/resources/templates/board/Aicharacter.html
        return "board/Aicharacter";
    }

    /**
     * AI 캐릭터 생성(POST)
     * - 서비스에서 "항상 PNG byte[]" 반환한다는 전제
     * - 성공: image/png 바이너리
     * - 실패: 400/500 + 텍스트 메시지
     */
    @PostMapping(value = "/api/pet/character", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public ResponseEntity<?> create(
            @RequestParam("userid") Integer userid,
            @RequestParam("file") MultipartFile file,
            @RequestParam("kindpet") String kindpet
    ) {
        try {
            if (userid == null) return ResponseEntity.badRequest().body("userid is required");
            if (file == null || file.isEmpty()) return ResponseEntity.badRequest().body("file is required");
            if (kindpet == null || kindpet.isBlank()) return ResponseEntity.badRequest().body("kindpet is required");

            String prompt = kindpet + "를 귀여운 캐릭터 스타일로 변환. 큰 머리, 밝은 색상, 심플한 배경";
            byte[] result = petChaApiService.generateChricature(file, prompt);

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(result);

        } catch (IllegalArgumentException e) {
            // 파라미터/요청 문제는 400이 맞음
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (Exception e) {
            // OpenAI 호출 실패 등 서버 에러는 500
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("AI generation failed: " + e.getMessage());
        }
    }
    /**
     * 서버에 저장된 이미지 제공
     * - 경로 탈출(../) 방지 포함
     */
    @GetMapping("/api/pet/character/image/{filename}")
    public ResponseEntity<?> serveImage(@PathVariable("filename") String filename) {
        try {
            Path baseDir = Paths.get(imageDir).toAbsolutePath().normalize();
            Path filePath = baseDir.resolve(filename).normalize();

            // 디렉토리 트래버설 방지
            if (!filePath.startsWith(baseDir)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid filename");
            }

            if (!Files.exists(filePath)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
            }

            byte[] bytes = Files.readAllBytes(filePath);
            MediaType contentType = guessMediaType(filename);

            return ResponseEntity.ok()
                    .contentType(contentType)
                    .cacheControl(org.springframework.http.CacheControl.noCache())
                    .body(new ByteArrayResource(bytes));

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
    }

    /**
     * 외부 URL 이미지 다운로드 프록시(주의 기능)
     * - SSRF 방지: scheme 제한 + 내부망/로컬 주소 차단
     * - Redirect 차단: 우회 방지 위해 NEVER 권장
     */
    @GetMapping("/api/pet/character/download")
    public ResponseEntity<?> download(@RequestParam("url") String url) {
        try {
            URI uri = new URI(url);

            String scheme = uri.getScheme();
            if (scheme == null || !(scheme.equalsIgnoreCase("http") || scheme.equalsIgnoreCase("https"))) {
                return ResponseEntity.badRequest().body("Invalid scheme");
            }

            if (!isSafeHost(uri.getHost())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Blocked host");
            }

            HttpClient client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(10))
                    .followRedirects(HttpClient.Redirect.NEVER) // ✅ redirect로 내부망 우회 방지
                    .build();

            HttpRequest req = HttpRequest.newBuilder(uri)
                    .timeout(Duration.ofSeconds(30))
                    .GET()
                    .build();

            HttpResponse<byte[]> resp = client.send(req, HttpResponse.BodyHandlers.ofByteArray());

            if (resp.statusCode() >= 400) {
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Upstream error");
            }

            String ct = resp.headers().firstValue("content-type").orElse("application/octet-stream");
            MediaType mediaType = safeParseMediaType(ct);

            String ext = ".png";
            String lower = ct.toLowerCase();
            if (lower.contains("jpeg") || lower.contains("jpg")) ext = ".jpg";
            else if (lower.contains("webp")) ext = ".webp";
            else if (lower.contains("png")) ext = ".png";

            ContentDisposition cd = ContentDisposition.attachment()
                    .filename("pet_character" + ext)
                    .build();

            return ResponseEntity.ok()
                    .contentType(mediaType)
                    .header(HttpHeaders.CONTENT_DISPOSITION, cd.toString())
                    .body(resp.body());

        } catch (URISyntaxException e) {
            return ResponseEntity.badRequest().body("Invalid URL");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Download failed");
        }
    }

    // =========================
    // Helpers
    // =========================

    private MediaType guessMediaType(String filename) {
        String f = filename.toLowerCase();
        if (f.endsWith(".jpg") || f.endsWith(".jpeg")) return MediaType.IMAGE_JPEG;
        if (f.endsWith(".webp")) return MediaType.valueOf("image/webp");
        return MediaType.IMAGE_PNG;
    }

    /**
     * content-type이 이상하게 오면 parse에서 예외 날 수 있어서 방어
     */
    private MediaType safeParseMediaType(String ct) {
        try {
            return MediaType.parseMediaType(ct);
        } catch (Exception e) {
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

    /**
     * PNG signature(매직넘버) 검사
     * 89 50 4E 47 0D 0A 1A 0A
     */
    private boolean isPng(byte[] b) {
        return b != null && b.length >= 8
                && (b[0] & 0xFF) == 0x89
                && (b[1] & 0xFF) == 0x50
                && (b[2] & 0xFF) == 0x4E
                && (b[3] & 0xFF) == 0x47
                && (b[4] & 0xFF) == 0x0D
                && (b[5] & 0xFF) == 0x0A
                && (b[6] & 0xFF) == 0x1A
                && (b[7] & 0xFF) == 0x0A;
    }

    /**
     * SSRF 방지용 호스트 체크
     * - 로컬/사설/링크로컬/루프백 차단
     */
    private boolean isSafeHost(String host) throws UnknownHostException {
        if (host == null || host.isBlank()) return false;

        // IP literal(127.0.0.1 등)도 여기서 resolve됨
        InetAddress addr = InetAddress.getByName(host);

        if (addr.isAnyLocalAddress()
                || addr.isLoopbackAddress()
                || addr.isLinkLocalAddress()
                || addr.isSiteLocalAddress()) {
            return false;
        }

        if ("localhost".equalsIgnoreCase(host)) return false;

        return true;
    }
}
