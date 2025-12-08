package com.pawject.dao.food;

import java.util.List;

import com.pawject.dao.MyDao;
import com.pawject.dto.food.BrandDto;

@MyDao
public interface BrandDao {
	
	//<select resultMap="BrandMap" id="brandSelectAll" >
	public List<BrandDto> brandSelectAll();
}
