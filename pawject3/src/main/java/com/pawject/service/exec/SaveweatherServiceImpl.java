package com.pawject.service.exec;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawject.dao.exec.SaveweatherDao;
import com.pawject.dto.exec.SaveweatherDto;

@Service
public class SaveweatherServiceImpl implements SaveweatherService {
	@Autowired SaveweatherDao wdao;
	
	@Override public int insertweather(SaveweatherDto wdto) { return wdao.insertweather(wdto); }
	@Override public int updateweather(SaveweatherDto wdto) { return wdao.updateweather(wdto); }
	@Override public int deleteweather(SaveweatherDto wdto) { return wdao.deleteweather(wdto); }
	@Override public List<SaveweatherDto> selectAllweather() { return wdao.selectAllweather(); }
	@Override public SaveweatherDto selectweather(int basedate) { return wdao.selectweather(basedate); }
	@Override public SaveweatherDto selectweatherUpdateForm(int basedate) { return wdao.selectweather(basedate); }

	/* PAGING */
	@Override public List<SaveweatherDto> selectweather10(int pageNo) { 
		HashMap<String,Integer>   para = new HashMap<>();
		int start = (pageNo-1)*10 + 1;  //(1)1    (2)11  (2)21
		int end   = start + 9;
		
		para.put("start", start);
		para.put("end"  , end);

		return wdao.selectweather10(para); 
	}
	@Override public int selectweatherTotalCnt() { return wdao.selectweatherTotalCnt(); }

	/* SEARCH + PAGING */
	@Override public List<SaveweatherDto> selectweather3(String keyword, int pageNo) { 
		HashMap<String, Object> para = new HashMap<>();
		//  11-1 (10/10 = 1) 20-1(19/10 = 1)
		int pageSize=3; //3개씩의 페이지
		// 1: start→1, end→3   2: start→4, end→6   3: start→7, end→9 
		para.put("search", keyword);
		int start = (pageNo-1)*pageSize+1;
		para.put("start", start);   // 1→1    2→4 (2-1)*3+1   3→7 (3-1)*3+1
		para.put("end"  , start + pageSize-1);   //4→6 (4+3-1)   , 7→9  (7+3-1)
		return wdao.selectweather3(para); 
	}
	@Override public int selectweatherSearchTotalCnt(String keyword) { return wdao.selectweatherSearchTotalCnt(keyword); }

}
