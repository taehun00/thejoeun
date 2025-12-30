package com.pawject.service.food;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawject.dao.food.FoodDao;
import com.pawject.dao.food.NutriDao;
import com.pawject.dao.food.SearchPetfoodDao;
import com.pawject.dto.food.FoodDto;
import com.pawject.dto.food.NutriDto;
import com.pawject.dto.food.SearchPetfoodDto;
import com.pawject.util.UtilPaging;
@Service
public class SearchPetfoodServiceImpl implements SearchPetfoodService {
	@Autowired SearchPetfoodDao dao;
	
	//카드 전체 출력 - 랜더링 테스트용
	@Override
	public List<SearchPetfoodDto> resultcard() {
		return dao.resultcard();
	}

	//서치
	/*
	 * @Override public List<SearchPetfoodDto> foodfilter(Map<String, Object>
	 * params) {
	 * 
	 * HashMap<String, Object> para = new HashMap<>(); para.put("keyword", keyword);
	 * 
	 * return dao.foodfilter(para); }
	 */

	@Override
	public List<SearchPetfoodDto> foodfilter(Map<String,Object> params) {
	    return dao.foodfilter(params);
	}
	
	
	//사료+영양데이터(기존 메서드 활용)
	@Autowired FoodDao fdao;
	@Autowired NutriDao ndao;

	@Override
	public FoodDto getFoodDetail(int foodid) {
		return fdao.foodselectwithBrand(foodid);
	}

	@Override
	public List<NutriDto> getFoodNutrients(int foodid) {
		return ndao.nutriselectWithInfo(foodid);
	}

	@Override
	public List<SearchPetfoodDto> rangeList() {
		return dao.rangeList();
	}

	@Override
	public List<SearchPetfoodDto> foodfilter10(Map<String,Object> params, int pstartno) {
		//5개씩 출력
		int pageSize=5;
		int start = (pstartno-1)*pageSize+1;
		params.put("start", start);  
		params.put("end"  , start + pageSize-1);  

		return dao.foodfilter10(params);
	}

	
	@Override
	public int foodfilterCnt(Map<String, Object> params) {
		return dao.foodfilterCnt(params);
	}

	@Override
	public List<SearchPetfoodDto> aiRecommend() {
		return dao.aiRecommend();
	}
	


}
