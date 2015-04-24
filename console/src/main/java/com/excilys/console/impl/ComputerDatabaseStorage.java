package com.excilys.console.impl;

import java.util.List;

import com.excilys.console.IComputerDatabaseStorage;
import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;


public class ComputerDatabaseStorage implements IComputerDatabaseStorage {
	private long count;
	private ComputerDTO computer;
	private CompanyDTO company;
	private List<ComputerDTO> computers;
	private List<CompanyDTO> companies;
	
	@Override
	public long getCount() {
		return count;
	}
	
	@Override
	public ComputerDTO getComputer() {
		return computer;
	}

	@Override
	public CompanyDTO getCompany() {
		return company;
	}
	
	@Override
	public List<ComputerDTO> getComputers() {
		return computers;
	}
	
	@Override
	public List<CompanyDTO> getCompanies() {
		return companies;
	}
	
	@Override
	public void setComputer(ComputerDTO computer) {
		this.computer = computer;
	}

	@Override
	public void setCompany(CompanyDTO company) {
		this.company = company;
	}	

	@Override
	public void setComputers(List<ComputerDTO> computers) {
		this.computers = computers;
	}

	@Override
	public void setCompanies(List<CompanyDTO> companies) {
		this.companies = companies;
	}

	@Override
	public void setCount(long count) {
		this.count = count;
	}

}
