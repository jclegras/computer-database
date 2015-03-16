package com.excilys.dao;

import java.io.Serializable;
import java.util.List;

import com.excilys.exception.DAOException;

public interface DAO<T, I extends Serializable> {
	List<T> getAll() throws DAOException;
	T getById(I id) throws DAOException;
	I create(T entity) throws DAOException;
	void update(T entity) throws DAOException;
	void delete(I id) throws DAOException;
}
