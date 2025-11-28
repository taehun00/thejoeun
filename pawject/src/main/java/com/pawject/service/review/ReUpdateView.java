package com.pawject.service.review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pawject.model.review.ReDao;

public class ReUpdateView implements ReService {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//데이터 받고
		
		int reviewid=Integer.parseInt(request.getParameter("reviewid"));
		
		ReDao dao = new ReDao();
		FoodList foodlist = new FoodList();
		foodlist.exec(request, response);
		
		
		request.setAttribute("dto", dao.select(reviewid));
		request.setAttribute("reviewid", reviewid);

		

	}

}
