package com.pawject.dto.Apiweather;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiKmaWeatherExec {
	
	
	@Value("${kma.api}")
	private String apiKey;
	
	public String getWeatherResponse() throws URISyntaxException {
//		String date = new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());
//		String url ="http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"
//				+ "?serviceKey="+apiKey+"&numOfRows=10&pageNo=1"
//				+ "&base_date="+date+"&base_time=0800&nx=55&ny=124";
				// base_time=1200
			    // &nx=55&ny=124  인천좌표
				// 단기예보는 하루 8회 발표 (02, 05, 08, 11, 14, 17, 20, 23시)
		String date = new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());
		String encodedKey = URLEncoder.encode(apiKey, StandardCharsets.UTF_8);
		String url = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"
		    + "?serviceKey=" + encodedKey
		    + "&numOfRows=10&pageNo=1"
		    + "&base_date="+date
		    +"&base_time=0500&nx=55&ny=124";
		URI uri = new URI(url);  //## 인코딩 문제 해결
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
		
		return response.getBody();
	}
}

/*
https://www.data.go.kr/data/15084084/openapi.do#tab_layer_detail_function
 
 
TEST - xml
http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst
?serviceKey=79d67e9b9f14131eeb45afaf8f5b82115d9c35db643ec65af915335b67f7b9d2
&numOfRows=10&pageNo=1
&base_date=20260102&base_time=0500&nx=55&ny=124

양식
http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst
?serviceKey=인증키&numOfRows=10&pageNo=1
&base_date=오늘날짜&base_time=0500&nx=55&ny=124



79d67e9b9f14131eeb45afaf8f5b82115d9c35db643ec65af915335b67f7b9d2


*/
