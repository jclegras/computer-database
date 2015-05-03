package com.excilys.persistence.dao;

import java.util.List;

import com.excilys.model.Computer;
import com.excilys.persistence.util.Page;

/**
 * A computer DAO.
 * @inv
 *     count() >= 0
 * @param <Computer> The entity to manage.
 * @param <Long> The id type.
 */
public interface IComputerDAO extends DAO<Computer, Long> {
	/**
	 * Total number of computers.
	 * 
	 * @return Total entities
	 */
	Long count();

    /**
     * Total numbers of computers for the given pattern.
     * Match computer and company's name.
     * @param search Pattern for computer's and company's name
     * @return Total entities
     */
    Long count(String search);

	/**
	 * Retrieve all computers for the given page.
	 * 
	 * @param page
	 * @return All entities of page
	 * @pre page != null
	 */
	List<Computer> getAll(Page page);
	/**
	 * Retrieve all computers of name <code>name</code>.
	 * 
	 * @param name Pattern for computer's and company's name
	 * @param page
	 * @return All entities matching the given pattern
	 * @pre name != null
	 */
	List<Computer> getByName(Page page, String name);
	/**
	 * Retrieve all computers with the company of identifier <code>id</code>.
	 * 
	 * @param id Company's identifier
	 * @return All entities for the given company
	 * @pre id != null && id > 0
	 */
	List<Computer> getAllByCompany(Long id);
}
