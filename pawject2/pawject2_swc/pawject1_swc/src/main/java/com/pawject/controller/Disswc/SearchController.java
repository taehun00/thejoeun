package com.pawject.controller.Disswc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pawject.dto.Disswc.DisswcDto;
import com.pawject.service.Disswc.DiseaseService;

@RestController         //@Controller
public class SearchController {
	@Autowired DiseaseService service;
	@RequestMapping("/searchTest")
//	@ResponseBody
	public String hi() {
		return "hi";
	}
	
	
	// http://localhost:8282/spring005_board/selectSearch?search=t 
		@RequestMapping("/selectSearch") 
		public List<DisswcDto> selectSearch( @RequestParam("search") String search ) {  
			return service.selectSearch(search);  //값줄께
		}  
	
	
}
