package com.pawject.service.Disease;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pawject.dao.Disswc.DiseaseDao;
import com.pawject.dto.Disswc.DisswcDto;
@Service
public class DiseaseServiceImpl implements DiseaseService { 
	@Autowired DiseaseDao  dao;
	

	@Override public int insert( DisswcDto dto) { 
	
		try { dto.setBip( InetAddress.getLocalHost().getHostAddress() ); }
		catch (UnknownHostException e) { e.printStackTrace(); }
		return dao.insert(dto); 
	}
	
	@Override public int update( DisswcDto dto) {
		
		return dao.update(dto); 
	}
	
	@Override public int delete(int disno) { return dao.delete(disno); }
	
	@Override public List<DisswcDto> selectAll(Map<String, Object> params) { return dao.selectAll(params); }
	@Transactional
	@Override public DisswcDto select(int disno) { dao.updateHit(disno);  return dao.select(disno); }
	@Override public DisswcDto selectUpdateForm(int disno) { return dao.select(disno); }

	/* paging */
	@Override
	public List<DisswcDto> select10(int pageNo) {  //(1)1,10 , (2) 11,20 (3) 21,30
		HashMap<String,Object>   para = new HashMap<>();
		int start = (pageNo-1)*10 + 1;  //(1)1    (2)11  (2)21
		int end   = start + 9;
		
		para.put("start", start);
		para.put("end"  , end);
		return dao.select10(para);
	}

	@Override public int selectTotalCnt() { return dao.selectTotalCnt(); }
	
	/* Paging + Search */
	@Override
	public List<DisswcDto> select3(String keyword, int pageNo) {
		HashMap<String, Object> para = new HashMap<>();
		//  11-1 (10/10 = 1) 20-1(19/10 = 1)
		int pageSize=3; //3개씩의 페이지
		// 1: start→1, end→3   2: start→4, end→6   3: start→7, end→9 
		para.put("search", keyword);
		int start = (pageNo-1)*pageSize+1;
		para.put("start", start);   // 1→1    2→4 (2-1)*3+1   3→7 (3-1)*3+1
		para.put("end"  , start + pageSize-1);   //4→6 (4+3-1)   , 7→9  (7+3-1)
		return dao.select3(para);
	}

	@Override 
	public int selectSearchTotalCnt(String keyword) { return dao.selectSearchTotalCnt(keyword); }

}
