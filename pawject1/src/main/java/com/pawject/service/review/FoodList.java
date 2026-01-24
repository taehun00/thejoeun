package com.pawject.service.review;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pawject.model.food.FoodDao;
import com.pawject.model.food.FoodDto;

public class FoodList implements ReService {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		FoodDao dao = new FoodDao();
        ArrayList<FoodDto> foodlist = dao.FoodList();
        ArrayList<FoodDto> brandlist = dao.BrandList();
        request.setAttribute("foodlist", foodlist);
        request.setAttribute("brandlist", brandlist);

        request.getRequestDispatcher("/reviewboard/write.jsp");

	}

}
