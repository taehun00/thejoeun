package com.pawject.service.food;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.pawject.model.food.*;

public class FoodList implements FoodService {
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		FoodDao dao = new FoodDao();
		request.setAttribute("list", dao.selectAll());
		
	}

}
