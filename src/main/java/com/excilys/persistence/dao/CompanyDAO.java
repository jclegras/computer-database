package com.excilys.persistence.dao;

import com.excilys.exception.DAOException;
import com.excilys.mapper.CompanyMapper;
import com.excilys.model.Company;
import com.excilys.persistence.ComputerDatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public enum CompanyDAO implements DAO<Company, Long> {
    INSTANCE;

    @Override
    public List<Company> getAll() {
        final List<Company> companies = new ArrayList<>();
        final CompanyMapper companyMapper = new CompanyMapper();

        try (final Statement state = ComputerDatabaseConnection.INSTANCE
                .getInstance().createStatement()) {
            final ResultSet rs = state.executeQuery("SELECT * FROM company");
            while (rs.next()) {
                companies.add(companyMapper.rowMap(rs));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return companies;
    }

    @Override
    public Company getById(Long id) {
        final CompanyMapper companyMapper = new CompanyMapper();
        final String sql = "SELECT * FROM company WHERE id = ?";

        try (final PreparedStatement pStatement = ComputerDatabaseConnection.INSTANCE
                .getInstance().prepareStatement(sql)) {
            pStatement.setLong(1, id);
            final ResultSet rs = pStatement.executeQuery();
            if (rs.first()) {
                return companyMapper.rowMap(rs);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return null;
    }

    public List<Long> getAllCompaniesId() {
        final List<Long> companies = new ArrayList<>();

        try (final Statement state = ComputerDatabaseConnection.INSTANCE
                .getInstance().createStatement()) {
            final ResultSet rs = state.executeQuery("SELECT id FROM company");
            while (rs.next()) {
                companies.add(rs.getLong("id"));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return companies;
    }
}
