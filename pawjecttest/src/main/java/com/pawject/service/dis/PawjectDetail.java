package com.pawject.service.dis;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pawject.model.dis.PawPostDao;
import com.pawject.model.dis.PawPostDto;

public class PawjectDetail implements PawjectService {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		int id = Integer.parseInt(request.getParameter("id"));
		

		PawPostDao dao = new PawPostDao();
		// dao.update_hit(id); // 조회수 올리기
		dao.select(id); // 해당번호의 값 가져오기  
		PawPostDto result = dao.select(id);
		// 3. 데이터 넘겨주기
		request.setAttribute("dto", result);
		
		
	}

}
