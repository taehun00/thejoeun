package com.pawject.service.review;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ReviewApi {
	
	@Value("${openai.api.key.hj}")
	private String apikey;

	private static final String API_URL="https://api.openai.com/v1/chat/completions";
	private final ObjectMapper objectMapper = new ObjectMapper();

	RestTemplate  restTemplate = new RestTemplate();
	//리뷰 내용 다듬기
	public String helpReviewWriting(String title, String reviewcomment) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		headers.set("Authorization", "Bearer " + apikey); 
		
		Map<String, Object> body = new HashMap<>();
		body.put("model", "gpt-4.1");
		body.put("messages", List.of(
			    Map.of(
			        "role", "system",
			        "content",
			        "너는 사용자 리뷰를 정중하고 중립적인 표현으로 다듬는 도우미다. " +
			        "의미는 유지하고 비속어, 과한 표현, 감정적인 표현만 순화한다. " +
			        "주제는 사료 리뷰이며 섭취 대상은 강아지, 고양이다. " +
			        "강아지, 고양이 여부는 화면의 종과 사료 이름에서 확인이 가능하다. " +
			        "출력은 반드시 아래 형식을 따른다:\n" +
			        "제목: <다듬은 제목>\n" +
			        "내용: <다듬은 내용>"
			    ),
			    Map.of(
			        "role", "user",
			        "content",
			        "제목: " + title + "\n" +
			        "내용: " + reviewcomment
			    )
			));

		HttpEntity<Map<String, Object>>  requestEntity = new HttpEntity<>(body, headers);

	    //org.springframework.http.ResponseEntity                          주소,      요청,          응답형태
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(API_URL, requestEntity, String.class);
		String responseBody = responseEntity.getBody();
		
		//com.fasterxml.jackson.databind.JsonNode
		try {
			JsonNode root =   objectMapper.readTree(responseBody);  
			return  root.path("choices").get(0).path("message").path("content").asText();
		} catch (Exception e) { throw new RuntimeException("OpenAI 응답 파싱 오류" , e); }
		 
	}
}

