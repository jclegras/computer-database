package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.model.Company;

public class CompanyMapper implements Mapper<Company> {

	@Override
	public Company rowMap(ResultSet res) throws SQLException {
		final Company company = new Company();
		company.setId(res.getLong("id"));
		company.setName(res.getString("name"));
		return company;
	}

}
