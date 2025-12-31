package com.pawject.service.PetChaApi;

import java.io.File;
import java.io.IOException;

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

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public String call(MultipartFile file, String prompt) {
        try {
            if (file != null && !file.isEmpty()) {
                return callImageEdits(file, prompt);
            } else {
                return callImageGenerations(prompt);
            }
        } catch (Exception e) {
            throw new RuntimeException("OpenAI 이미지 API 호출 실패", e);
        }
    }

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
            .addHeader("Authorization", "Bearer " + apiKey)
            .post(requestBody)
            .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("OpenAI Edits API 실패: " + response);
            JsonNode root = mapper.readTree(response.body().string());
            return root.path("data").get(0).path("url").asText();
        }
    }

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
            .addHeader("Authorization", "Bearer " + apiKey)
            .post(body)
            .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("OpenAI Generations API 실패: " + response);
            JsonNode root = mapper.readTree(response.body().string());
            return root.path("data").get(0).path("url").asText();
        }
    }

    private File convert(MultipartFile file) throws IOException {
        File convFile = File.createTempFile("upload", ".png");
        file.transferTo(convFile);
        return convFile;
    }
}
