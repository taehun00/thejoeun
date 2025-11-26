package com.pawject.service.food;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.pawject.model.food.*;

public class FoodUpdateView implements FoodService {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int foodid = Integer.parseInt(request.getParameter("id"));
		FoodDao dao = new FoodDao();
		request.setAttribute("dto", dao.select(foodid));
		
	}

}
