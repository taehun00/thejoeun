package com.pawject.service.exec;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawject.dao.exec.WalkingcourseDao;
import com.pawject.dto.exec.WalkingcourseDto;

@Service
public class WalkingcourseServiceImpl implements WalkingcourseService {
	@Autowired WalkingcourseDao cdao;
	
	@Override public int insertwalking(WalkingcourseDto wdto) { return cdao.insertwalking(wdto); }
	@Override public int updatewalking(WalkingcourseDto wdto) { return cdao.updatewalking(wdto); }
	@Override public int deletewalking(WalkingcourseDto wdto) { return cdao.deletewalking(wdto); }
	@Override public List<WalkingcourseDto> selectAllwalking() { return cdao.selectAllwalking(); }
	@Override public WalkingcourseDto selectwalking(int courseid) { return cdao.selectwalking(courseid); }
	@Override public WalkingcourseDto selectwalkingUpdateForm(int courseid) { return cdao.selectwalking(courseid); }

	/* PAGING */
	@Override public List<WalkingcourseDto> selectwalking10(int pageNo) { 
		HashMap<String,Integer>   para = new HashMap<>();
		int start = (pageNo-1)*10 + 1;  //(1)1    (2)11  (2)21
		int end   = start + 9;
		
		para.put("start", start);
		para.put("end"  , end);

		return cdao.selectwalking10(para); 
	}
	@Override public int selectwalkingTotalCnt() { return cdao.selectwalkingTotalCnt(); }

	/* SEARCH + PAGING */
	@Override public List<WalkingcourseDto> selectwalking3(String keyword, int pageNo) { 
		HashMap<String, Object> para = new HashMap<>();
		//  11-1 (10/10 = 1) 20-1(19/10 = 1)
		int pageSize=3; //3개씩의 페이지
		// 1: start→1, end→3   2: start→4, end→6   3: start→7, end→9 
		para.put("search", keyword);
		int start = (pageNo-1)*pageSize+1;
		para.put("start", start);   // 1→1    2→4 (2-1)*3+1   3→7 (3-1)*3+1
		para.put("end"  , start + pageSize-1);   //4→6 (4+3-1)   , 7→9  (7+3-1)

		return cdao.selectwalking3(para); 
	}
	@Override public int selectwalkingSearchTotalCnt(String keyword) { return cdao.selectwalkingSearchTotalCnt(keyword); }

}
