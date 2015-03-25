package com.excilys.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.mapper.ComputerMapperDTO;
import com.excilys.service.ComputerService;
import com.excilys.util.Page;
import com.excilys.util.SimplePage;

@WebServlet(urlPatterns = "/dashboard")
public class Dashboard extends HttpServlet {

	private final ComputerMapperDTO computerMapperDTO = ComputerMapperDTO.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		String size = request.getParameter("size");
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
		final Page p = new SimplePage(currentPage, entitiesByPage);
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
		request.setAttribute("computers", computerMapperDTO
				.modelsToDto(ComputerService.INSTANCE.getAll(p)));
		request.setAttribute("currentPage", pge);
		request.setAttribute("total", totalEntities);
		getServletContext()
				.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(
						request, response);
	}

}
