package com.excilys.console;

import java.util.List;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;

public interface IComputerDatabaseStorage {
	ComputerDTO getComputer();
	CompanyDTO getCompany();
	List<ComputerDTO> getComputers();
	List<CompanyDTO> getCompanies();
	void setComputer(ComputerDTO computer);
	void setCompany(CompanyDTO company);
	void setComputers(List<ComputerDTO> computers);
	void setCompanies(List<CompanyDTO> companies);
}
