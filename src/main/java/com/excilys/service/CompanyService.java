package com.excilys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.exception.ExceptionMessage;
import com.excilys.exception.ServiceException;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.dao.ICompanyDAO;
import com.excilys.persistence.dao.IComputerDAO;

@Service
public class CompanyService implements ICompanyService {

	@Autowired
    private ICompanyDAO companyDAO;
	@Autowired
	private IComputerDAO computerDAO;
	
	public void setCompanyDAO(ICompanyDAO companyDAO) {
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
