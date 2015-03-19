package com.excilys.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.service.ComputerService;

/**
 * Servlet implementation class Test
 */
public class Dashboard extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("computers", ComputerService.INSTANCE.getAll());
		getServletContext()
				.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(
						request, response);
	}

}
