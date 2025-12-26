package com.pawject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pawject.dto.food.FoodDto;
import com.pawject.dto.food.NutriDto;
import com.pawject.dto.food.SearchPetfoodDto;
import com.pawject.service.food.SearchPetfoodService;

@RestController
@RequestMapping("/findpetfood")
public class SearchPetfoodController {
	
	@Autowired SearchPetfoodService service;


	//검색
	@GetMapping("/searchfilter")
		public Map<String, Object> searchfilter(
		        @RequestParam(required = false) String keyword) {
		    Map<String, Object> result = new HashMap<>();
		    
		    List<SearchPetfoodDto> list = service.foodfilter(keyword);
		    result.put("list", list);
		    
		    return result;
		}
	
	//사료정보 - 아작스는 기능별 분리 필요
	@GetMapping("/fooddetail")
	public FoodDto foodDetail(@RequestParam int foodid) {
	    return service.getFoodDetail(foodid);
	}
	
	//영양정보
	@GetMapping("/nutridetail")
	public List<NutriDto> foodNutrients(@RequestParam int foodid) {
	    return service.getFoodNutrients(foodid);
	}
	
}	


