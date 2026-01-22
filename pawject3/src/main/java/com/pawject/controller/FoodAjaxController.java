package com.pawject.controller;
	
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pawject.dto.food.FoodDto;
import com.pawject.dto.food.FoodDtoForList;
import com.pawject.dto.support.CSQuestionDto;
import com.pawject.service.food.FoodService;
import com.pawject.service.food.NaverOcrService;
import com.pawject.util.UtilPaging;

	
	@RestController
	@RequestMapping("/foodboard")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
	public class FoodAjaxController {
		@Autowired FoodService service;

		//페이징 적용 버전
		@RequestMapping("/foodselectForList")
		public Map<String, Object> foodselectForList(
     		@RequestParam(required=false) String condition,
		    @RequestParam(defaultValue="1") int pageNo){	
			Map<String, Object> result = new HashMap<>();
			
			int total=service.foodselectcnt();
			List<FoodDtoForList> list = service.foodselect10(pageNo, condition);
			
			
	        UtilPaging paging = new UtilPaging(total, pageNo);  

	        result.put("paging", paging);
	    	result.put("total", total);
	    	result.put("list", list);

			
		    return result;
		}
		
		//빠른 삭제
		@PostMapping("/foodquikdelete")
		public Map<String, Object> foodquikdelete(@RequestParam int foodid){
			Map<String, Object> result = new HashMap<>();
			FoodDto fdto = new FoodDto();
			fdto.setFoodid(foodid);
			result.put("result", service.fooddelete(foodid));
			return result;
		}
		
		
		//검색 기능+검색페이징
		@RequestMapping("/foodsearch")
		public Map<String, Object> foodsearch(
		        @RequestParam("keyword") String keyword,
		        @RequestParam("searchType") String searchType,
		        @RequestParam(required=false) String condition,
		        @RequestParam(value="pageNo", defaultValue="1") int pageNo) {

		    Map<String, Object> result = new HashMap<>();
		    int total = service.foodsearchcnt(keyword, searchType);
		    		
		    List<FoodDtoForList> list = service.foodsearch(keyword, searchType, condition, pageNo);
		    UtilPaging paging = new UtilPaging(total, pageNo);
		    
		      result.put("total", total);   
		      result.put("list", list);
		      result.put("paging", paging);
		      result.put("search", keyword);

		    return result;
		}
		
		
		//api

		@Autowired NaverOcrService nservice;
		@PostMapping("/naverocr")
		@ResponseBody  //빼먹지 말기
	    public String analyzeImage(@RequestParam("ocrfile") MultipartFile ocrfile) throws IOException {
	        File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + ocrfile.getOriginalFilename());
	        ocrfile.transferTo(convFile);
	        String result = nservice.callOcrApi(convFile);
	        convFile.delete();
	        
	        return result;
	    }
		
	}

	
	/*
	
	
	@Autowired NaverOcrService service;
    @GetMapping("/test")
    public String ocr() {
        return "foodboard/test";
    }

    @PostMapping("/test")
    public String analyzeImage(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
        file.transferTo(convFile);
        String result = service.callOcrApi(convFile);
        convFile.delete();
        model.addAttribute("result", result);

        return"foodboard/test";
    }
	
	
	*/