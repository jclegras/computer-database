package com.excilys.webservice.impl;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.LocalDateTimeConverter;
import com.excilys.mapper.MapperDTO;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.ICompanyService;
import com.excilys.service.IComputerService;
import com.excilys.webservice.IComputerDatabase;

@Service
@WebService(endpointInterface = "com.excilys.webservice.IComputerDatabase")
public class ComputerDatabase implements IComputerDatabase {

	@Autowired
	private IComputerService computerService;
	@Autowired
	private ICompanyService companyService;
	@Autowired
	private MapperDTO<Computer, ComputerDTO> computerMapper;
	@Autowired
	private MapperDTO<Company, CompanyDTO> companyMapper;
	@Autowired
	private LocalDateTimeConverter localDateTimeConverter;

	@Override
	public long count() {
		return computerService.count();
	}

	@Override
	public List<ComputerDTO> getAllComputers() {
		return computerMapper.modelsToDto(computerService.getAll());
	}

	@Override
	public ComputerDTO getByIdComputer(long id) {
		return computerMapper.modelToDto(computerService.getById(id));
	}

	@Override
	public List<ComputerDTO> getByName(String name) {
		return computerMapper.modelsToDto(computerService.getByName(name));
	}

	@Override
	public void create(ComputerDTO computer) {
		computerService.create(computerMapper.dtoToModel(computer));
	}

	@Override
	public void update(ComputerDTO computer) {
		final Computer old = computerService.getById(computer.getId());
		if (computer.getName() == null) {
			computer.setName(old.getName());
		}
		if (computer.getIntroduced() == null) {
			computer.setIntroduced(localDateTimeConverter.convertToText(old.getIntroduced()));
		}
		if (computer.getDiscontinued() == null) {
			computer.setDiscontinued(localDateTimeConverter.convertToText(old.getDiscontinued()));
		}
		if (computer.getCompanyId() != null) {
			computer.setCompanyName(companyService.getById(
					Long.valueOf(computer.getCompanyId())).getName());	
		} else {
			if (old.getCompany() != null) {
				computer.setCompanyId(String.valueOf(old.getCompany().getId()));
				computer.setCompanyName(old.getCompany().getName());
			}
		}
		computerService.update(computerMapper.dtoToModel(computer));
	}

	@Override
	public void deleteComputer(long id) {
		computerService.delete(id);
	}

	@Override
	public List<CompanyDTO> getAllCompanies() {
		return companyMapper.modelsToDto(companyService.getAll());
	}

	@Override
	public CompanyDTO getByIdCompany(long id) {
		return companyMapper.modelToDto(companyService.getById(id));
	}

	@Override
	public void deleteCompany(long id) {
		companyService.delete(id);
	}

}
