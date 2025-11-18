package com.pawject.service.exec;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ExecinfoService {

	  public void exec(HttpServletRequest request, HttpServletResponse response)
	  throws ServletException, IOException ;
	
	//1. 데이터가져오기  
	//2. 드커프리       
	//3. 데이터 넘겨받기  
	
}

//6-2. service 구조
//◆  MbtiTypeService<<interface>>
//   △......... ExecInsert           등록기능        / insert()
//   △......... ExecList             전체보기        / selectAll() 
//   △......... ExecDetail           상세보기,수정폼   / select()  
//   △......... ExecUpdate           수정기능        / update() 
//   △......... ExecDelete           삭제기능        / delete() 