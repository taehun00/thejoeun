package com.company.thejoa703.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.thejoa703.dto.PostDao;
import com.company.thejoa703.dto.PostDto;



public class MbtiInsert implements MbtiService {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		//1. 데이터 넘겨받기
		//int app_user_id = Integer.parseInt(request.getParameter("app_user_id"));
		HttpSession session = request.getSession();
		int app_user_id = (Integer)session.getAttribute("APP_USER_ID");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String pass = request.getParameter("pass");
		//2. 드카프리 ( PostDao )
		
		PostDao dao = new PostDao();
		PostDto dto = new PostDto();
		dto.setAppUserId(app_user_id);
		dto.setTitle(title); dto.setContent(content); dto.setPass(pass);
		String result = String.valueOf( dao.insert(dto) );
		//3. 데이터 넘겨주기
		request.setAttribute("result", result);
	}

}