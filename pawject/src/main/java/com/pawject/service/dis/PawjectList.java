package com.pawject.service.dis;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pawject.model.dis.PawPostDao;

public class PawjectList implements PawjectService {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		PawPostDao dao = new PawPostDao();
		
		//3. 데이터 넘겨주기
		request.setAttribute("PawTestList1", dao.selectAll());

	}

}
