package com.pawject.service.food;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pawject.dto.food.BrandDto;
import com.pawject.dto.food.FoodDto;
import com.pawject.dto.food.FoodDtoForList;
import com.pawject.dto.food.NutriDto;
@Service
public class FoodServiceImpl implements FoodService {

	@Override
	public int foodinsert(FoodDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<FoodDto> foodselectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FoodDto foodselect(int foodid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int foodupdate(FoodDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int fooddelete(int foodid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int nutriinsert(NutriDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<NutriDto> nutriselectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NutriDto> nutriselect(int foodid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int nutriupdate(NutriDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int nutridelete(NutriDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int nutrideleteAll(int foodid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<FoodDtoForList> foodselectForList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NutriDto nutriselectForWrite(int foodid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BrandDto> brandSelectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NutriDto> nutrientSelectName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FoodDto foodselectwithBrand(int foodid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NutriDto> nutriselectWithInfo(int foodid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FoodDto> foodselect10(int pstartno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int foodselectcnt() {
		// TODO Auto-generated method stub
		return 0;
	}

}
