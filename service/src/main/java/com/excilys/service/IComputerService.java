package com.excilys.service;

import java.util.List;

import com.excilys.model.Computer;
import com.excilys.persistence.util.Page;

/**
 * A computer service.
 * @inv
 *     count() >= 0
 * @param <T> The entity to manage.
 * @param <I> The id type.
 */
public interface IComputerService extends IService<Computer, Long> {
	/**
	 * Total number of computers.
	 * 
	 * @return Total entities
	 */
	Long count();
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
	 * @return All entities matching the given pattern
	 * @pre name != null
	 */
	List<Computer> getByName(String name);
}
