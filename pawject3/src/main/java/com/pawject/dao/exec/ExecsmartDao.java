package com.pawject.dao.exec;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.pawject.dto.exec.ExecsmartDto;

@Mapper
public interface ExecsmartDao {
	public int insertsmart(  ExecsmartDto sdto);
	public int updatesmart(  ExecsmartDto sdto);
	public int updateHitsmart( int postid ); // 타입캐스팅: @RequestParam("postid")
	public int deletesmart(   ExecsmartDto sdto);
	public List<ExecsmartDto> selectAllsmart();
	public ExecsmartDto       selectsmart(int postid);
	
	/* PAGING */
	public List<ExecsmartDto> selectsmart10(HashMap<String,Integer> para);
	public int		    selectsmartTotalCnt();
	
	/* SEARCH + PAGING */
	public List<ExecsmartDto> selectsmart3(HashMap<String, Object> para); //##
	public int				  selectsmartSearchTotalCnt(String search); //##
	
}

