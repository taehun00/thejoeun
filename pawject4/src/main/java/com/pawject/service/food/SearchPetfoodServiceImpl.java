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
import com.pawject.dto.food.SearchPetfoodCondition;
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
	public List<SearchPetfoodDto> foodfilter10(SearchPetfoodCondition cond) {

	    int pageSize = 5;
	    int start = (cond.getPage() - 1) * pageSize + 1;

	    cond.setStart(start);
	    cond.setEnd(start + pageSize - 1);

	    // 정렬값 검증 (화이트리스트)
	    if (cond.getSort() != null) {
	        switch (cond.getSort()) {
	            case "foodnameAsc":
	            case "foodnameDesc":
	            case "brandnameAsc":
	            case "brandnameDesc":
	            case "avgratingAsc":
	            case "avgratingDesc":
	                break;
	            default:
	                cond.setSort(null);
	        }
	    }

	    return dao.foodfilter10(cond);
	}

	@Override
	public int foodfilterCnt(SearchPetfoodCondition cond) {
	    return dao.foodfilterCnt(cond);
	}

	@Override
	public List<SearchPetfoodDto> aiRecommend() {
		return dao.aiRecommend();
	}

	@Override
	public SearchPetfoodDto detailCard(int foodid) {
		return dao.detailCard(foodid);
	}
	

}
