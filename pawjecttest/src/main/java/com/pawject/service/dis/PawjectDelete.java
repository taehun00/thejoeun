package com.pawject.service.dis;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pawject.model.dis.PawPostDao;
import com.pawject.model.dis.PawPostDto;


public class PawjectDelete implements PawjectService {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
		int id = Integer.parseInt( request.getParameter("id"));
		  
		  PawPostDao dao = new PawPostDao();
	      PawPostDto dto = new PawPostDto();
	      
	      dto.setDisno(id);
	     
			
			request.setAttribute("result", String.valueOf(dao.delete(dto)));
		
		
		
	}

}
