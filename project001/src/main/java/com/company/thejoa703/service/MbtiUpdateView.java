package com.company.thejoa703.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.thejoa703.dto.PostDao;

public class MbtiUpdateView implements MbtiService {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		//1. 데이터넘겨받고
		int id =  Integer.parseInt(request.getParameter("id"));
		
		//2. 디커프리( PostDao ) db처리
		PostDao dao = new PostDao();
		
		//3. 데이터 넘기기
		request.setAttribute("dto", dao.select(id));
	}

}