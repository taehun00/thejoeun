package com.pawject.service.Disswc;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawject.dao.Disswc.DiseaseDao;
import com.pawject.dto.Disswc.DisswcDto;

@Service
public class DiseaseServiceImpl implements DiseaseService {
	@Autowired DiseaseDao  dao;
	public int insert(DisswcDto dto) {
		try {dto.setBip(InetAddress.getLocalHost().getHostAddress());}
		catch(UnknownHostException e) {e.printStackTrace();}
		return dao.insert(dto);
	}

//	@Override public int insert(DisswcDto dto) {  return 0; }
	@Override public int update(DisswcDto dto) {  return dao.update(dto); }
//	@Override public int updateHit(int id) { return dao.updateHit(id); }
	@Override public int delete(int disno) {  return dao.delete(disno); }
	@Override public DisswcDto selectUpdateForm(int disno) {     return dao.select(disno); }
	@Override public DisswcDto select(int disno) { dao.updateHit(disno);  /*조회수올리기*/ return dao.select(disno); }
	@Override public List<DisswcDto> selectAll(Map<String, Object> params) {  return dao.selectAll(params); }

	
	/* Search - Ajax */
	public List<DisswcDto> selectSearch(String keyword){
		HashMap<String, String> para=new HashMap<>();
		para.put("search", "%" + keyword + "%");
		return dao.selectSearch(para);
	}
	

}
