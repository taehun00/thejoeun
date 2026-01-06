package com.pawject.service.PetChaApi;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pawject.dao.PetCharacter.PetCharacterDao;
import com.pawject.dto.PetCharacter.PetCharacter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PetChaApiService {

    private final PetCharacterDao petCharacterDao;
    private final OpenAiImageClient openAiImageClient;

    // ✅ 기본값 추가: 설정 없어도 앱이 죽지 않게
    @Value("${app.upload.dir:uploads}")
    private String uploadDir;

    /**
     * ✅ (현재 방식) PNG bytes 즉시 반환
     * - DB 저장 X
     * - 파일 저장 X
     * - 컨트롤러에서 바로 image/png로 응답 가능
     */
    public byte[] generateChricature(MultipartFile file, String prompt) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("file is required");
        }
        if (prompt == null || prompt.isBlank()) {
            throw new IllegalArgumentException("prompt is required");
        }

        // OpenAI 호출 → PNG bytes 리턴 (전제)
        return openAiImageClient.callBytes(file, prompt);
    }

    // =======================
    // 아래는 URL 저장 방식용(현재 흐름에선 사용 안 함)
    // 필요 없으면 나중에 정리 가능
    // =======================

    public String createCharacter(Integer userid, MultipartFile file, String kindpet) {
        String prompt = buildPrompt(file, kindpet);

        byte[] imageBytes = openAiImageClient.callBytes(file, prompt);
        String savedUrl = savePngAndGetUrl(userid, imageBytes);

        PetCharacter character = new PetCharacter();
        character.setUserid(userid);
        character.setKindpet(kindpet);
        character.setPrompt(prompt);
        character.setImageurl(savedUrl);

        petCharacterDao.insertCharacter(character);

        return savedUrl;
    }

    private String buildPrompt(MultipartFile file, String kindpet) {
        String species = (kindpet == null || kindpet.isBlank()) ? "반려동물" : kindpet.trim();

        if (file != null && !file.isEmpty()) {
            return "원본 사진의 구도와 특징(얼굴/털무늬/귀 모양)은 유지하고, "
                 + "아주 귀여운 치비(chibi) 캐릭터 일러스트로 변환해줘. "
                 + "파스텔 톤, 깨끗한 라인, 배경은 심플한 흰색 또는 아주 연한 그라데이션. "
                 + "과장된 눈, 따뜻한 표정, 스티커 느낌.";
        }

        return "아주 귀여운 " + species + " 캐릭터 일러스트. "
             + "치비(chibi), 파스텔, 깨끗한 라인, 스티커 스타일, 흰색 배경, 고해상도.";
    }

    private String savePngAndGetUrl(Integer userid, byte[] pngBytes) {
        try {
            Path baseDir = Paths.get(uploadDir, "petcharacter", String.valueOf(userid));
            Files.createDirectories(baseDir);

            String filename = UUID.randomUUID().toString().replace("-", "") + ".png";
            Path filePath = baseDir.resolve(filename);

            Files.write(filePath, pngBytes);

            return "/uploads/petcharacter/" + userid + "/" + filename;
        } catch (Exception e) {
            throw new RuntimeException("이미지 저장 실패", e);
        }
    }

	public byte[] generateCaricature(MultipartFile file, String prompt) {
		// TODO Auto-generated method stub
		return null;
	}
}
