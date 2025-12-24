package com.pawject.dao.exec;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.pawject.dto.exec.SaveweatherDto;


@Mapper
public interface SaveweatherDao {
	public int insert(  SaveweatherDto dto);
	public int update(  SaveweatherDto dto);
	public int delete(   SaveweatherDto dto);
	public List<SaveweatherDto> selectAll();
	public SaveweatherDto       select(int basedate);
	
	/* PAGING */
	public List<SaveweatherDto> select10();
	public int				  selectTotal();
	/* SEARCH + PAGING */
	public List<SaveweatherDto> select3(HashMap<String, Object> para); //##
	public int				  selectSearchTotalCnt(String search); //##

}
