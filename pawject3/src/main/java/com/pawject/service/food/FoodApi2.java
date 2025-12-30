package com.pawject.service.food;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pawject.dto.food.SearchPetfoodDto;

public class FoodApi2 {	
	@Value("${openai.api.key.hj}")
	private String apikey;

	private static final String API_URL="https://api.openai.com/v1/chat/completions";
	private final ObjectMapper objectMapper = new ObjectMapper();
	RestTemplate  restTemplate = new RestTemplate();


	@Autowired SearchPetfoodService service;
	
	public String helpfoodrecommend(String userMessage, int foodid) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		headers.set("Authorization", "Bearer " + apikey); 
		
		List<SearchPetfoodDto> food = service.aiRecommend();
		
		
		List<Map<String, String>> messages = new ArrayList<>();
	    messages.add(Map.of(
			        "role", "system",
			        "content",
			        "너는 반려동물 사료 필터링 추천 챗봇이다 " +
			        "반려동물의 상태에 맞는 필터링을 추천해준다" +
			        "DB에 존재하지 않는 사료는 참조하지 않는다"
	    	    ));
	    messages.add(Map.of("role", "user", "content", userMessage));

	    return null;
	    

	
	}

}
