package com.pawject.service.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pawject.model.user.UserDao;
import com.pawject.model.user.UserDto;

public class UserLogout implements UserInterface {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
	    PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		
		session.invalidate();
		

		out.println("<script>alert('로그아웃 성공'); location.href='loginView.u';</script>");
	}

}
