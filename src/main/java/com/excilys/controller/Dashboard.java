package com.excilys.controller;

import com.excilys.mapper.ComputerMapperDTO;
import com.excilys.model.Computer;
import com.excilys.service.ComputerService;
import com.excilys.util.Page;
import com.excilys.util.Page.Sort;
import com.excilys.util.SimplePage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/dashboard")
public class Dashboard extends HttpServlet {

    private final ComputerMapperDTO computerMapperDTO = ComputerMapperDTO.INSTANCE;

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
            List<Computer> computers = ComputerService.INSTANCE.getByName(search);
            p.setTotalEntities(computers.size());
            request.setAttribute("computers", computerMapperDTO
                    .modelsToDto(computers));
            getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(
                    request, response);
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
        p.setTotalEntities(ComputerService.INSTANCE.count());
        int maxPages = (p.getTotalEntities() / p.getSize());
        if (p.getTotalEntities() % p.getSize() != 0) {
            ++maxPages;
        }
        p.setTotalPages(maxPages);
        p.setDisplayablePages(Math.min(maxPages, p.getPage() + p.getSize() - 1));
        request.setAttribute("page", p);
        request.setAttribute("computers", computerMapperDTO
                .modelsToDto(ComputerService.INSTANCE.getAll(p)));
        System.out.println(p.getProperties());
        getServletContext()
                .getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(
                request, response);
    }

}
