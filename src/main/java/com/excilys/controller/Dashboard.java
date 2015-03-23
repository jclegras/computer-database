package com.excilys.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.service.ComputerService;
import com.excilys.util.Page;
import com.excilys.util.SimplePage;

public class Dashboard extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		String size = request.getParameter("size");
		Page p = null;
		int currentPage = 1, entitiesByPage = 10, pge = 1;
		if (page != null) {
			page = page.trim();
			if (!page.isEmpty()) {
				currentPage = Integer.valueOf(page);
				pge = currentPage;
			}
		}
		if (size != null) {
			size = size.trim();
			if (!size.isEmpty()) {
				entitiesByPage = Integer.valueOf(size);
			}
		}
		p = new SimplePage(currentPage, entitiesByPage);
		final int totalEntities = ComputerService.INSTANCE.count();
		int maxPages = (totalEntities / entitiesByPage);
		if (totalEntities % entitiesByPage != 0) {
			++maxPages;
		}
		request.setAttribute("totalPages", maxPages);
		maxPages = Math.min(maxPages, pge + entitiesByPage - 1);
		request.setAttribute("page", p);
		request.setAttribute("sizePage", entitiesByPage);
		request.setAttribute("maxPages", maxPages);
		System.out.println(ComputerService.INSTANCE.getAll(p));
		request.setAttribute("computers", ComputerService.INSTANCE.getAll(p));
		request.setAttribute("currentPage", pge);
		request.setAttribute("total", totalEntities);
		getServletContext()
				.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(
						request, response);
	}

}
