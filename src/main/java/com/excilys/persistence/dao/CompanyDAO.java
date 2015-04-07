package com.excilys.persistence.dao;

import com.excilys.exception.DAOException;
import com.excilys.exception.ExceptionMessage;
import com.excilys.mapper.CompanyMapper;
import com.excilys.model.Company;
import com.excilys.persistence.ComputerDatabaseConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CompanyDAO implements DAO<Company, Long> {

    private static final String GET_BY_ID_COMPANY = "SELECT * FROM company WHERE id = ?";
    private static final String RETRIEVE_ALL_COMPANIES = "SELECT * FROM company";

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
        }
//        finally {
//        	compDtbconnection.close();
//        }

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
        }
//        finally {
//        	compDtbconnection.close();
//        }

        return null;
    }
}
