package com.pawject.dao.exec;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.pawject.dto.exec.SaveweatherDto;


@Mapper
public interface SaveweatherDao {
	public int insertweather(  SaveweatherDto wdto);
	public int updateweather(  SaveweatherDto wdto);
	public int deleteweather(  SaveweatherDto wdto);
	public List<SaveweatherDto> selectAllweather();
	public SaveweatherDto       selectweather(int wid);
	
	/* PAGING */
	public List<SaveweatherDto> selectweather10(HashMap<String,Integer> para);
	public int			  selectweatherTotalCnt();
	
	/* SEARCH + PAGING */
	public List<SaveweatherDto> selectweather3(HashMap<String, Object> para); //##
	public int				      selectweatherSearchTotalCnt(String search); //##
}
