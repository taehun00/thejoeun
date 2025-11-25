<<<<<<<< HEAD:pawjecttest/src/main/java/com/pawject/service/pet/PetDelete.java
package com.pawject.service.pet;
========
package com.pawject.service;
>>>>>>>> 961999066a4a0c02d9d83aedc3f720f8b5947f2c:pawject1/src/main/java/com/pawject/service/ReList.java

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pawject.model.ReDao;

public class ReList implements ReService {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		ReDao dao = new ReDao();
		request.setAttribute("list", dao.selectAll());
		
	}

}