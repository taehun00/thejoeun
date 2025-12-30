package com.pawject.service.food;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pawject.dto.food.SearchPetfoodDto;
@Service
public class FoodApi {	
	@Value("${openai.api.key.hj}")
	private String apikey;
	@Autowired SearchPetfoodService service;
	
	private static final String API_URL="https://api.openai.com/v1/chat/completions";
	private final ObjectMapper objectMapper = new ObjectMapper();
	RestTemplate  restTemplate = new RestTemplate();
	
	//AI 설명
	public String getAiExplanation(String userMessage) {
	    // 프롬프트 생성 // GPT 호출	    // JSON 파싱  // content String 반환
		
	        // 1. 프롬프트 만들기
			List<SearchPetfoodDto> filterlist = service.aiRecommend();
	    	StringBuilder prompt = new StringBuilder();
	    	
	    	prompt.append("너는 사용자의 요구에 따라 반려동물 사료 검색 필터를 자동으로 조정하는 시스템이다.\n");
	    	prompt.append("사용자 입력을 분석해 필터를 내부적으로 변경한다.\n");
	    	prompt.append("필터 목록을 나열하거나 선택을 요구하지 않는다.\n");
	    	prompt.append("특정 사료를 직접 추천하면 안 된다.\n");
	    	prompt.append("사료 이름 기반 필터는 직접 추천에 해당하므로 자동 추천에서 제외한다.\n");
	    	prompt.append("특정 사료나 재료를 찾는 경우, 사용자가 직접 입력하여 검색하도록 안내한다.\n\n");

	    	prompt.append("응답은 다음 조건을 따른다.\n");
	    	prompt.append("- 필터를 자동으로 변경했음을 알린다\n");
	    	prompt.append("- 필터 변경 이유를 자연어로 설명한다\n");
	    	prompt.append("- 결과는 간결하고 친절하게 작성한다\n");
	    	prompt.append("- 불필요한 설명, 인사말, 추천 문장은 하지 않는다\n\n");

	    	prompt.append("아래 정보는 필터 선택 참고용 데이터다.\n\n");
	    	prompt.append("[사료 데이터 요약]\n");

	    	//문자열만 - 아이디 해석x
	    	for (SearchPetfoodDto filter : filterlist) {
	    	    prompt.append("- ")
	    	          .append(filter.getPettypename()).append(" / ")
	     	          .append(filter.getBrandname()).append(" / ")
	     	          .append(filter.getCountry()).append(" / ")
	     	          .append(filter.getBrandtype()).append(" / ")
	     	          .append(filter.getOrigin()).append(" / ")
	    	          .append(filter.getCategory()).append(" / ")
	    	          .append(filter.getPetagegroup()).append(" / ")
	    	          .append(filter.getIsgrainfree()).append(" / ")    	          
	    	          .append(filter.getFoodtype()).append(" / ")    	          
	    	          .append(filter.getRangelabel())

	    	          .append("\n");
	    	}

	    	String finalPrompt = prompt.toString();
	    	
	    	
	        // 2. GPT 호출
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", "application/json");
			headers.set("Authorization", "Bearer " + apikey); 
			
			Map<String, Object> body = new HashMap<>();
			body.put("model", "gpt-4"); 
			body.put("messages", List.of(
				    Map.of(
				        "role", "system",
				        "content", finalPrompt + "\n이 정보를 기반으로 검색 필터를 설정해줘."
				    ),
				    Map.of("role", "user", 
				    		"content", userMessage)
				));
					
			
			
	    	// 3. JSON 파싱
			HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
			String response = restTemplate.postForObject(API_URL, request, String.class);

			//문자열 -> JSON 구조화
		
			
			try {

				JsonNode root = objectMapper.readTree(response);
			    
				JsonNode choices = root.path("choices");
			    
				if (!choices.isArray() || choices.size() == 0) {
			        return "검색 필터를 조정했습니다.";
			    }

			    JsonNode message = choices.get(0).path("message");
			    String content = message.path("content").asText("");

			    if (content.isBlank()) {
			        return "검색 조건을 반영하여 필터를 조정했습니다.";
			    }

			    return content;

			} catch (Exception e) {
			    e.printStackTrace();
			    return "검색 조건을 반영하여 필터를 조정했습니다.";
			}
			
		
	}

	//필터 + 검색 담당
	public List<SearchPetfoodDto> searchByUserMessage(String userMessage){
    	
    	return null;//임시
    	
	}

	
}


/*
{
  "choices": [
    {
      "index": 0,
      "message": {
        "role": "assistant",
        "content": "필터를 조정했습니다."
      }
    }
  ]
}
 	*/
