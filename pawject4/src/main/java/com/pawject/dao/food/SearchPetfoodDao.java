package com.pawject.dao.food;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.pawject.dto.food.SearchPetfoodCondition;
import com.pawject.dto.food.SearchPetfoodDto;

@Mapper
public interface SearchPetfoodDao {

	//	<select resultMap="SearchPetfoodMap" id="resultcard">
	public List<SearchPetfoodDto> resultcard();
	
//	<select resultMap="SearchPetfoodMap" id="foodfilter" parameterType="java.util.HashMap">
	public List<SearchPetfoodDto> foodfilter(Map<String,Object> params);
	
	//	<select resultMap="RangeMap" id="rangeList">
	public List<SearchPetfoodDto> rangeList();
	
	
	//서치+페이징
	List<SearchPetfoodDto> foodfilter10(SearchPetfoodCondition cond);
	int foodfilterCnt(SearchPetfoodCondition cond);
	
	
	//ai데이터용
	//<select resultMap="SearchPetfoodMap" id="aiRecommend" >
	public List<SearchPetfoodDto> aiRecommend();
	
	//모달용
//	<select resultMap="SearchPetfoodMap" id="detailCard" parameterType="int">
	public SearchPetfoodDto detailCard(int foodid);
}
