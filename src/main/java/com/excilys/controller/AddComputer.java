package com.excilys.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.MapperDTO;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.ICompanyService;
import com.excilys.service.IComputerService;

@Controller
@RequestMapping("/addComputer")
public class AddComputer {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(AddComputer.class);
    private static final String ADDCOMPUTER_VIEW = "addComputer";
    private static final String DASHBOARD_VIEW = "dashboard";
    @Autowired
    private MapperDTO<Company, CompanyDTO> companyMapperDTO;
    @Autowired
    private MapperDTO<Computer, ComputerDTO> computerMapperDTO;
    @Autowired
    private IComputerService computerService;
    @Autowired
    private ICompanyService companyService;

	@RequestMapping(method = RequestMethod.GET)
	public String index(@ModelAttribute("newComputer") ComputerDTO computerDTO, Model model) {
		model.addAttribute("companies", 
				companyMapperDTO.modelsToDto(companyService.getAll()));
		return ADDCOMPUTER_VIEW;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String addComputer(@Valid @ModelAttribute("newComputer") ComputerDTO computerDTO,
			BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return ADDCOMPUTER_VIEW;
        }
//		if (name.isPresent()) {
//			final String nme = name.get().trim();
//            if (nme.isEmpty()) {
//                LOGGER.error("Adding computer failed because of empty name");
//                model.addAttribute("companies", 
//                		companyMapperDTO.modelsToDto(companyService.getAll()));
//                model.addAttribute("message", "Name is mandatory");
//                return ADDCOMPUTER_VIEW;
//            }
//        } else {
//            LOGGER.error("Adding computer failed because of null name");
//            model.addAttribute("companies", 
//            		companyMapperDTO.modelsToDto(companyService.getAll()));
//            model.addAttribute("message", "Name is mandatory");
//            return ADDCOMPUTER_VIEW;
//        }
//        final ComputerDTO dto = new ComputerDTO();
//        if (companyId.isPresent()) {
//        	final Company company = companyService.getById(companyId.get());
//        	dto.setCompanyId(String.valueOf(companyId.get()));
//        	dto.setCompanyName(company.getName());
//        }
//        dto.setName(name.get());
//        dto.setIntroduced(introduced.get());
//        dto.setDiscontinued(discontinued.get());
//        final Computer computer = computerMapperDTO.dtoToModel(dto);
//        computerService.create(computer);
//        LOGGER.info("Successfully created computer with id {}",
//                computer.getId());
        return "redirect:" + DASHBOARD_VIEW;
	}
}
