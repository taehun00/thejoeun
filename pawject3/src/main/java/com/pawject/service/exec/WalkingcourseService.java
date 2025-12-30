package com.pawject.service.exec;

import java.util.List;
import com.pawject.dto.exec.WalkingcourseDto;

public interface WalkingcourseService {
	public int insertwalking(  WalkingcourseDto wdto);
	public int updatewalking(  WalkingcourseDto wdto);
	public int deletewalking(  WalkingcourseDto wdto);
	public List<WalkingcourseDto> selectAllwalking();
	public WalkingcourseDto       selectwalking(int courseid);
	public WalkingcourseDto		   selectwalkingUpdateForm(int courseid);
	
	/* PAGING */
	public List<WalkingcourseDto> selectwalking10(int pageNo);
	public int				selectwalkingTotalCnt();
	
	/* SEARCH + PAGING */
	public List<WalkingcourseDto> selectwalking3( String keyword ,int pageNo); //##
	public int				        selectwalkingSearchTotalCnt( String keyword ); //##

}
