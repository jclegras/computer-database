package com.excilys.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.exception.DAOException;
import com.excilys.exception.ExceptionMessage;
import com.excilys.mapper.CompanyMapper;
import com.excilys.model.Company;
import com.excilys.persistence.ComputerDatabaseConnection;

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
    private ComputerDatabaseConnection compDtbconnection;

    @Override
    public List<Company> getAll() {
        final List<Company> companies = new ArrayList<>();
        final Connection connection = compDtbconnection.getInstance();
        
        try (final Statement state = connection.createStatement()) {
            final ResultSet rs = state.executeQuery(RETRIEVE_ALL_COMPANIES);
            while (rs.next()) {
                companies.add(companyMapper.rowMap(rs));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
        	try {
				connection.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
        }

        return companies;
    }

    @Override
    public Company getById(Long id) {
        if (id == null || id <= 0) {
            throw new DAOException(ExceptionMessage.WRONG_ID.toString());
        }
        final Connection connection = compDtbconnection.getInstance();
        
        try (final PreparedStatement pStatement = connection.prepareStatement(GET_BY_ID_COMPANY)) {
            pStatement.setLong(1, id);
            final ResultSet rs = pStatement.executeQuery();
            if (rs.first()) {
                return companyMapper.rowMap(rs);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
        	try {
				connection.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
        }

        return null;
    }
    
    @Override
    public void delete(Long id) {
        if (id == null || id <= 0) {
            throw new DAOException(ExceptionMessage.WRONG_ID.toString());
        }
        final Connection connection = compDtbconnection.getInstance();
        
		try (final PreparedStatement pStatement = connection
				.prepareStatement(DELETE_COMPANY)) {
			pStatement.setLong(1, id);
			pStatement.execute();
			LOGGER.info("Entity with id {} successfully deleted", id);
		} catch (SQLException e) {
			throw new DAOException(e);
        } finally {
        	try {
				connection.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
        }
    }
   
}
