package com.excilys.service;

import java.util.List;

import com.excilys.exception.ExceptionMessage;
import com.excilys.exception.ServiceException;
import com.excilys.model.Company;
import com.excilys.persistence.dao.CompanyDAO;

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
