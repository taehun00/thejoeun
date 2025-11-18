package com.pawject.service.review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pawject.model.review.ReDao;
import com.pawject.model.review.ReDto;

public class ReDelete implements ReService {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		int reviewid=Integer.parseInt(request.getParameter("reviewid"));
		int userid=Integer.parseInt(request.getParameter("userid"));
		String password = request.getParameter("password");

		ReDto dto = new ReDto();
		ReDao dao = new ReDao();
		
		dto.setReviewid(reviewid);
		dto.setUserid(userid);
		dto.setPassword(password);
		
//		dto.getReviewid();
//		dto.getUserid();
//		dto.getPassword();
//		?이걸왜씀걍바보인듯 
		request.setAttribute("result", String.valueOf(dao.delete(dto)));
		
		
		

	}

}
