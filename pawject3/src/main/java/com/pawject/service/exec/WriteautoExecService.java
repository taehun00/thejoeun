package com.pawject.service.exec;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WriteautoExecService {
    @Value("${openai.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String generateText(String prompt) {

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = new HashMap<>();
        body.put("model", "gpt-4.1-mini");
        body.put("input", prompt);

        HttpEntity<Map<String, Object>> request =
                new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(
                "https://api.openai.com/v1/responses",
                request,
                Map.class
        );

        return extractText(response.getBody());
    }

    private String extractText(Map body) {
        return (String)((Map)((List)body.get("output")).get(0))
                .get("content");
    }
}
