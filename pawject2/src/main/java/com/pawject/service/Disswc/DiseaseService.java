package com.pawject.service.Disswc;

import java.util.List;
import java.util.Map;
<<<<<<< HEAD
=======

>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
import com.pawject.dto.Disswc.DisswcDto;

public interface DiseaseService {
	public int insert(DisswcDto dto);
	public int update(DisswcDto dto);
	//public int updateHit(int id);
	public int delete(int dino);
	
	public DisswcDto	     select(int disno);
	public List<DisswcDto> selectAll(Map<String, Object> params);
	public DisswcDto	     selectUpdateForm(int disno);
	
	/* Search - Ajax */
	public List<DisswcDto> selectSearch(String keyword);
<<<<<<< HEAD
=======
	
	/*Paging*/
	public List<DisswcDto>  select10(int pstartno);
	public int  selectTotalCnt();
>>>>>>> cb4a38b2ec94f84ae8d5b1165d16b0247b5b119c
}
