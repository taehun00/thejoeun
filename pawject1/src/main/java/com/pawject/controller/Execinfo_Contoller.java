package com.pawject.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pawject.service.exec.ExecDelete;
import com.pawject.service.exec.ExecDetail;
import com.pawject.service.exec.ExecInsert;
import com.pawject.service.exec.ExecList;
import com.pawject.service.exec.ExecUpdate;
import com.pawject.service.exec.ExecinfoService;

//@WebServlet("*.hsh")
public class Execinfo_Contoller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Execinfo_Contoller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doExecinfo(request, response);  //경로 확인!!
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doExecinfo(request, response);
	}

	protected void doExecinfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 각 경로 호출되는지 확인.
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String path = request.getServletPath();
		ExecinfoService service = null; //##
		
		if (path.equals("/regForm.hsh")) {  //등록폼
			request.getRequestDispatcher("/execboard/write.jsp").forward(request, response);
			
		} else if (path.equals("/reg.hsh")) { //등록기능
			service = new ExecInsert(); service.exec(request, response);
			String result = (String) request.getAttribute("result");
			if(result.equals("1")) {out.println("<script>alert ('등록했습니다.'); location.href='execAll.hsh';</script>");}
			else { out.println("<script>alert('관리자에게 문의해주세요.');history.go(-1); </script>");}
			
		} else if (path.equals("/execAll.hsh")) { //전체보기
			service = new ExecList(); service.exec(request, response);
			request.getRequestDispatcher("/execboard/list.jsp").forward(request, response);
			
		} else if (path.equals("/exec.hsh")) { //상세보기
			service = new ExecDetail(); service.exec(request, response);
			request.getRequestDispatcher("/execboard/detail.jsp").forward(request, response);

		} else if (path.equals("/updateForm.hsh")) { //수정폼
			service = new ExecDetail(); service.exec(request, response);
			request.getRequestDispatcher("/execboard/edit.jsp").forward(request, response);

		} else if (path.equals("/update.hsh")) { //수정기능
			service = new ExecUpdate(); service.exec(request, response);
			String result = (String) request.getAttribute("result");

			int id = Integer.parseInt(request.getParameter("id"));	
			if(result.equals("1")) {out.println("<script>alert ('수정했습니다.'); location.href='exec.hsh?id="+id+"';</script>");}
			else { out.println("<script>alert('관리자에게 문의해주세요.');history.go(-1); </script>");}
		
		} else if (path.equals("/delete.hsh")) { //삭제기능
			service = new ExecDelete(); service.exec(request, response);
			String result = (String) request.getAttribute("result");
			if(result.equals("1")) {out.println("<script>alert ('삭제했습니다.'); location.href='execAll.hsh';</script>");}		
			else { out.println("<script>alert('관리자에게 문의해주세요.');history.go(-1); </script>");}
		}
	}
}
//ㄴindex.jsp
//ㄴ  [등록폼]   /regForm.hsh               □ /execinfoboard/list.jsp   
//ㄴ  [등록기능]         /reg.hsh            ■ insert()   /execAll.hsh
//ㄴ  [전체보기]         /execAll.hsh        ■ selectAll()       /execinfoboard/list.jsp   
//ㄴ  [상세보기]         /exec.hsh           ■ select()          /execinfoboard/detail.jsp   
//ㄴ  [수정폼]          /updateForm.hsh      ■ updateForm()   /execinfoboard/edit.jsp   
//ㄴ  [수정기능]         /update.hsh          ■ update()    /exec.hsh  해당번호의 글이 바로 수정
//ㄴ  [삭제기능]         /delete.hsh          ■ delete()    /execAll.hsh     




