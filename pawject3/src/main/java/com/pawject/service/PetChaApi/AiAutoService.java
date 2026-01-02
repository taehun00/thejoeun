package com.pawject.service.PetChaApi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.pawject.dto.AiAuto.AiAutoResponse;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class AiAutoService {

    @Value("${openai.api.key}")
    private String apiKey;

    
    private static final String API_URL="https://api.openai.com/v1/responses";
    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public AiAutoResponse autoFill(String disname) {

        try {
            String prompt =
                "너는 수의학 정보 자동완성 AI다.\n" +
                "아래 JSON 형식으로만 응답해라.\n" +
                "다른 텍스트는 절대 포함하지 마라.\n\n" +
                "{\n" +
                "  \"disex\": \"질환 설명\",\n" +
                "  \"kindpet\": \"주로 발생하는 반려동물 종\",\n" +
                "  \"infval\": \"관련 수치나 특징\",\n" +
                "  \"mannote\": \"주의사항\"\n" +
                "}\n\n" +
                "질환명: " + disname;

            ObjectNode root = mapper.createObjectNode();
            root.put("model", "gpt-4.1-mini");

            ArrayNode inputArr = root.putArray("input");
            ObjectNode inputObj = inputArr.addObject();
            inputObj.put("role", "user");
            inputObj.put("content", prompt);

            Request request = new Request.Builder()
                .url("https://api.openai.com/v1/responses")
                .post(RequestBody.create(
                    mapper.writeValueAsString(root),
                    MediaType.parse("application/json")
                ))
                .addHeader("Authorization", "Bearer " + apiKey)
                .build();

            Response response = client.newCall(request).execute();
            String body = response.body().string();

            JsonNode rootNode = mapper.readTree(body);

            // 🔒 null-safe 파싱
            JsonNode textNode = rootNode
                .path("output")
                .path(0)
                .path("content")
                .path(0)
                .path("text");

            if (textNode.isMissingNode() || textNode.isNull()) {
                throw new RuntimeException("AI 응답 파싱 실패");
            }

            return mapper.readValue(textNode.asText(), AiAutoResponse.class);

        } catch (Exception e) {
            throw new RuntimeException("AI 자동완성 실패", e);
        }
    }
}




