package com.excilys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.exception.ExceptionMessage;
import com.excilys.exception.ServiceException;
import com.excilys.model.Company;
import com.excilys.persistence.dao.CompanyDAO;

@Service
@Transactional(readOnly = true)
public class CompanyService implements IService<Company, Long> {

	@Autowired
    private CompanyDAO companyDAO;

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
