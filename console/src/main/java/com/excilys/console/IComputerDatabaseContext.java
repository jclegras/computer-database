package com.excilys.console;

import com.excilys.dto.ComputerDTO;
import com.excilys.webservice.IComputerDatabase;

public interface IComputerDatabaseContext {
	long getId();
	String getName();
	IComputerDatabase getWebService();
	ComputerDTO getComputerDTO();
	IComputerDatabaseScanner getScanner();
	void setId(long id);
	void setWebService(IComputerDatabase webService);
	void setName(String name);
	void setComputerDTO(ComputerDTO dto);
	void setScanner(IComputerDatabaseScanner scanner);
}
