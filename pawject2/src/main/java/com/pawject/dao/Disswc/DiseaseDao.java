package com.pawject.dao.Disswc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pawject.dao.MyDao;
import com.pawject.dto.Disswc.DisswcDto;



@MyDao
public interface DiseaseDao {
	public int       insert(DisswcDto dto);
	public int       update(DisswcDto dto);
	public int       updateHit(int  disno);
	public int       delete(int disno);
	public DisswcDto	     select(int disno);
	public List<DisswcDto> selectAll(Map<String, Object> params);
	
	/*Ajax*/
	public List<DisswcDto>  selectSearch( HashMap<String, String> para);
	
	/*Paging*/
	public List<DisswcDto> select10(HashMap<String,Object> para);
	public int selectTotalCnt();
	 

}