package com.excilys.controller;

import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.CompanyMapperDTO;
import com.excilys.mapper.ComputerMapperDTO;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/editComputer")
public class EditComputer extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(EditComputer.class);
    private final CompanyMapperDTO companyMapperDTO = CompanyMapperDTO.INSTANCE;
    private final ComputerMapperDTO computerMapperDTO = ComputerMapperDTO.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null) {
            id = id.trim();
            if (id.isEmpty()) {
                getServletContext().getRequestDispatcher(
                        "/WEB-INF/views/404.jsp").forward(request, response);
            } else {
                if (!id.matches("^[1-9][0-9]*$")) {
                    getServletContext().getRequestDispatcher(
                            "/WEB-INF/views/404.jsp")
                            .forward(request, response);
                }
                Long idComputer = Long.valueOf(id);
                request.setAttribute("computer", computerMapperDTO
                        .modelToDto(ComputerService.INSTANCE
                                .getById(idComputer)));
            }
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/views/404.jsp")
                    .forward(request, response);
        }
        request.setAttribute("companies",
                companyMapperDTO.modelsToDto(CompanyService.INSTANCE.getAll()));
        getServletContext().getRequestDispatcher(
                "/WEB-INF/views/editComputer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String introduced = req.getParameter("introduced");
        String discontinued = req.getParameter("discontinued");
        String companyId = req.getParameter("companyId");
        Long computerId = null;
        if (id != null) {
            id = id.trim();
            if (!id.isEmpty()) {
                computerId = Long.valueOf(id);
            } else {
                getServletContext().getRequestDispatcher(
                        "/WEB-INF/views/404.html").forward(req, resp);
            }
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/views/404.html")
                    .forward(req, resp);
        }
        if (name != null) {
            name = name.trim();
            if (name.isEmpty()) {
                LOGGER.error("Editing computer failed because of empty name");
                req.setAttribute("companies", companyMapperDTO
                        .modelsToDto(CompanyService.INSTANCE.getAll()));
                req.setAttribute("message", "Name is mandatory");
                getServletContext().getRequestDispatcher(
                        "/WEB-INF/views/editComputer.jsp").forward(req, resp);
            }
        } else {
            LOGGER.error("Editing computer failed because of null name");
            req.setAttribute("companies", companyMapperDTO
                    .modelsToDto(CompanyService.INSTANCE.getAll()));
            req.setAttribute("message", "Name is mandatory");
            getServletContext().getRequestDispatcher(
                    "/WEB-INF/views/editComputer.jsp").forward(req, resp);
        }
        final ComputerDTO dto = new ComputerDTO();
        if (companyId != null) {
            companyId = companyId.trim();
            if (!companyId.isEmpty() && companyId.matches("^[1-9][0-9]*$")) {
                final Company company = CompanyService.INSTANCE.getById(Long
                        .valueOf(companyId));
                dto.setCompanyId(companyId);
                dto.setCompanyName(company.getName());
            }
        }
        dto.setId(computerId);
        dto.setName(name);
        dto.setIntroduced(introduced);
        dto.setDiscontinued(discontinued);
        final Computer computer = computerMapperDTO.dtoToModel(dto);
        ComputerService.INSTANCE.update(computer);
        resp.sendRedirect("dashboard");
    }

}
