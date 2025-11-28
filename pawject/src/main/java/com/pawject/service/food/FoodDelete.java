package com.pawject.service.food;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.pawject.model.food.*;


public class FoodDelete implements FoodService {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int foodid  = Integer.parseInt(request.getParameter("foodid"));

		FoodDao dao = new FoodDao();
		FoodDto dto = new FoodDto();

		dto.setFoodid(foodid);
		
		request.setAttribute("result", String.valueOf(dao.delete(dto)));
		

	}

}
