package com.pawject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pawject.dto.food.SearchPetfoodCondition;
import com.pawject.dto.food.SearchPetfoodDto;
import com.pawject.dto.food.SearchPetfoodInitResponse;
import com.pawject.dto.support.CSQuestionDto;
import com.pawject.service.food.FoodService;
import com.pawject.service.food.SearchPetfoodService;
import com.pawject.util.CalorieRangeUtil;
import com.pawject.util.PageResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "PETFOODSEARCHER", description = "사료검색") 
@RestController 
@RequestMapping("/petfoodsearcher") 
@RequiredArgsConstructor 
public class SearchPetfoodController {
@Autowired SearchPetfoodService service;
@Autowired FoodService fservice;

    @GetMapping("/init")   //검색창 초기값 기존 model -> dto로 분리
    public ResponseEntity<SearchPetfoodInitResponse> init() {
    		SearchPetfoodInitResponse res = new SearchPetfoodInitResponse();
        res.setBrandList(fservice.brandSelectAll());
        res.setFoodList(fservice.foodselectAll());
        res.setNutrientList(fservice.nutrientSelectName());
        res.setRangeList(service.rangeList());
        return ResponseEntity.ok(res);
    }
    
	@Operation(summary = "사료검색+페이징")
	@PostMapping("/foodsearch")
	public ResponseEntity<PageResponse<SearchPetfoodDto>> search(
	        @RequestBody SearchPetfoodCondition cond
	) {
		CalorieRangeUtil.calorie(cond);
		
	    int total = service.foodfilterCnt(cond);
	    List<SearchPetfoodDto> list = service.foodfilter10(cond);

	    PageResponse<SearchPetfoodDto> res = new PageResponse<>();
	    res.setList(list);
	    res.setTotal(total);
	    res.setPage(cond.getPage());
	    res.setTotalPages((int)Math.ceil((double)total / 5));
	    res.setHasPrev(cond.getPage() > 1);
	    res.setHasNext(cond.getPage() < res.getTotalPages());

	    return ResponseEntity.ok(res);
	}
    
    
}
/**
 *
	
	//api
	@Autowired FoodApi apiservice;
	@PostMapping("/foodapi")
	@ResponseBody
	public Map<String, Object> foodapi(@RequestParam String userMessage) { //스트링아님
		return apiservice.aiChangeFilter(userMessage);
	}
	
	
	//모달정보 - model에 담을 필요x
	@ResponseBody
	@RequestMapping("/modalcard")
	public SearchPetfoodDto modalcard(int foodid) {
	    return service.detailCard(foodid);
	}
	
	
	
}	
 */