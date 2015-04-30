package com.excilys.service;

import com.excilys.exception.ExceptionMessage;
import com.excilys.exception.ServiceException;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.dao.ICompanyDAO;
import com.excilys.persistence.dao.IComputerDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CompanyService implements ICompanyService {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(CompanyService.class);

	@Autowired
    private ICompanyDAO companyDAO;
	@Autowired
	private IComputerDAO computerDAO;
	
	public void setCompanyDAO(ICompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}

	@Transactional(readOnly = true)
    public List<Company> getAll() {
        return companyDAO.getAll();
    }

	@Transactional(readOnly = true)
    public Company getById(Long id) {
        if ((id == null) || (id <= 0)) {
            LOGGER.error("ID is invalid");
            throw new ServiceException(ExceptionMessage.WRONG_ID.toString());
        }
        return companyDAO.getById(id);
    }
    
    public void delete(Long id) {
        if ((id != null) && (id <= 0)) {
            LOGGER.error("ID is invalid");
            throw new ServiceException(ExceptionMessage.WRONG_ID.toString());
        }
        final List<Computer> computers = computerDAO.getAllByCompany(id);
        
        for (Computer computer : computers) {
        	computerDAO.delete(computer.getId());
        }
        companyDAO.delete(id);
        LOGGER.info("Entity with id {} successfully deleted", id);
    }
}
