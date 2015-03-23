package com.excilys.controller;

import com.excilys.service.ComputerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditComputer extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id != null) {
			id = id.trim();
			if (!id.isEmpty()) {
				Long idComputer = Long.valueOf(id);
				request.setAttribute("computer", ComputerService.INSTANCE.getById(idComputer));
			}
		}
		getServletContext().getRequestDispatcher(
				"/WEB-INF/views/editComputer.jsp").forward(request, response);
	}

}
