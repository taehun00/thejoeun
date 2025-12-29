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
import com.pawject.dto.support.CSQuestionDto;
import com.pawject.service.food.FoodService;
import com.pawject.service.food.SearchPetfoodService;
import com.pawject.util.UtilPaging;

@RestController
@RequestMapping("/petfoodsearcher")
public class SearchPetfoodAjaxController {
	
	@Autowired SearchPetfoodService service;	
	
	//검색(페이징x)
//	@GetMapping("/searchfilter")
//		public Map<String, Object> searchfilter(    
//				  @RequestParam(required = false) String keyword,
//				  @RequestParam(required = false) Integer pettypeid,
//				  @RequestParam(required = false) String foodtype,
//				  @RequestParam(required = false) Integer  brandid,
//				  @RequestParam(required = false) Integer  foodid,
//				  @RequestParam(required = false) String category,
//				  @RequestParam(required = false) String petagegroup,
//				  @RequestParam(required = false) String isgrainfree,
//				  @RequestParam(required = false) String origin,
//				  @RequestParam(required = false) Integer  rangeid,
//				  @RequestParam(required = false) Integer  minvalue,
//				  @RequestParam(required = false) Integer  maxvalue) { //int 안됨!!
//		
//		Map<String, Object> params = new HashMap<>();
//	    params.put("keyword", keyword);
//	    params.put("pettypeid", pettypeid);
//	    params.put("foodtype", foodtype);
//	    params.put("brandid", brandid);
//	    params.put("foodid", foodid);
//	    params.put("category", category);
//	    params.put("petagegroup", petagegroup);
//	    params.put("isgrainfree", isgrainfree);
//	    params.put("origin", origin);
//	    params.put("rangeid", rangeid);
//	    params.put("minvalue", minvalue);
//	    params.put("maxvalue", maxvalue);
//
//	    List<SearchPetfoodDto> list = service.foodfilter(params);
//
//	    Map<String, Object> result = new HashMap<>();
//	    result.put("list", list);
//	    return result;
//		}
	
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
	
	
	//검색+페이징
	@GetMapping("/searchfilterPaging")
		public Map<String, Object> searchfilterPaging(    
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
				  @RequestParam(required = false) Integer  maxvalue,
				  
				  @RequestParam(defaultValue="1") int pstartno				) { 
		//위치 주의
		if (minvalue != null && minvalue < 0) minvalue = null;
		if (maxvalue != null && maxvalue < 0) maxvalue = null;
		
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

	    
	    int total = service.foodfilterCnt(params);

	    List<SearchPetfoodDto> list = service.foodfilter10(params, pstartno);
	    Map<String, Object> result = new HashMap<>();
	    result.put("list", list);
	    
	    //public UtilPaging(int listtotal, int pageNo , int onepagelist , int  bottomlist)
	    UtilPaging paging = new UtilPaging(total, pstartno, 5, 10);
	    result.put("paging", paging);
	    result.put("total", total);

	    return result;
	}
	
	
	
	
}	


