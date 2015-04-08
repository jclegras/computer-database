package com.excilys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.exception.ExceptionMessage;
import com.excilys.exception.ServiceException;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.dao.CompanyDAO;
import com.excilys.persistence.dao.ComputerDAO;

@Service
public class CompanyService implements IService<Company, Long> {

	@Autowired
    private CompanyDAO companyDAO;
	@Autowired
	private ComputerDAO computerDAO;
	
	public void setCompanyDAO(CompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}

    public List<Company> getAll() {
        return companyDAO.getAll();
    }

    public Company getById(Long id) {
        if ((id == null) || (id <= 0)) {
            throw new ServiceException(ExceptionMessage.WRONG_ID.toString());
        }
        return companyDAO.getById(id);
    }
    
    @Transactional
    public void delete(Long id) {
        if ((id != null) && (id <= 0)) {
            throw new ServiceException(ExceptionMessage.WRONG_ID.toString());
        }
        final List<Computer> computers = computerDAO.getAllByCompany(id);
        
        for (Computer computer : computers) {
        	computerDAO.delete(computer.getId());
        }
        companyDAO.delete(id);
    }
}
