package com.pawject.service.pet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pawject.model.pet.PetDao;
import com.pawject.model.pet.PetDto;

public class PetInfoService implements PetInterface {

	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		int userid = Integer.parseInt(request.getParameter("userid"));
		
		PetDao dao = new PetDao();
		List<PetDto> dtoList = dao.getPetsByUserId(userid);

		
		request.setAttribute("dtoList", dtoList);
	}

}
