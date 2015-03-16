package com.excilys.dao;

import java.io.Serializable;
import java.util.List;

import com.excilys.exception.DAOException;

public interface DAO<T, I extends Serializable> {
	/**
	 * Retrieves all entities.
	 * @return Entities
	 * @throws DAOException
	 */
	List<T> getAll() throws DAOException;
	/**
	 * Retrieve entity by its identifier.
	 * @pre id != null
	 * @param id Identifier
	 * @return The matching entity
	 * @throws DAOException
	 */
	T getById(I id) throws DAOException;
	/**
	 * Create a new entity.
	 * @pre entity != null
	 * @param entity
	 * @return The identifier of the entity
	 * @throws DAOException
	 */
	I create(T entity) throws DAOException;
	/**
	 * Update the entity.
	 * @pre entity != null
	 * @param entity Entity to update
	 * @throws DAOException
	 */
	void update(T entity) throws DAOException;
	/**
	 * Delete a entity by its identifier.
	 * @pre id != null
	 * @param id Identifier
	 * @throws DAOException
	 */
	void delete(I id) throws DAOException;
}
