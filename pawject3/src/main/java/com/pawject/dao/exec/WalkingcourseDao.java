package com.pawject.dao.exec;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.pawject.dto.exec.WalkingcourseDto;

@Mapper
public interface WalkingcourseDao {
	public int insertwalking(  WalkingcourseDto wdto);
	public int updatewalking(  WalkingcourseDto wdto);
	public int deletewalking(  int courseid);
	public List<WalkingcourseDto> selectAllwalking();
	public WalkingcourseDto       selectwalking(int courseid);

	/* PAGING */
	public List<WalkingcourseDto> selectwalking10(HashMap<String,Integer> para);
	public int				selectwalkingTotalCnt();
	
	/* SEARCH + PAGING */
	public List<WalkingcourseDto> selectwalking3(HashMap<String, Object> para); //##
	public int				        selectwalkingSearchTotalCnt(String search); //##

}
