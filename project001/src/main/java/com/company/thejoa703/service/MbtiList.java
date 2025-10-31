package com.company.thejoa703.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.thejoa703.dto.PostDao;

public class MbtiList implements MbtiService {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		//1. 데이터 넘겨받기
		//2. 드카프리 ( PostDao )
		PostDao dao = new PostDao();
		
		//3. 데이터 넘겨주기
		request.setAttribute("list", dao.selectAll());
	}

}
