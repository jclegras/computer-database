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

@WebServlet(urlPatterns = "/addComputer")
public class AddComputer extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(AddComputer.class);
    private static final String DASHBOARD_VIEW = "/WEB-INF/views/addComputer.jsp";
    private static final String DASHBOARD_CTRL = "dashboard";
    private final CompanyMapperDTO companyMapperDTO = CompanyMapperDTO.INSTANCE;
    private final ComputerMapperDTO computerMapperDTO = ComputerMapperDTO.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("companies",
                companyMapperDTO.modelsToDto(CompanyService.INSTANCE.getAll()));
        getServletContext().getRequestDispatcher(DASHBOARD_VIEW).forward(
                request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String introduced = req.getParameter("introduced");
        String discontinued = req.getParameter("discontinued");
        String companyId = req.getParameter("companyId");
        if (name != null) {
            name = name.trim();
            if (name.isEmpty()) {
                LOGGER.error("Adding computer failed because of empty name");
                req.setAttribute("companies", companyMapperDTO
                        .modelsToDto(CompanyService.INSTANCE.getAll()));
                req.setAttribute("message", "Name is mandatory");
                getServletContext().getRequestDispatcher(DASHBOARD_VIEW)
                        .forward(req, resp);
            }
        } else {
            LOGGER.error("Adding computer failed because of null name");
            req.setAttribute("companies", companyMapperDTO
                    .modelsToDto(CompanyService.INSTANCE.getAll()));
            req.setAttribute("message", "Name is mandatory");
            getServletContext().getRequestDispatcher(DASHBOARD_VIEW).forward(
                    req, resp);
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
        dto.setName(name);
        dto.setIntroduced(introduced);
        dto.setDiscontinued(discontinued);
        final Computer computer = computerMapperDTO.dtoToModel(dto);
        ComputerService.INSTANCE.create(computer);
        LOGGER.info("Successfully created computer with id {}",
                computer.getId());
        resp.sendRedirect(DASHBOARD_CTRL);
    }

}
