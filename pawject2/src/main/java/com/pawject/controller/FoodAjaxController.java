	package com.pawject.controller;
	
	import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pawject.dto.food.FoodDto;
import com.pawject.dto.food.FoodDtoForList;
import com.pawject.service.food.FoodService;

	
	@RestController
	public class FoodAjaxController {
		@Autowired FoodService service;
		
//		//foodselectForList - 게시판용 전체 리스트 출력 페이지
//		@RequestMapping("/foodselectForList")
//		public List<FoodDtoForList> foodselectForList(){	
//			return service.foodselectForList();
//		}
//		
		//페이징 적용 버전
		@RequestMapping("/foodselectForList")
		public Map<String, Object> foodselectForList(
		    @RequestParam(defaultValue="1") int pstartno){	
			Map<String, Object> result = new HashMap<>();
			
			int total=service.foodselectcnt();
			List<FoodDtoForList> list = service.foodselect10(pstartno);
			
			result.put("total", total);
			result.put("list", list);
			
		    return result;
		}
		
		//빠른 삭제
		@RequestMapping("/foodquikdelete")
		public Map<String, Object> foodquikdelete(@RequestParam int foodid){
			Map<String, Object> result = new HashMap<>();
			FoodDto fdto = new FoodDto();
			fdto.setFoodid(foodid);
			result.put("result", service.fooddelete(foodid));
			return result;
		}
		
		
		//검색 기능
		@RequestMapping("/foodsearch")
		public List<FoodDtoForList> foodsearch(@RequestParam("search") String search){
			return service.foodsearch(search);
		}
	
	}
