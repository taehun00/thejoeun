package com.company.thejoa703.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.thejoa703.dto.PostDao;
import com.company.thejoa703.dto.PostDto;

public class MbtiDelete implements MbtiService {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		//String title = request.getParameter("title");
		//String content = request.getParameter("content");
		String pass = request.getParameter("pass");
		int id = Integer.parseInt(request.getParameter("id"));
		
		PostDao dao = new PostDao();
		PostDto dto = new PostDto();
		
		dto.setId(id);
		dto.setPass(pass);
		
		request.setAttribute("result", dao.delete(dto));



		
	}

}
