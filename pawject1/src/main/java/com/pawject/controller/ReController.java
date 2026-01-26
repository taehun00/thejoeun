package com.pawject.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.pawject.service.review.*;



/**
 * Servlet implementation class PetController
 */
//@WebServlet("*.rv")
public class ReController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	@Override
	public void init() throws ServletException {

	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReController() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doActon(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doActon(request, response);
	}
	
	protected void doActon(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String path=request.getServletPath();
		PrintWriter out = response.getWriter();
		out.println(path);
		ReService service = null; 
		
		if(path.equals("/list.rv")) {
		//	ㄴ[전체글보기] list.do           ■ ReList 불러오고 /  ReBoard/list.jsp로 넘김
			service = new ReList();  service.exec(request, response);
			request.getRequestDispatcher("/reviewboard/list.jsp").forward(request, response);
			
		}else if(path.equals("/writeView.rv")){	
		//ㄴ[글쓰기폼]   /writeView.do      □ 기능X 단순 폼 소환 / ReBoard/write.jsp	
			service =  new FoodList(); service.exec(request, response);
			request.getRequestDispatcher("/reviewboard/write.jsp").forward(request, response);

				

		//	ㄴ[글쓰기기능] /write.do          ■ ReInsert         /  list.do 
		}else if(path.equals("/write.rv")){
		    service = new ReInsert(); 
		    service.exec(request, response);

		    String result = (String)request.getAttribute("result");

		    response.setContentType("text/html; charset=UTF-8");

		    if("1".equals(result)) {
		        out.println("<script>alert('글쓰기에 성공했습니다.'); location.href='list.rv';</script>");
		    } else {
		        out.println("<script>alert('관리자에게 문의바랍니다.'); location.href='list.rv';</script>");
		    }

		    out.flush();
		    out.close();
		    
		}else if(path.equals("/editView.rv")){
		//ㄴ[글수정폼]   /editView.do       ■ ReUpdateView   /  ReBoard/edit.jsp	
			service = new FoodList(); 	service.exec(request, response);
			service = new ReUpdateView();		service.exec(request, response);
			
			request.getRequestDispatcher("/reviewboard/edit.jsp").forward(request, response);
			
		}else if(path.equals("/edit.rv")){
		// ㄴ[글수정기능] /edit.do           ■ ReUpdate       /  알림창 + ReBoard/detail.jsp 
			service= new ReUpdate();  service.exec(request, response);
			String result = (String)request.getAttribute("result");
			int reviewid = Integer.parseInt(request.getParameter("reviewid"));
			
		    if("1".equals(result)) {
		        out.println("<script>alert('수정 완료'); location.href='list.rv';</script>");
		    } else {
		        out.println("<script>alert('비밀번호를 확인해 주세요'); history.go(-1);</script>");
		    }

			
		    
		    
		}else if(path.equals("/deleteView.rv")){
			service = new ReDeleteView(); service.exec(request, response);
			request.getRequestDispatcher("/reviewboard/delete.jsp").forward(request, response); 
			

		}else if(path.equals("/delete.rv")){
		//ㄴ[글삭제기능] /delete.do         ■ ReDelete       /  알림창 + list.do 
			
			service = new ReDelete(); service.exec(request, response);
			String result = (String)request.getAttribute("result");
			int reviewid = Integer.parseInt(request.getParameter("reviewid"));
			
		    if("1".equals(result)) {
		        out.println("<script>alert('삭제 완료'); location.href='list.rv';</script>");
		    } else {
		        out.println("<script>alert('비밀번호를 확인해 주세요'); history.go(-1);</script>");
		    }
			
		}
		
		
		
	
	}

}


/*
 
 
 
             ㄴindex.jsp
                ㄴ[전체글보기]/ReBoard/list.jsp (or login.jsp 등등 가능)
                            >/list.do           ■ ReList 불러오고 /  ReBoard/list.jsp로 넘김
                ㄴ[글쓰기폼]   /writeView.do      □ 기능X 단순 폼 소환 / ReBoard/write.jsp
                ㄴ[글쓰기기능] /write.do          ■ ReInsert         /  list.do 
                ㄴ[글수정폼]   /editView.do       ■ ReUpdateView   /  ReBoard/edit.jsp 
                ㄴ[글수정기능] /edit.do           ■ ReUpdate       /  알림창 + ReBoard/detail.jsp 
                ㄴ[글삭제기능] /delete.do         ■ ReDelete       /  알림창 + list.do 


 */
 