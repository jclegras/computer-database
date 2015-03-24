package com.excilys.service;

import com.excilys.model.Company;
import com.excilys.persistence.dao.CompanyDAO;

import java.util.List;

public enum CompanyService {
    INSTANCE;

    public List<Long> getAllCompaniesId() {
        return CompanyDAO.INSTANCE.getAllCompaniesId();
    }

    public List<Company> getAll() {
        return CompanyDAO.INSTANCE.getAll();
    }

    public Company getById(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException();
        }
        return CompanyDAO.INSTANCE.getById(id);
    }
}
