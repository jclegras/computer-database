package com.excilys.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.excilys.mapper.ComputerMapperDTO;
import com.excilys.model.Computer;
import com.excilys.service.ComputerService;
import com.excilys.util.Page;
import com.excilys.util.Page.Sort;
import com.excilys.util.SimplePage;

@Controller
@WebServlet(urlPatterns = "/dashboard")
public class Dashboard extends AbstractServlet {

	@Autowired
    private ComputerMapperDTO computerMapperDTO;
    @Autowired
    private ComputerService computerService;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        String size = request.getParameter("size");
        String search = request.getParameter("search");
        String sort = request.getParameter("sort");
        String column = request.getParameter("col");
        final Page p = new SimplePage("computer.name");
        if (search != null) {
            search = search.trim();
            List<Computer> computers = computerService.getByName(search);
            p.setTotalEntities(computers.size());
            request.setAttribute("computers", computerMapperDTO
                    .modelsToDto(computers));
            getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(
                    request, response);
            return;
        }
        if (page != null) {
            page = page.trim();
            if (!page.isEmpty()) {
                p.setPage(Integer.valueOf(page));
            }
        }
        if (size != null) {
            size = size.trim();
            if (!size.isEmpty()) {
                p.setSize(Integer.valueOf(size));
            }
        }
        if (sort != null) {
            sort = sort.trim();
            if (!sort.isEmpty()) {
                if (Page.Sort.isValid(sort)) {
                    p.setSort(Sort.valueOf(sort));
                }
            }
        }
        if (column != null) {
            column = column.trim();
            if (!column.isEmpty()) {
                p.setProperties(column);
            }
        }
        p.setTotalEntities(computerService.count());
        int maxPages = (p.getTotalEntities() / p.getSize());
        if (p.getTotalEntities() % p.getSize() != 0) {
            ++maxPages;
        }
        p.setTotalPages(maxPages);
        p.setDisplayablePages(Math.min(maxPages, p.getPage() + p.getSize() - 1));
        request.setAttribute("page", p);
        request.setAttribute("computers", computerMapperDTO
                .modelsToDto(computerService.getAll(p)));
        getServletContext()
                .getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(
                request, response);
    }

}
