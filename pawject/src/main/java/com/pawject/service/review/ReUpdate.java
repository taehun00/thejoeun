package com.pawject.service.review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pawject.model.review.ReDao;
import com.pawject.model.review.ReDto;

public class ReUpdate implements ReService {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
//		  update review set brandid=?, foodid=?, rating=?, title=?, reviewcomment=? where reviewid=? and userid=? and password='?;
		//ㄴ이 데이터 넘겨받기
		int reviewid=Integer.parseInt(request.getParameter("reviewid"));
		int brandid=Integer.parseInt(request.getParameter("brandid"));
		int foodid=Integer.parseInt(request.getParameter("foodid"));
		int rating=Integer.parseInt(request.getParameter("rating"));
		String title = request.getParameter("title");
		String reviewcomment = request.getParameter("reviewcomment");
		int userid=Integer.parseInt(request.getParameter("userid"));
		String password = request.getParameter("password");
		
		//드커프리 여기서 했음
		ReDao dao = new ReDao();
		ReDto dto = new ReDto();
		
		dto.setReviewid(reviewid);
		dto.setBrandid(brandid);
		dto.setFoodid(foodid);
		dto.setRating(rating);
		dto.setTitle(title);
		dto.setReviewcomment(reviewcomment);
		dto.setUserid(userid);
		dto.setPassword(password);
		
		//넘기기 스트링 바꿔야댐
		request.setAttribute("result", String.valueOf(dao.update(dto)));
		
	}

}
