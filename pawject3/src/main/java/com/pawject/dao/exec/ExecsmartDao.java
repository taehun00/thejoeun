package com.pawject.dao.exec;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pawject.dto.exec.ExecsmartDto;

@Mapper
public interface ExecsmartDao {
	public int insert(  ExecsmartDto dto);
	public int update(  ExecsmartDto dto);
	public int updateHit(int postid);
	public int delete(   ExecsmartDto dto);
	public List<ExecsmartDto> selectAll();
	public ExecsmartDto       select(int postid);
	
	/* PAGING */
	public List<ExecsmartDto> select10();
	public int				  selectTotal();
	
	/* SEARCH + PAGING */
	public List<ExecsmartDto> select3(HashMap<String, Object> para); //##
	public int				  selectSearchTotalCnt(String search); //##
	
}

