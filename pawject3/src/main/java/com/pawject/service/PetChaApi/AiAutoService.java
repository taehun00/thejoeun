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

    @Value("${openai.api.key.swc}")
    private String apiKey;

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public AiAutoResponse autoFill(String disname) throws Exception {

        String prompt =
            "너는 수의학 정보 자동완성 AI다.\n" +
            "아래 JSON 형식으로만 응답해라.\n" +
            "설명 문장이나 다른 텍스트는 절대 포함하지 마라.\n\n" +
            "{\n" +
            "  \"disex\": \"질환 설명\",\n" +
            "  \"kindpet\": \"주로 발생하는 반려동물 종\",\n" +
            "  \"infval\": \"관련 수치나 특징\",\n" +
            "  \"mannote\": \"주의사항\"\n" +
            "}\n\n" +
            "질환명: " + disname;

        // 요청 JSON 생성
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

        // OpenAI 응답 파싱
        JsonNode textNode = mapper.readTree(body)
            .path("output").get(0)
            .path("content").get(0)
            .path("text");

        return mapper.readValue(textNode.asText(), AiAutoResponse.class);
    }
}




