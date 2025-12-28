package com.pawject.dao.food;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.pawject.dto.food.SearchPetfoodDto;

@Mapper
public interface SearchPetfoodDao {

	//	<select resultMap="SearchPetfoodMap" id="resultcard">
	public List<SearchPetfoodDto> resultcard();
	
//	<select resultMap="SearchPetfoodMap" id="foodfilter" parameterType="java.util.HashMap">
	public List<SearchPetfoodDto> foodfilter(Map<String,Object> params);
	
	//	<select resultMap="RangeMap" id="rangeList">
	public List<SearchPetfoodDto> rangeList();
}
