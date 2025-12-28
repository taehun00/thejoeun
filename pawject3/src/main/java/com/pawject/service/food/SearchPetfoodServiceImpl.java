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
	


}
