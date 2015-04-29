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
	/**
	 * Retrieve number of computers.
	 * @return total number of computers
	 */
	@WebMethod
	long count();
	/**
	 * Retrieve all computers.
	 * @return computers
	 */
	@WebMethod
	List<ComputerDTO> getAllComputers();
	/**
	 * Retrieve all companies.
	 * @return companies
	 */
	@WebMethod
	List<CompanyDTO> getAllCompanies();
	/**
	 * Retrieve a computer from the id.
	 * @param id Computer's ID
	 * @return a computer
	 */
	@WebMethod
	ComputerDTO getByIdComputer(long id);
	/**
	 * Retrieve a company from the id.
	 * @param id Company's ID
	 * @return a company
	 */
	@WebMethod
	CompanyDTO getByIdCompany(long id);
	/**
	 * Retrieve a computer from its name or its company's name.
	 * @param name Pattern
	 * @return a computer
	 */
	@WebMethod
	List<ComputerDTO> getByName(String name);
	/**
	 * Create a new computer.
	 * @param computer New computer
	 */
	@WebMethod
	void create(ComputerDTO computer);
	/**
	 * Update a computer.
	 * @param computer Computer to update
	 */
	@WebMethod
	void update(ComputerDTO computer);
	/**
	 * Delete a computer.
	 * @param id Computer to delete.
	 */
	@WebMethod
	void deleteComputer(long id);
	/**
	 * Delete a company.
	 * @param id Company to delete
	 */
	@WebMethod
	void deleteCompany(long id);
}
