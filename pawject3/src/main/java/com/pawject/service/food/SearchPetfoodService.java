package com.pawject.service.food;

import java.util.List;

import com.pawject.dto.food.FoodDto;
import com.pawject.dto.food.NutriDto;
import com.pawject.dto.food.SearchPetfoodDto;

public interface SearchPetfoodService {
	
		public List<SearchPetfoodDto> resultcard();
		public List<SearchPetfoodDto> foodfilter(String keyword);
		
		//기존 데이터
		public FoodDto getFoodDetail(int foodid);
		public List<NutriDto> getFoodNutrients(int foodid);

}
