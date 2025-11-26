package com.pawject.service.food;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.pawject.model.food.*;

public class FoodUpdate implements FoodService {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int foodid = Integer.parseInt(request.getParameter("id"));
		
		String foodname = request.getParameter("foodname");
		String description = request.getParameter("description");
		String mainingredient = request.getParameter("mainingredient");
		String subingredient = request.getParameter("subingredient");
		int pettypeid = Integer.parseInt(request.getParameter("pettypeid"));
		String brandname = request.getParameter("brandname");
		String country = request.getParameter("country");
		
		

		FoodDao dao = new FoodDao();
		FoodDto dto = new FoodDto();
		
		dto.setFoodid(foodid);
		dto.setFoodname(foodname);
		dto.setDescription(description);
		dto.setMainingredient(mainingredient);
		dto.setSubingredient(subingredient);
		dto.setPettypeid(pettypeid);
		dto.setFoodid(foodid);
		dto.setBrandname(brandname);
		dto.setCountry(country);
		
		request.setAttribute("foodid", foodid);
		request.setAttribute("result", dao.update(dto));
		
	}

}
