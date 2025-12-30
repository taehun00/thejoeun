package com.pawject.service.PetChaApi;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pawject.dto.AiAuto.AiAutoResponse;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class OpenAiService {
	
	private OkHttpClient createClient() {
	    return new OkHttpClient.Builder()
	        .connectTimeout(10, TimeUnit.SECONDS)
	        .readTimeout(30, TimeUnit.SECONDS)
	        .build();
	}


    @Value("${openai.api.key.swc}")
    private String apiKey;

    private final ObjectMapper mapper = new ObjectMapper();

    public AiAutoResponse generateDiseaseInfo(String disname) {

        String prompt = String.format(
            "너는 수의학 전문가야.\n" +
            "질환명: %s\n\n" +
            "아래 형식의 JSON으로만 응답해.\n" +
            "{\n" +
            "  \"disex\": \"질환 설명\",\n" +
            "  \"kindpet\": \"주로 발생하는 반려동물\",\n" +
            "  \"infval\": \"관련 수치 또는 특징\",\n" +
            "  \"mannote\": \"관리 및 주의사항\"\n" +
            "}",
            disname
        );

        String content = callOpenAi(prompt);

        // GPT가 ```json ``` 붙이는 경우 제거
        content = content.replace("```json", "")
                         .replace("```", "")
                         .trim();

        try {
            return mapper.readValue(content, AiAutoResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("AI 응답 파싱 실패: " + content, e);
        }
    }

    private String callOpenAi(String prompt) {
    	
    	OkHttpClient client = createClient();


		/* OkHttpClient client = new OkHttpClient(); */

        String body = String.format(
            "{\n" +
            "  \"model\": \"gpt-4o-mini\",\n" +
            "  \"messages\": [\n" +
            "    {\"role\": \"system\", \"content\": \"반려동물 질환 전문가\"},\n" +
            "    {\"role\": \"user\", \"content\": \"%s\"}\n" +
            "  ]\n" +
            "}",
            escapeJson(prompt)
        );

        Request request = new Request.Builder()
            .url("https://api.openai.com/v1/chat/completions")
            .addHeader("Authorization", "Bearer " + apiKey)
            .addHeader("Content-Type", "application/json")
            .post(RequestBody.create(
                body,
                MediaType.parse("application/json")
            ))
            .build();

        try (Response response = client.newCall(request).execute()) {

            if (!response.isSuccessful()) {
                throw new RuntimeException("OpenAI API 실패: " + response);
            }

            String json = response.body().string();

            JsonNode root = mapper.readTree(json);
            return root.get("choices")
                       .get(0)
                       .get("message")
                       .get("content")
                       .asText();

        } catch (Exception e) {
            throw new RuntimeException("OpenAI 호출 실패", e);
        }
    }

    /**
     * JSON 문자열 안전 처리 (개행/따옴표)
     */
    private String escapeJson(String text) {
        return text
            .replace("\\", "\\\\")
            .replace("\"", "\\\"")
            .replace("\n", "\\n");
    }
}


