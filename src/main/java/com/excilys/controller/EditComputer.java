package com.excilys.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.MapperDTO;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.ICompanyService;
import com.excilys.service.IComputerService;

@Controller
@RequestMapping("/editComputer")
@SessionAttributes("companies")
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

	@ModelAttribute("companies")
	public List<CompanyDTO> populateModelWithCompanies() {
		return companyMapperDTO.modelsToDto(companyService.getAll());
	}

	@RequestMapping(method = RequestMethod.GET)
	public String index(@RequestParam("id") Optional<Long> id, Model model) {
		if (id.isPresent()) {
			if (id.get() <= 0) {
				return PAGE_404;
			} else {
				model.addAttribute("computer", computerMapperDTO
						.modelToDto(computerService.getById(id.get())));
			}
		} else {
			return PAGE_404;
		}

		return EDIT_VIEW;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String editComputer(
			@Valid @ModelAttribute("computer") ComputerDTO computerDTO,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return EDIT_VIEW;
		}
		if (computerDTO.getName() == null) {
			LOGGER.error("Updating computer failed because of null name");
			model.addAttribute("message", "Name is mandatory");
			return EDIT_VIEW;
		}
		if (computerDTO.getCompanyId() != null) {
			computerDTO.setCompanyName(companyService.getById(
					Long.valueOf(computerDTO.getCompanyId())).getName());
		}
		final Computer computer = computerMapperDTO.dtoToModel(computerDTO);
		computerService.update(computer);
		LOGGER.info("Successfully updated computer with id {}",
				computer.getId());
		return "redirect:" + DASHBOARD_VIEW;
	}
}
