package com.pawject.service.exec;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pawject.model.exec.ExecDao;
import com.pawject.model.exec.ExecDto;

public class ExecDetail implements ExecinfoService {
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 데이터넘겨받기 (ID)
		int id = Integer.parseInt(request.getParameter("id"));
		//2. 디커프리( PostDao ) db처리
		ExecDao dao = new ExecDao();
		dao.select(id);
		ExecDto result = dao.select(id); //해당번호로 값 가져오기.
		//3. 데이터 넘겨주기
		request.setAttribute("dto", result);
		
  		//select * from exerciseinfo  where  execid=?
 	
	}
}
