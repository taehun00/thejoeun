package com.pawject.dao.food;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pawject.dto.food.SearchPetfoodDto;

@Mapper
public interface SearchPetfoodDao {

	//	<select resultMap="SearchPetfoodMap" id="resultcard">
	public List<SearchPetfoodDto> resultcard();
	
//	<select resultMap="SearchPetfoodMap" id="foodfilter" parameterType="java.util.HashMap">
	public List<SearchPetfoodDto> foodfilter(HashMap<String,Object> para);
}
