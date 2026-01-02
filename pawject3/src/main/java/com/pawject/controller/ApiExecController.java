package com.pawject.controller;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pawject.dto.Apiweather.ApiKmaWeatherExec;


@Controller
@RequestMapping("/execapi")
//@SpringBootApplication
public class ApiExecController {
	///////////////////////////////ChatGpt
//	@Autowired ApiChatGpt apiChatGpt; 
//	
//	@GetMapping("/openai")
//	public String openai_get() { return "external/openai"; }
//	
//	@PostMapping(value="/openai", produces = "application/json;charset=UTF-8")
//	@ResponseBody
//	public String openai(@RequestBody String content) {
//		return apiChatGpt.getAIResponse(content);
//	}
	/////////////////////////////// Weather
	@Autowired ApiKmaWeatherExec apiKmaWeatherExec; 
	
	@GetMapping("/saveweather")
	public String kma_get() { return "weather/apiweather"; }
	
	@GetMapping(value="/saveweatherapi", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String kma() throws URISyntaxException {
		return apiKmaWeatherExec.getWeatherResponse();
	}
///////////////////////////////Maps
	@GetMapping("/walkingcourse")
	public String maps(Model model) {
	    model.addAttribute("location", "송도센트럴파크");
	    model.addAttribute("lat", 37.3925);
	    model.addAttribute("lng", 126.6394);

	    return "walking/walkingnavermaps"; 
	    // 파일 위치가 src/main/resources/templates/walking/walkingnavermaps.html 이어야 합니다
	}

}
