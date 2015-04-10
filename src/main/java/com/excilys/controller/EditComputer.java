package com.excilys.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.MapperDTO;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.ICompanyService;
import com.excilys.service.IComputerService;

@Controller
@RequestMapping("/editComputer")
public class EditComputer {
	private static final String PAGE_404 = "404";
	private static final String EDIT_VIEW = "editComputer";
	private static final String DASHBOARD_VIEW = "dashboard";
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
    
    
    @RequestMapping(method = RequestMethod.GET)
    public String index(@RequestParam("id") Optional<Long> id, Model model) {
    	if (id.isPresent()) {
    		if (id.get() <= 0) {
    			return PAGE_404;
    		} else {
    			model.addAttribute("computer", computerMapperDTO.modelToDto(
    					computerService.getById(id.get())));
    		}
    	} else {
    		return PAGE_404;
    	}
    	model.addAttribute("companies", 
    			companyMapperDTO.modelsToDto(companyService.getAll()));
    	
    	return EDIT_VIEW;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String editComputer(@RequestParam("id") Optional<Long> id,
    		@RequestParam("name") Optional<String> name, 
			@RequestParam("introduced") Optional<String> introduced,
			@RequestParam("discontinued") Optional<String> discontinued, 
			@RequestParam("companyId") Optional<Long> companyId,
			Model model) {
    	if (!id.isPresent()) {
    		LOGGER.error("Editing computer failed because of non-present id");
    		return PAGE_404;
        }
    	
        if (name.isPresent()) {
        	final String nam = name.get().trim();
            if (nam.isEmpty()) {
                LOGGER.error("Editing computer failed because of empty name");
                model.addAttribute("companies", companyMapperDTO
                        .modelsToDto(companyService.getAll()));
                model.addAttribute("message", "Name is mandatory");
                return EDIT_VIEW;
            }
        } else {
            LOGGER.error("Editing computer failed because of null name");
            model.addAttribute("companies", companyMapperDTO
                    .modelsToDto(companyService.getAll()));
            model.addAttribute("message", "Name is mandatory");
            return EDIT_VIEW;
        }
        final ComputerDTO dto = new ComputerDTO();
        if (companyId.isPresent()) {
                final Company company = companyService.getById(companyId.get());
                dto.setCompanyId(String.valueOf(companyId.get()));
                dto.setCompanyName(company.getName());
        }
        dto.setId(id.get());
        dto.setName(name.get());
        dto.setIntroduced(introduced.get());
        dto.setDiscontinued(discontinued.get());
        final Computer computer = computerMapperDTO.dtoToModel(dto);
        computerService.update(computer);
        LOGGER.info("Successfully updated computer with id {}",
                computer.getId());
        return "redirect:" + DASHBOARD_VIEW;
    }
}
