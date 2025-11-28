package com.pawject.service.review;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ReService {
		public void exec(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException;
	}