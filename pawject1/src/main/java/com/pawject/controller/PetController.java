package com.pawject.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pawject.service.pet.PetInfoService;
import com.pawject.service.pet.PetInterface;

/**
 * Servlet implementation class PetController
 */
//@WebServlet("*.pet")
public class PetController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PetController() {
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
	    
	    PetInterface service = null;
	    
	    
		
		 if(path.equals("/petinfo.pet")) {
		 service = new PetInfoService();
		 service.exec(request, response);
		 
		 request.getRequestDispatcher("pet/petinfo.jsp").forward(request, response); 
		 
		 }else if(path.equals("/petaddView.pet")){
			 
			 request.getRequestDispatcher("pet/petadd.jsp").forward(request, response);
			 
		 }else if(path.equals("/petadd.pet")){
			 //service = new PetAdd();
			 //service.exec(request, response);
			 
			 out.println("<script>alert('반려동물 추가완료'); location.href='petinfo.pet'; </script>");
		 }else if(path.equals("petupdateView.pet")) {
			 
			 request.getRequestDispatcher("pet/petupdate.jsp").forward(request, response);
		 }else if(path.equals("petupdate.pet")) {
			 //service = new PetUpdate();
			 //service.exec(request, response);
			 
			 out.println("<script>alert('반려동물 수정완료'); location.href='petinfo.pet'; </script>");
		 }else if(path.equals("petdeleteView.pet")) {
			 
			 request.getRequestDispatcher("pet/petdelete.jsp").forward(request, response);
			 
		 }else if(path.equals("petdelete.pet")) {
			//service = new PetDelete();
			//service.exec(request, response);
			 
			 out.println("<script>alert('반려동물 삭제완료'); location.href='petinfo.pet'; </script>");
		 }
		 
		 
		
	}
}
