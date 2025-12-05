package com.pawject.service.food;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawject.dao.food.BrandDao;
import com.pawject.dao.food.FoodDao;
import com.pawject.dao.food.NutriDao;
import com.pawject.dto.food.BrandDto;
import com.pawject.dto.food.FoodDto;
import com.pawject.dto.food.FoodDtoForList;
import com.pawject.dto.food.NutriDto;
@Service
public class FoodServiceImpl implements FoodService {
	@Autowired FoodDao fdao;
	@Autowired BrandDao bdao;
	@Autowired NutriDao ndao;


		@Override
		public int foodinsert(FoodDto dto) {
			return fdao.foodinsert(dto);
		}

		@Override
		public List<FoodDto> foodselectAll() {
			return fdao.foodselectAll();
		}

		@Override
		public FoodDto foodselect(int foodid) {
			return fdao.foodselect(foodid);
		}

		@Override
		public int foodupdate(FoodDto dto) {
			return fdao.foodupdate(dto);
		}

		@Override
		public int fooddelete(int foodid) {
			return fdao.fooddelete(foodid);
		}

		@Override
		public int nutriinsert(NutriDto dto) {
			return ndao.nutriinsert(dto);
		}

		@Override
		public List<NutriDto> nutriselectAll() {
			return ndao.nutriselectAll();
		}



		@Override
		public List<NutriDto> nutriselect(int foodid) {
			return ndao.nutriselect(foodid);
		}

		@Override
		public int nutriupdate(NutriDto dto) {
			return ndao.nutriupdate(dto);
		}

		@Override
		public int nutridelete(NutriDto dto) {
			return ndao.nutridelete(dto);
		}

		@Override
		public int nutrideleteAll(int foodid) {
			return ndao.nutrideleteAll(foodid);
		}

		

		@Override
		public List<FoodDtoForList> foodselectForList() {
			return fdao.foodselectForList();
		}

		@Override
		public NutriDto nutriselectForWrite(int foodid) {
			return ndao.nutriselectForWrite(foodid);
		}

		@Override
		public List<BrandDto> brandSelectAll() {
			return bdao.brandSelectAll();
		}

		@Override
		public List<NutriDto> nutrientSelectName() {

			return ndao.nutrientSelectName();
		}

		@Override
		public FoodDto foodselectwithBrand(int foodid) {
			return fdao.foodselectwithBrand(foodid);
		}

		@Override
		public List<NutriDto> nutriselectWithInfo(int foodid) {
			// TODO Auto-generated method stub
			return ndao.nutriselectWithInfo(foodid);
		}

		@Override
		public List<FoodDtoForList> foodselect10(int pstartno) {
		    HashMap<String, Object> para = new HashMap<>();
		    int start = (pstartno - 1) * 10 + 1;
		    int end = start + 9;

		    para.put("start", start);
		    para.put("end", end);
		    

		    return fdao.foodselect10(para); 
		}

		@Override
		public int foodselectcnt() {
			return fdao.foodselectcnt();
		}

		@Override
		public List<FoodDtoForList> foodsearch(String keyword, String searchType) {

			HashMap<String, Object> para = new HashMap<>();
			keyword = keyword.toLowerCase(); //대소문자 구분 x
			String searchLike = "%" + keyword + "%";
			
			switch(searchType) {
			//분기1. 펫타입
	        case "pettypeid":
	            para.put("searchType", "pettypeid");

	            if ("고양이".equals(keyword)) {
	                para.put("search", "1");
	            } else if ("강아지".equals(keyword)) {
	                para.put("search", "2");
	            } else {
	                para.put("search", "-1");
	            }
	            break;
			//분기2. 브랜드
			case "brandname" : para.put("searchType", "brandname");
			 				   para.put("search", searchLike);	break;

			//분기3. 사료이름
			case "foodname" : para.put("searchType", "foodname"); 
							  para.put("search", searchLike);	break;
	
			//분기4. 제목+내용+브랜드
			case "all" : para.put("searchType", "all");
						para.put("search", searchLike);	break;
			
	
			}//switch
			
			return fdao.foodsearch(para);
		}

		@Override
		public int foodsearchcnt(String keyword, String searchType) {
			HashMap<String, Object> para = new HashMap<>();
			String searchLike = "%" + keyword + "%";
			
			switch(searchType) {
			//분기1. 펫타입
	        case "pettypeid":
	            para.put("searchType", "pettypeid");

	            if ("고양이".equals(keyword)) {
	                para.put("search", "1");
	            } else if ("강아지".equals(keyword)) {
	                para.put("search", "2");
	            } else {
	                para.put("search", "-1");
	            }
	            break;
			//분기2. 브랜드
			case "brandname" : para.put("searchType", "brandname");
			 				   para.put("search", searchLike);	break;

			//분기3. 사료이름
			case "foodname" : para.put("searchType", "foodname"); 
							  para.put("search", searchLike);	break;
	
			//분기4. 제목+내용
			case "all" : para.put("searchType", "all");
						para.put("search", searchLike);	break;
			
	
			}//switch
			
			return fdao.foodsearchcnt(para);
		}

		
	}

