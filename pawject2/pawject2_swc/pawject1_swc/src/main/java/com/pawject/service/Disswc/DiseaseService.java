package com.pawject.service.Disswc;

import java.util.List;
import java.util.Map;
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
}
