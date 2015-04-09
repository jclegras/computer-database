package com.excilys.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.MapperDTO;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.ICompanyService;
import com.excilys.service.IComputerService;

@WebServlet(urlPatterns = "/editComputer")
public class EditComputer extends AbstractServlet {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(EditComputer.class);
    @Autowired
    private MapperDTO<Company, CompanyDTO> companyMapperDTO;
    @Autowired
    private MapperDTO<Computer, ComputerDTO> computerMapperDTO;
    @Autowired
    private IComputerService computerService;
    @Autowired
    private ICompanyService companyService;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null) {
            id = id.trim();
            if (id.isEmpty()) {
                getServletContext().getRequestDispatcher(
                        "/WEB-INF/views/404.jsp").forward(request, response);
                return;
            } else {
                if (!id.matches("^[1-9][0-9]*$")) {
                    getServletContext().getRequestDispatcher(
                            "/WEB-INF/views/404.jsp")
                            .forward(request, response);
                }
                Long idComputer = Long.valueOf(id);
                request.setAttribute("computer", computerMapperDTO
                		.modelToDto(computerService
                                .getById(idComputer)));
            }
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/views/404.jsp")
                    .forward(request, response);
        }
        request.setAttribute("companies",
                companyMapperDTO.modelsToDto(companyService.getAll()));
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
                return;
            }
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/views/404.html")
                    .forward(req, resp);
            return;
        }
        if (name != null) {
            name = name.trim();
            if (name.isEmpty()) {
                LOGGER.error("Editing computer failed because of empty name");
                req.setAttribute("companies", companyMapperDTO
                        .modelsToDto(companyService.getAll()));
                req.setAttribute("message", "Name is mandatory");
                getServletContext().getRequestDispatcher(
                        "/WEB-INF/views/editComputer.jsp").forward(req, resp);
                return;
            }
        } else {
            LOGGER.error("Editing computer failed because of null name");
            req.setAttribute("companies", companyMapperDTO
                    .modelsToDto(companyService.getAll()));
            req.setAttribute("message", "Name is mandatory");
            getServletContext().getRequestDispatcher(
                    "/WEB-INF/views/editComputer.jsp").forward(req, resp);
            return;
        }
        final ComputerDTO dto = new ComputerDTO();
        if (companyId != null) {
            companyId = companyId.trim();
            if (!companyId.isEmpty() && companyId.matches("^[1-9][0-9]*$")) {
                final Company company = companyService.getById(Long
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
        computerService.update(computer);
        resp.sendRedirect("dashboard");
    }

}
