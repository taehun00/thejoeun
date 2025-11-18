package com.pawject.service.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pawject.model.user.UserDao;
import com.pawject.model.user.UserDto;

public class UserTitle implements UserInterface {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		String email = (String) session.getAttribute("email");
		
		UserDao dao = new UserDao();
		UserDto result = dao.getUserInfoByEmail(email);
		
		
		
		request.setAttribute("dto", result);
	}

}
