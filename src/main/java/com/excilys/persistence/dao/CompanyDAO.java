package com.excilys.persistence.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.exception.DAOException;
import com.excilys.exception.ExceptionMessage;
import com.excilys.mapper.CompanyMapper;
import com.excilys.model.Company;

@Repository
public class CompanyDAO implements DAO<Company, Long> {

	private static final String DELETE_COMPANY = "DELETE FROM company WHERE id = ?";
    private static final String GET_BY_ID_COMPANY = "SELECT * FROM company WHERE id = ?";
    private static final String RETRIEVE_ALL_COMPANIES = "SELECT * FROM company";
    
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CompanyDAO.class);

    @Autowired
    private ComputerDAO computerDAO;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Company> getAll() {
        return jdbcTemplate.query(RETRIEVE_ALL_COMPANIES, companyMapper);
    }

    @Override
    public Company getById(Long id) {
        if (id == null || id <= 0) {
            throw new DAOException(ExceptionMessage.WRONG_ID.toString());
        }
        return jdbcTemplate.queryForObject(GET_BY_ID_COMPANY, new Object[] { id }, companyMapper);
    }
    
    @Override
    public void delete(Long id) {
        if (id == null || id <= 0) {
            throw new DAOException(ExceptionMessage.WRONG_ID.toString());
        }
        jdbcTemplate.update(DELETE_COMPANY, id);
		LOGGER.info("Entity with id {} successfully deleted", id);
    }
   
}
