package com.excilys.service;

import java.util.List;

import com.excilys.model.Company;
import com.excilys.persistence.dao.CompanyDAO;

public enum CompanyService {
	INSTANCE;

	public List<Company> getAll() {
		return CompanyDAO.INSTANCE.getAll();
	}

	public Company getById(Long id) {
		return CompanyDAO.INSTANCE.getById(id);
	}
}
