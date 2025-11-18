package com.pawject.service.review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pawject.model.review.ReDao;
import com.pawject.model.review.ReDto;

public class ReInsert implements ReService {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       String email = request.getParameter("email"); // 숨겨진 input으로 전달
	        String password = request.getParameter("password");
	        int brandid = Integer.parseInt(request.getParameter("brandid")); 
	        int foodid = Integer.parseInt(request.getParameter("foodid"));
	        int rating = Integer.parseInt(request.getParameter("rating"));
	        String title = request.getParameter("title");
	        String reviewcomment = request.getParameter("reviewcomment");
	        
			//드커프리
			ReDto dto = new ReDto();
			ReDao dao = new ReDao();
					
			dto.setEmail(email);
	        dto.setPassword(password);
	        dto.setBrandid(brandid);
	        dto.setFoodid(foodid);
	        dto.setRating(rating);
	        dto.setTitle(title);
	        dto.setReviewcomment(reviewcomment);
			
			String result = String.valueOf(dao.insert(dto));
			
			request.setAttribute("result", result);
		}

	}

