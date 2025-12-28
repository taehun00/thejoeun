package com.pawject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pawject.dto.food.FoodDto;
import com.pawject.dto.food.NutriDto;
import com.pawject.dto.food.SearchPetfoodDto;
import com.pawject.service.food.FoodService;
import com.pawject.service.food.SearchPetfoodService;

@RestController
@RequestMapping("/petfoodsearcher")
public class SearchPetfoodAjaxController {
	
	@Autowired SearchPetfoodService service;	
	
	//검색
	@GetMapping("/searchfilter")
		public Map<String, Object> searchfilter(    
				  @RequestParam(required = false) String keyword,
				  @RequestParam(required = false) Integer pettypeid,
				  @RequestParam(required = false) String foodtype,
				  @RequestParam(required = false) Integer  brandid,
				  @RequestParam(required = false) Integer  foodid,
				  @RequestParam(required = false) String category,
				  @RequestParam(required = false) String petagegroup,
				  @RequestParam(required = false) String isgrainfree,
				  @RequestParam(required = false) String origin,
				  @RequestParam(required = false) Integer  rangeid,
				  @RequestParam(required = false) Integer  minvalue,
				  @RequestParam(required = false) Integer  maxvalue) { //int 안됨!!
		
		Map<String, Object> params = new HashMap<>();
	    params.put("keyword", keyword);
	    params.put("pettypeid", pettypeid);
	    params.put("foodtype", foodtype);
	    params.put("brandid", brandid);
	    params.put("foodid", foodid);
	    params.put("category", category);
	    params.put("petagegroup", petagegroup);
	    params.put("isgrainfree", isgrainfree);
	    params.put("origin", origin);
	    params.put("rangeid", rangeid);
	    params.put("minvalue", minvalue);
	    params.put("maxvalue", maxvalue);

	    List<SearchPetfoodDto> list = service.foodfilter(params);

	    Map<String, Object> result = new HashMap<>();
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


