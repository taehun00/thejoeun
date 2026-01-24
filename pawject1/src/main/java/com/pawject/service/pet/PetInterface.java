package com.pawject.service.pet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PetInterface {
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	
}
