package com.excilys.service;

import java.util.List;

import com.excilys.exception.ServiceException;
import com.excilys.model.Company;
import com.excilys.persistence.dao.CompanyDAO;

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
            throw new ServiceException();
        }
        return CompanyDAO.INSTANCE.getById(id);
    }
}
