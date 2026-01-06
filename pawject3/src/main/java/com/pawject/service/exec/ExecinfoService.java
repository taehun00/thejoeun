package com.pawject.service.exec;

import java.util.List;
import com.pawject.dto.exec.ExecinfoDto;

public interface ExecinfoService {
	public int insertinfo(  ExecinfoDto idto);
	public int updateinfo(  ExecinfoDto idto);
	public int deleteinfo(  int execid);
	public List<ExecinfoDto> selectAllinfo();
	public ExecinfoDto       selectinfo(int execid);
	public ExecinfoDto		 selectinfoUpdateForm(int execid);
	
	/* PAGING */
	public List<ExecinfoDto> selectinfo10(int pageNo);
	public int	       selectinfoTotalCnt();
	
	/* SEARCH + PAGING */
	public List<ExecinfoDto> selectinfo3( String keyword, int pageNo ); //##
	public int				   selectinfoSearchTotalCnt(String keyword); //##

}
