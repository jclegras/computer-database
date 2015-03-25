package com.excilys.dto;

public class ComputerDTO {
	private long id;
	private String name;
	private String introduced;
	private String discontinued;
	private String companyId;
	private String companyName;


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
	
	public String getCompanyId() {
		return companyId;
	}
	
	public String getCompanyName() {
		return companyName;
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
	
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
