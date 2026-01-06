package com.pawject.dao.exec;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pawject.dto.exec.ExecinfoDto;

@Mapper
public interface ExecinfoDao {
	public int insertinfo(  ExecinfoDto idto);
	public int updateinfo(  ExecinfoDto idto);
	public int deleteinfo(  int execid);
	public List<ExecinfoDto> selectAllinfo();
	public ExecinfoDto       selectinfo(int execid);
	
	/* PAGING */
	public List<ExecinfoDto> selectinfo10(HashMap<String,Integer> para);
	public int	       selectinfoTotalCnt();
	/* SEARCH + PAGING */
	public List<ExecinfoDto> selectinfo3(HashMap<String, Object> para); //##
	public int				   selectinfoSearchTotalCnt(String search); //##

	
}
