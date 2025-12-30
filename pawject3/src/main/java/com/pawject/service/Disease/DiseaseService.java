package com.pawject.service.Disease;

import java.util.List;
import java.util.Map;

import com.pawject.dto.Disswc.DisswcDto;


public interface DiseaseService {
	public int insert(DisswcDto dto);
	public int update( DisswcDto dto);
	public int delete(int disno);
	public List<DisswcDto>  selectAll(Map<String, Object> params);
	public DisswcDto        select(int disno);  //조회수올리기 + 상세보기
	public DisswcDto        selectUpdateForm(int disno);  //수정하기폼
	
	/* Paging */
	public List<DisswcDto> select10(int pageNo);
	public int  selectTotalCnt();
	
	/* Paging + Search */
	public List<DisswcDto>  select3( String keyword ,int pageNo);
	public int  selectSearchTotalCnt( String keyword );
}
