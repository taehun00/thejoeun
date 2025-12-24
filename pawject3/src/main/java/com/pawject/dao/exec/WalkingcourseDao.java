package com.pawject.dao.exec;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.pawject.dto.exec.WalkingcourseDto;

@Mapper
public interface WalkingcourseDao {
	public int insert(  WalkingcourseDto dto);
	public int update(  WalkingcourseDto dto);
	public int delete(   WalkingcourseDto dto);
	public List<WalkingcourseDto> selectAll();
	public WalkingcourseDto       select(int courseid);

	/* PAGING */
	public List<WalkingcourseDto> select10();
	public int				   selectTotal();
	
	/* SEARCH + PAGING */
	public List<WalkingcourseDto> select3(HashMap<String, Object> para); //##
	public int				  selectSearchTotalCnt(String search); //##

}
