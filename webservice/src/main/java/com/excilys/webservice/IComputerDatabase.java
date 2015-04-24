package com.excilys.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL)
public interface IComputerDatabase {
	@WebMethod
	long count();
	@WebMethod
	List<ComputerDTO> getAllComputers();
	@WebMethod
	List<CompanyDTO> getAllCompanies();
	@WebMethod
	ComputerDTO getByIdComputer(long id);
	@WebMethod
	CompanyDTO getByIdCompany(long id);
	@WebMethod
	List<ComputerDTO> getByName(String name);
	@WebMethod
	void create(ComputerDTO computer);
	@WebMethod
	void update(ComputerDTO computer);
	@WebMethod
	void deleteComputer(long id);
	@WebMethod
	void deleteCompany(long id);
}
