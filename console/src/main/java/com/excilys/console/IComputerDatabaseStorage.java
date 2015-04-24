package com.excilys.console;

import java.util.List;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;

public interface IComputerDatabaseStorage {
	long getCount();
	ComputerDTO getComputer();
	CompanyDTO getCompany();
	List<ComputerDTO> getComputers();
	List<CompanyDTO> getCompanies();
	void setCount(long count);
	void setComputer(ComputerDTO computer);
	void setCompany(CompanyDTO company);
	void setComputers(List<ComputerDTO> computers);
	void setCompanies(List<CompanyDTO> companies);
}
