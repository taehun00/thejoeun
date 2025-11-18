package com.pawject.service.dis;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pawject.model.dis.PawPostDao;
import com.pawject.model.dis.PawPostDto;

public class PawjectUpdate implements PawjectService {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String disname = request.getParameter("disname");
		String disex = request.getParameter("disex");
		String kindpet = request.getParameter("kindpet");
		String infval = request.getParameter("infval");
		String mannote = request.getParameter("mannote");
		
		int id = Integer.parseInt( request.getParameter("id"));
		
		System.out.println(".............................1" + id);
		//2. 디커프리 (PostDao) db 처리
		PawPostDao dao = new PawPostDao();
		PawPostDto dto = new PawPostDto();
		
		dto.setDisname(disname);
		dto.setDisex(disex);
		dto.setKindpet(kindpet);
		dto.setInfval(infval);
		dto.setMannote(mannote);
		dto.setDisno(id);
	      
		
		
		System.out.println(".............................2" + dto);
		//request.setAttribute("id", dto);
		request.setAttribute("result", String.valueOf(dao.update(dto)));

	}

}
