package com.pawject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pawject.dto.food.SearchPetfoodDto;
import com.pawject.dto.food.SearchPetfoodInitResponse;
import com.pawject.dto.support.CSQuestionDto;
import com.pawject.service.food.FoodApi;
import com.pawject.service.food.FoodService;
import com.pawject.service.food.SearchPetfoodService;
import com.pawject.util.UtilPaging;

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
    
 // 검색 + 페이징
    @GetMapping("/searchfilterPaging")
    public Map<String, Object> searchfilterPaging(    
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer pettypeid,
            @RequestParam(required = false) String foodtype,
            @RequestParam(required = false) Integer brandid,
            @RequestParam(required = false) Integer foodid,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String petagegroup,
            @RequestParam(required = false) String isgrainfree,
            @RequestParam(required = false) String origin,
            @RequestParam(required = false) Integer rangeid,
            @RequestParam(required = false) Integer minvalue,
            @RequestParam(required = false) Integer maxvalue,
            @RequestParam(defaultValue = "1") int pstartno,
            @RequestParam(required = false) String condition
    ) { 
        // 위치 주의
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
        List<SearchPetfoodDto> list = service.foodfilter10(params, condition, pstartno);

        Map<String, Object> result = new HashMap<>();
        result.put("list", list);

        UtilPaging paging = new UtilPaging(total, pstartno, 5, 10);
        result.put("paging", paging);
        result.put("total", total);

        return result;
    }

	@Autowired FoodApi apiservice;
	@Operation(summary = "사료 검색 AI 필터 변환")
	@PostMapping("/foodapi")
	public ResponseEntity<Map<String, Object>> foodapi(@RequestParam String userMessage) {
	    Map<String, Object> result = apiservice.aiChangeFilter(userMessage);
	    return ResponseEntity.ok(result);
	}
	
	@Operation(summary = "사료 상세 카드 조회")
	@GetMapping("/modalcard/{foodid}")
	public ResponseEntity<SearchPetfoodDto> modalcard(@PathVariable int foodid){
	    return ResponseEntity.ok(service.detailCard(foodid));
	}
}
