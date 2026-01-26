package com.pawject.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pawject.service.food.*;



//@WebServlet("*.jys")
public class FoodController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FoodController() { super(); }

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
		System.out.println(path);
		
		FoodService service = null; 
		

		if(	path.equals("/list.jys")) { 		// 리스트 

			service = new FoodList();	service.exec(request, response);
			request.getRequestDispatcher("foodboard/list.jsp").forward(request, response);

			 
		}else if(	path.equals("/writeView.jys")){	

			request.getRequestDispatcher("foodboard/write.jsp").forward(request, response);
		}
		else if(	path.equals("/write.jys")){ 

			service = new FoodInsert();	service.exec(request, response);

			String result = (String)request.getAttribute("result");
			if(result.equals("1")) {
				out.println("<script>alert('글쓰기에 성공했습니다.'); location.href='list.jys' </script>");
			}
			else {
				out.println("<script>alert('관리자에게 문의바랍니다.'); location.href='list.jys' </script>");
			}
		} else if(	path.equals("/detail.jys")){

			service = new FoodDetail();	service.exec(request, response);
			request.getRequestDispatcher("foodboard/detail.jsp").forward(request, response);
			
		}else if(	path.equals("/editView.jys")){

			service = new FoodUpdateView();	service.exec(request, response);
			request.getRequestDispatcher("foodboard/edit.jsp").forward(request, response);
		
		}else if(	path.equals("/edit.jys")){

			service = new FoodUpdate();	service.exec(request, response);
			
			
			int foodid = Integer.parseInt(request.getParameter("id"));	
			
			Integer resultInt = (Integer) request.getAttribute("result");
			String result = String.valueOf(resultInt);

			if(resultInt != null && resultInt == 1) {
			    out.println("<script>alert('글수정에 성공했습니다.'); location.href='detail.jys?id=" + foodid + "';</script>");
			} else {
			    out.println("<script>alert('수정에 실패했습니다. 관리자에게 문의해주세요.'); history.go(-1);</script>");
			}
		////////////////////////////////////////////////////////////////////////////////////////////
		}else if(path.equals("/deleteView.jys")) {
		    String foodid = request.getParameter("foodid");
		    request.setAttribute("foodid", foodid);
		    request.getRequestDispatcher("foodboard/delete.jsp").forward(request, response);
		}

		else if(path.equals("/delete.jys")) {
		    service = new FoodDelete();
		    service.exec(request, response);
		    String result = (String) request.getAttribute("result");

		    if(result.equals("1")) {
		        out.println("<script>alert('글삭제에 성공했습니다.'); location.href='list.jys'</script>");
		    } else {
		        out.println("<script>alert('비밀번호를 확인해주세요'); history.go(-1);</script>");
		    }
		}
	}
}
