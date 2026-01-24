package com.pawject.service.exec;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pawject.model.exec.ExecDao;
import com.pawject.model.exec.ExecDto;

public class ExecUpdate implements ExecinfoService {
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//1. 데이터넘겨받고
		String type = request.getParameter("exectype");
	    String desc = request.getParameter("description");
        Float avg =  Float.parseFloat(request.getParameter("avgkcal30min"));
        Integer target = Integer.parseInt(request.getParameter("exectargetmin"));
        String suit = request.getParameter("suitablefor");
        String level = request.getParameter("intensitylevel");
		int id = Integer.parseInt(request.getParameter("id"));		
		
		System.out.println(".......1: " + id);
		//2. 디커프리( PostDao ) db처리
		ExecDao dao = new ExecDao();
		ExecDto dto = new ExecDto();
		dto.setExectype(type);
		dto.setDescription(desc);
		dto.setAvgkcal30min(avg);
		dto.setExectargetmin(target);
		dto.setSuitablefor(suit);
		dto.setIntensitylevel(level);
		dto.setExecid(id);
		//3. 데이터 넘겨주기
		request.setAttribute("id", id);
		request.setAttribute("result", String.valueOf(dao.update(dto)) );
	}
}
