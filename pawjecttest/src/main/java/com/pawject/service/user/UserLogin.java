package com.pawject.service.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pawject.model.user.UserDao;
import com.pawject.model.user.UserDto;


public class UserLogin implements UserInterface {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserDao dao = new UserDao();
		UserDto dto = new UserDto();
		
		dto.setEmail(email);
		dto.setPassword(password);
		int result = dao.loginCheck(dto);
		
		HttpSession  session = request.getSession(); 
		
		session.setAttribute("email", email);
		// session.setAttribute("userid", result.getUserid());
		
		request.setAttribute("result", result);

	}

}
