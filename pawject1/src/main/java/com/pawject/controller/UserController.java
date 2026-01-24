package com.pawject.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pawject.service.user.UserTitle;
import com.pawject.service.user.UserInterface;
import com.pawject.service.user.UserJoin;
import com.pawject.service.user.UserLogin;
import com.pawject.service.user.UserLogout;

//   /user/*
//@WebServlet("*.u")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserController() {
        super();

    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doAction(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doAction(request, response);
	}
	
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    String path = request.getServletPath();
	    
	    UserInterface service = null;
	    
	    if(path.equals("/joinView.u")) {
	    	
	    	request.getRequestDispatcher("user/signup.jsp").forward(request, response);
	    
	    } else if(path.equals("/join.u")) {
	    	//■ UserJoin
	    	service = new UserJoin();
	    	service.exec(request, response);
	    	
	    	int result = (int) request.getAttribute("dto");
	    	if(result == 1) {
	    		out.println("<script>alert('회원가입에 성공했습니다.'); location.href='loginView.u'; </script>");
	    	}else {
	    		out.println("<script>alert('관리자에게 문의바랍니다.'); location.href='loginView.u'; </script>");
	    	}
	    } else if(path.equals("/loginView.u")) {
	    	
	    	
	    	request.getRequestDispatcher("user/login.jsp").forward(request, response);
	    } else if(path.equals("/login.u")) {
	    	// ■ UserLogin
	    	service = new UserLogin();
	    	service.exec(request, response);
	    	
	    	int result = (int) request.getAttribute("result");
	    	if(result == 1 ) {
	    		out.println("<script>alert('로그인에 성공했습니다.'); location.href='home.u'; </script>");
	    	}else {
	    		out.println("<script>alert('로그인에 실패했습니다.'); location.href='loginView.u'; </script>");
	    	}
	    } else if(path.equals("/home.u")) {
	    	//■ UserTitle
	    	service = new UserTitle();
	    	service.exec(request, response);
	    	
	    	request.getRequestDispatcher("user/home.jsp").forward(request, response);
	    } else if(path.equals("/logout.u")) {
	    	//■ UserLogout
	    	service = new UserLogout();
	    	service.exec(request, response);
	    	
	    	
	    	//out.println("<script> location.href='loginView.u'; </script>");
	    	
	    }


	
	}

}
