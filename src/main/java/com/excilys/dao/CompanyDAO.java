package com.excilys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.mapper.CompanyMapper;
import com.excilys.model.Company;
import com.excilys.persistence.ComputerDatabaseConnection;



public enum CompanyDAO implements DAO<Company, Long> {
	INSTANCE;

	@Override
	public List<Company> getAll() {
		final List<Company> companies = new ArrayList<>();
		final CompanyMapper companyMapper = new CompanyMapper();
		
		try {
			try (final Statement state = ComputerDatabaseConnection.INSTANCE.getInstance().createStatement()) {
				try (final ResultSet rs = state.executeQuery("SELECT * FROM company")) {
					while (rs.next()) {
						companies.add(companyMapper.rowMap(rs));
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return companies;
	}

	@Override
	public Company getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long create(Company entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Company entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
