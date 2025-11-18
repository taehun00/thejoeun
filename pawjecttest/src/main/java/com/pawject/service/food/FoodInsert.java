package com.pawject.service.food;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.pawject.model.food.*;

public class FoodInsert implements FoodService {
	
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    HttpSession session = request.getSession();

	    String foodname = request.getParameter("foodname");
	    String description = request.getParameter("description");
	    String mainingredient = request.getParameter("mainingredient");
	    String subingredient = request.getParameter("subingredient");
	    String brandname = request.getParameter("brandname"); 
	    String country = request.getParameter("country"); 


	    int pettypeid = Integer.parseInt(request.getParameter("pettypeid"));



	    FoodDto dto = new FoodDto();
	    dto.setFoodname(foodname);
	    dto.setDescription(description);

	    dto.setBrandname(brandname);
	    dto.setCountry(country);
	    dto.setMainingredient(mainingredient);
	    dto.setSubingredient(subingredient);
	    dto.setPettypeid(pettypeid);

	    FoodDao dao = new FoodDao();
	    String result = String.valueOf(dao.foodinsert(dto));
	    request.setAttribute("result", result);
	
	};
	}
