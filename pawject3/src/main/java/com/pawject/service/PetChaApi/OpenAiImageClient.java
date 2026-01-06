package com.pawject.service.PetChaApi;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import okhttp3.*;

@Component
public class OpenAiImageClient {

    @Value("${openai.api.key}")
    private String apiKey;

    private static final String GENERATE_URL = "https://api.openai.com/v1/images/generations";
    private static final String EDIT_URL = "https://api.openai.com/v1/images/edits";

//    private final OkHttpClient client = new OkHttpClient();
    private final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(java.time.Duration.ofSeconds(20))
            .writeTimeout(java.time.Duration.ofSeconds(60))
            .readTimeout(java.time.Duration.ofSeconds(180))
            .callTimeout(java.time.Duration.ofSeconds(240))
            .build();

    private final ObjectMapper mapper = new ObjectMapper();

    /** 결과 이미지를 bytes로 반환 */
    public byte[] callBytes(MultipartFile file, String prompt) {
        try {
            if (file != null && !file.isEmpty()) {
                return callImageEdits(file, prompt);
            }
            return callImageGenerations(prompt);
        } catch (Exception e) {
            throw new RuntimeException("OpenAI 이미지 API 호출 실패", e);
        }
    }

    private byte[] callImageEdits(MultipartFile file, String prompt) throws IOException {
        // gpt-image-1 edits: multipart/form-data
        RequestBody imageBody = RequestBody.create(file.getBytes(),
                MediaType.parse(file.getContentType() != null ? file.getContentType() : "application/octet-stream"));

        RequestBody requestBody = new MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("model", "gpt-image-1")
            .addFormDataPart("image", file.getOriginalFilename() != null ? file.getOriginalFilename() : "input.png", imageBody)
            .addFormDataPart("prompt", prompt)
            .addFormDataPart("size", "1024x1024")     // ✅ gpt-image 규격
            .addFormDataPart("output_format", "png")  // ✅ 결과 포맷 고정
            .build();

        Request request = new Request.Builder()
            .url(EDIT_URL)
            .addHeader("Authorization", "Bearer " + apiKey)
            .post(requestBody)
            .build();

        try (Response response = client.newCall(request).execute()) {
            String body = response.body() != null ? response.body().string() : "";
            if (!response.isSuccessful()) {
                throw new IOException("OpenAI Edits API 실패: " + response.code() + " / " + body);
            }
            JsonNode root = mapper.readTree(body);

            // ✅ GPT 이미지 모델은 base64(b64_json) 반환
            String b64 = root.path("data").get(0).path("b64_json").asText(null);
            if (b64 == null || b64.isBlank()) throw new IOException("OpenAI 응답에 b64_json이 없습니다: " + body);

            return Base64.getDecoder().decode(b64);
        }
    }

    private byte[] callImageGenerations(String prompt) throws IOException {
        ObjectNode json = mapper.createObjectNode();
        json.put("model", "gpt-image-1");
        json.put("prompt", prompt);
        json.put("size", "1024x1024");      // ✅ gpt-image 규격
        json.put("output_format", "png");   // ✅ 결과 포맷 고정

        RequestBody body = RequestBody.create(
            mapper.writeValueAsString(json),
            MediaType.parse("application/json")
        );

        Request request = new Request.Builder()
            .url(GENERATE_URL)
            .addHeader("Authorization", "Bearer " + apiKey)
            .post(body)
            .build();

        try (Response response = client.newCall(request).execute()) {
            String resBody = response.body() != null ? response.body().string() : "";
            if (!response.isSuccessful()) {
                throw new IOException("OpenAI Generations API 실패: " + response.code() + " / " + resBody);
            }
            JsonNode root = mapper.readTree(resBody);

            // ✅ GPT 이미지 모델은 base64(b64_json) 반환
            String b64 = root.path("data").get(0).path("b64_json").asText(null);
            if (b64 == null || b64.isBlank()) throw new IOException("OpenAI 응답에 b64_json이 없습니다: " + resBody);

            return Base64.getDecoder().decode(b64);
        }
    }
}
