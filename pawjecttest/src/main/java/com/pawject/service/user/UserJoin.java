package com.pawject.service.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pawject.model.user.UserDao;



public class UserJoin implements UserInterface {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		//response.setContentType("text/html; charset=UTF-8");
        //PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String nickname = request.getParameter("nickname");
        String password = request.getParameter("password");

        UserDao dao = new UserDao();
        int result = dao.insertUser(email, nickname, password);

        request.setAttribute("dto", result);

	}

}
