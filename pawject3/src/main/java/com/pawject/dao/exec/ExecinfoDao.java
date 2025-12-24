package com.pawject.dao.exec;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pawject.dto.exec.ExecinfoDto;

@Mapper
public interface ExecinfoDao {
	public int insert(  ExecinfoDto dto);
	public int update(  ExecinfoDto dto);
	public int delete(   ExecinfoDto dto);
	public List<ExecinfoDto> selectAll();
	public ExecinfoDto       select(int execid);
	
	/* PAGING */
	public List<ExecinfoDto> select10();
	public int				  selectTotal();
	/* SEARCH + PAGING */
	public List<ExecinfoDto> select3(HashMap<String, Object> para); //##
	public int				  selectSearchTotalCnt(String search); //##

	
}
