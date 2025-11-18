package com.pawject.service.dis;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pawject.model.dis.PawPostDao;



public class PawjectUpdateView implements PawjectService {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		PawPostDao  dao =new PawPostDao();
		
		request.setAttribute("dto",dao.select(id));

		

	}

}
