package com.pawject.service.exec;

import java.util.List;
import com.pawject.dto.exec.SaveweatherDto;

public interface SaveweatherService {
	public int insertweather(  SaveweatherDto wdto);
	public int updateweather(  SaveweatherDto wdto);
	public int deleteweather(  SaveweatherDto wdto);
	public List<SaveweatherDto> selectAllweather();
	public SaveweatherDto       selectweather(int basedate);
	public SaveweatherDto       selectweatherUpdateForm(int basedate);
	
	/* PAGING */
	public List<SaveweatherDto> selectweather10(int pageNo);
	public int			  selectweatherTotalCnt();
	
	/* SEARCH + PAGING */
	public List<SaveweatherDto> selectweather3( String keyword ,int pageNo); //##
	public int				      selectweatherSearchTotalCnt( String keyword ); //##

}
