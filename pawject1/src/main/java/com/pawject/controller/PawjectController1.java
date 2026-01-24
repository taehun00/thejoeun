package com.pawject.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pawject.service.dis.PawjectDelete;
import com.pawject.service.dis.PawjectDetail;
import com.pawject.service.dis.PawjectInsert;
import com.pawject.service.dis.PawjectList;
import com.pawject.service.dis.PawjectService;
import com.pawject.service.dis.PawjectUpdate;
import com.pawject.service.dis.PawjectUpdateView;



//@WebServlet("*.swc")
public class PawjectController1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public PawjectController1() { super();  }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doAction(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		doAction(request, response);
	}
	protected void doAction(HttpServletRequest request, HttpServletResponse response) 
	    throws ServletException, IOException {
	
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			String path = request.getServletPath();
			
			System.out.println(path);
			PawjectService service = null;
			
			if(path.equals("/Pawjectlist.swc")) {
				service = new PawjectList(); service.exec(request, response);
				request.getRequestDispatcher("DisBoard/list.jsp").forward(request, response);	
				//1.[전체글보기] /Pawjectlist.swc  ->     ■  PawjectList ->   DisBoard/list.jsp
				
			}else if (path.equals("/PawjectwriteView.swc")) {
				request.getRequestDispatcher("DisBoard/Write.jsp").forward(request, response);
				//2.[글쓰기폼  ] /PawjectwriteView.swc   ->  □           ->   DisBoard/write.jsp
				
			}else if (path.equals("/Pawjectwrite.swc")) {  		    
				service = new PawjectInsert(); service.exec(request, response);
				 // 글쓰기 수정 삽입
				//3. [글쓰기기능]  /Pawjectwrite.swc   ->   ■  PawjectInsert  -> 알림창 +  Pawjectlist.swc 
				String result = (String)request.getAttribute("result");
				
				if(result.equals("1")) {
					out.println("<script>alert('글쓰기(submit)에 성공했습니다. ');location.href='Pawjectlist.swc';</script>");
				}else {
				out.println("<script>alert('관리자에게 문의 바랍니다. '); history.go(-1);</script>");
				System.out.println("글쓰기 결과: " +  result);
				System.out.println("insert 실행됨");
				}
			
			}else if (path.equals("/Pawjectdetail.swc")) {
				service = new PawjectDetail(); service.exec(request, response);
				request.getRequestDispatcher("DisBoard/Detail.jsp").forward(request, response);
				//4. [상세보기 ]   /Pawjectdetail.swc     ■  PawjectDetail       /          DisBoard/detail.jsp 
				
				
			}else if (path.equals("/PawjecteditView.swc")) {
				
				service = new PawjectUpdateView(); service.exec(request, response);
				request.getRequestDispatcher("DisBoard/Edit.jsp").forward(request, response);
				//5. [글수정폼  ]  /PawjecteditView.swc  ■  PawjectUpdateView   /          DisBoard/edit.jsp 
				
				
			}else if (path.equals("/Pawjectedit.swc")) {
				
				service= new PawjectUpdate();  service.exec(request, response);
				
				//6. [글수정기능]   /Pawjectedit.swc       ■  PawjectUpdate      /  알림창 +  DisBoard/detail.jsp
				
				int id = Integer.parseInt( request.getParameter("id"));
				String result = (String) request.getAttribute("result");
				System.out.println("결과확인" + result);
		         if(result.equals("1")) {
		            out.println("<script>alert('수정 완료'); location.href='Pawjectdetail.swc?id="+ id +"';</script>");   
		         }else {
		            out.println("<script>alert('관리자에게 문의 해주세요'); history.go(-1);</script>");
		         }
				//////////////////////////////////////////////////////////////////////////////////////
			
				
			}else if (path.equals("/Pawjectdelete.swc")) {
				service = new PawjectDelete(); service.exec(request, response);
				//7. [글삭제기능]   /Pawjectdelete.swc      ■  PawjectDelete      /  알림창 +  Pawjectlist.swc
				
				int id = Integer.parseInt( request.getParameter("id"));
				String result = (String) request.getAttribute("result");
				System.out.println("결과확인" + result);
				if(result.equals("1")) {
					out.println("<script>alert('글삭제에 성공했습니다. ');location.href='Pawjectlist.swc?id="+ id +"';</script>");
				}else {

				out.println("<script>alert('글삭제 실패 했습니다. ');location.href='Pawjectlist.swc';</script>");
				}
			}
		}

	}


/*
 ㄴ index.jsp
            ㄴ 1. [전체글보기] /Pawjectlist.swc         ■  PawjectList          /          DisBoard/list.jsp
            ㄴ 2. [글쓰기폼  ] /PawjectwriteView.swc   □                    /         DisBoard/write.jsp
            ㄴ 3. [글쓰기기능]  /Pawjectwrite.swc     ■  PawjectInsert        / 알림창 +  Pawjectlist.swc 
            ㄴ 4. [상세보기 ]   /Pawjectdetail.swc     ■  PawjectDetail       /          DisBoard/detail.jsp 
            ㄴ 5. [글수정폼  ]  /PawjecteditView.swc  ■  PawjectUpdateView   /          DisBoard/edit.jsp  
            ㄴ 6. [글수정기능]   /Pawjectedit.swc       ■  PawjectUpdate      /  알림창 +  DisBoard/detail.jsp 
            ㄴ 7. [글삭제기능]   /Pawjectdelete.swc      ■  PawjectDelete      /  알림창 +  Pawjectlist.swc
* 
 * 
 * 
 
 */
