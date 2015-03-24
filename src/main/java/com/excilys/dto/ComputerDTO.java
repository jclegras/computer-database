package com.excilys.dto;

public class ComputerDTO {
	private long id;
	private String name;
	private String introduced;
	private String discontinued;
	private String company;
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getIntroduced() {
		return introduced;
	}
	
	public String getDiscontinued() {
		return discontinued;
	}
	
	public String getCompany() {
		return company;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}
	
	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}
	
	public void setCompany(String company) {
		this.company = company;
	}
}
