package com.excilys.service;

import com.excilys.exception.ExceptionMessage;
import com.excilys.exception.ServiceException;
import com.excilys.model.Company;
import com.excilys.persistence.dao.CompanyDAO;

import java.util.List;

public enum CompanyService implements Service<Company, Long> {
    INSTANCE;

    private final CompanyDAO companyDAO = CompanyDAO.INSTANCE;

    public List<Company> getAll() {
        return companyDAO.getAll();
    }

    public Company getById(Long id) {
        if ((id == null) || (id <= 0)) {
            throw new ServiceException(ExceptionMessage.WRONG_ID.toString());
        }
        return companyDAO.getById(id);
    }
}
