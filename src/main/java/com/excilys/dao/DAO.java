package com.excilys.dao;

import java.io.Serializable;
import java.util.List;

public interface DAO<T, I extends Serializable> {
	List<T> getAll();
	T getById(I id);
	I create(T entity);
	void update(T entity);
	void delete(I id);
}
