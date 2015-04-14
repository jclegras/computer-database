package com.excilys.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.MapperDTO;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.ICompanyService;
import com.excilys.service.IComputerService;

@Controller
@RequestMapping("/addComputer")
@SessionAttributes("companies")
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
	
    @ModelAttribute("companies")
    public List<CompanyDTO> populateModelWithCompanies() {
        return companyMapperDTO.modelsToDto(companyService.getAll());
    }

	@RequestMapping(method = RequestMethod.GET)
	public String index(@ModelAttribute("newComputer") ComputerDTO computerDTO,
			Model model) {
		return ADDCOMPUTER_VIEW;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String addComputer(
			@Valid @ModelAttribute("newComputer") ComputerDTO computerDTO,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return ADDCOMPUTER_VIEW;
		}
		if (computerDTO.getName() == null) {
			LOGGER.error("Adding computer failed because of null name");
			model.addAttribute("message", "Name is mandatory");
			return ADDCOMPUTER_VIEW;
		}
		if (computerDTO.getCompanyId() != null) {
			computerDTO.setCompanyName(companyService.getById(
					Long.valueOf(computerDTO.getCompanyId())).getName());
		}
		final Computer newComputer = computerMapperDTO.dtoToModel(computerDTO);
		computerService.create(newComputer);
		LOGGER.info("Successfully created computer with id {}",
				newComputer.getId());
		return "redirect:" + DASHBOARD_VIEW;
	}
}
