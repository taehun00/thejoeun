package com.pawject.service.PetChaApi;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import okhttp3.*;

@Service
public class PetChaApiService {

    @Value("${openai.api.key}")
    private String petChaApiKey;

    // 기본은 이미지 생성 API
    private static final String GENERATE_URL = "https://api.openai.com/v1/images/generations";
    // 원본 이미지 기반 편집 API
    private static final String EDIT_URL = "https://api.openai.com/v1/images/edits";

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * 반려동물 캐릭터 생성
     * - 원본 이미지가 있으면 edits API 호출
     * - 없으면 generations API 호출
     */
    public String createCharacter(MultipartFile file, String kindpet) {
        try {
            if (file != null && !file.isEmpty()) {
                return callImageEdits(file, "귀여운 반려동물 캐릭터로 변환");
            } else if (kindpet != null && !kindpet.isBlank()) {
                return callImageGenerations("귀여운 " + kindpet + " 캐릭터 일러스트");
            } else {
                throw new IllegalArgumentException("원본 이미지 또는 반려동물 종을 입력해야 합니다.");
            }
        } catch (Exception e) {
            throw new RuntimeException("캐릭터 생성 실패", e);
        }
    }

    /** OpenAI Images Edits API 호출 */
    private String callImageEdits(MultipartFile file, String prompt) throws IOException {
        RequestBody requestBody = new MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("image", file.getOriginalFilename(),
                RequestBody.create(convert(file), MediaType.parse("image/png")))
            .addFormDataPart("prompt", prompt)
            .addFormDataPart("size", "512x512")
            .build();

        Request request = new Request.Builder()
            .url(EDIT_URL)
            .addHeader("Authorization", "Bearer " + petChaApiKey)
            .post(requestBody)
            .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("OpenAI API 호출 실패: " + response);
            }
            JsonNode root = mapper.readTree(response.body().string());
            return root.path("data").get(0).path("url").asText();
        }
    }

    /** OpenAI Images Generations API 호출 */
    private String callImageGenerations(String prompt) throws IOException {
        ObjectNode json = mapper.createObjectNode();
        json.put("model", "gpt-image-1");
        json.put("prompt", prompt);
        json.put("size", "512x512");

        RequestBody body = RequestBody.create(
            mapper.writeValueAsString(json),
            MediaType.parse("application/json")
        );

        Request request = new Request.Builder()
            .url(GENERATE_URL)
            .addHeader("Authorization", "Bearer " + petChaApiKey)
            .post(body)
            .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("OpenAI API 호출 실패: " + response);
            }
            JsonNode root = mapper.readTree(response.body().string());
            return root.path("data").get(0).path("url").asText();
        }
    }

    /** MultipartFile → File 변환 */
    private File convert(MultipartFile file) throws IOException {
        File convFile = File.createTempFile("upload", ".png");
        file.transferTo(convFile);
        return convFile;
    }
}