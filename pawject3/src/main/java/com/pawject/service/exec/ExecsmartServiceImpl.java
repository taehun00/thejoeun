package com.pawject.service.exec;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pawject.dao.exec.ExecsmartDao;
import com.pawject.dto.exec.ExecsmartDto;
import com.pawject.util.UtilUpload;

@Service
public class ExecsmartServiceImpl implements ExecsmartService {

	@Autowired ExecsmartDao sdao;
	@Autowired UtilUpload   supload;
	
	@Override public int insertsmart(MultipartFile file, ExecsmartDto sdto) { 
		if(!file.isEmpty()) {
			try { sdto.setEimg(supload.fileUpload(file)); } 
			catch (IOException e) { e.printStackTrace(); }
		}
		return sdao.insertsmart(sdto); 
	}
	
	@Override public int updatesmart(MultipartFile file, ExecsmartDto sdto) {
		if(!file.isEmpty()) {
			try { sdto.setEimg(supload.fileUpload(file)); } 
			catch (IOException e) { e.printStackTrace(); }
		}
		return sdao.updatesmart(sdto); 
	}
	
	@Override public int deletesmart(ExecsmartDto sdto) { return sdao.deletesmart(sdto); }
	@Override public List<ExecsmartDto> selectAllsmart() { return sdao.selectAllsmart(); }
	@Override public ExecsmartDto selectsmart(int postid) { sdao.updateHitsmart(postid); return sdao.selectsmart(postid); }
	@Override public ExecsmartDto selectsmartUpdateForm(int postid) { return sdao.selectsmart(postid); }
	
	/* PAGING */
	@Override 
	public List<ExecsmartDto> selectsmart10(int pageNo) {
		HashMap<String, Integer> para = new HashMap<>();
		int start = (pageNo-1)*10 + 1; //(1)1
		int end   = start + 9;
		
		para.put("start", start);
		para.put("end", end);
		
		return sdao.selectsmart10(para); 
	}
	@Override public int selectsmartTotalCnt() { return sdao.selectsmartTotalCnt(); }
	
	/* SEARCH + PAGING */
	@Override public List<ExecsmartDto> selectsmart3(String keyword, int pageNo) {
		HashMap<String, Object> para = new HashMap<>();
		int pageSize=3;
		para.put("search", keyword);
		
		int start = (pageNo-1)*pageSize+1;
		para.put("start", start);
		para.put("end", start + pageSize-1);
		
		return sdao.selectsmart3(para); 
	}
	@Override public int selectsmartSearchTotalCnt(String keyword) { return sdao.selectsmartSearchTotalCnt(keyword); }

}
