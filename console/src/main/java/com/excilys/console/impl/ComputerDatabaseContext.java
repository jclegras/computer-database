package com.excilys.console.impl;

import com.excilys.console.IComputerDatabaseContext;
import com.excilys.console.IComputerDatabaseScanner;
import com.excilys.dto.ComputerDTO;
import com.excilys.webservice.IComputerDatabase;


public class ComputerDatabaseContext implements IComputerDatabaseContext {
	private long id;
	private String name;
	private ComputerDTO computerDTO;
	private IComputerDatabase webService;
	private IComputerDatabaseScanner scanner;
	
	public ComputerDatabaseContext(IComputerDatabase webService) {
		this.webService = webService;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public IComputerDatabase getWebService() {
		return webService;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public ComputerDTO getComputerDTO() {
		return computerDTO;
	}
	
	@Override
	public IComputerDatabaseScanner getScanner() {
		return scanner;
	}	

	@Override
	public void setId(long id) {
		this.id = id;
	}
	
	public void setWebService(IComputerDatabase webService) {
		this.webService = webService;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setComputerDTO(ComputerDTO dto) {
		this.computerDTO = dto;
	}

	@Override
	public void setScanner(IComputerDatabaseScanner scanner) {
		this.scanner = scanner;
	}
}
