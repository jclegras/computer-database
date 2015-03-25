package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.model.Company;

public enum CompanyMapper implements Mapper<Company> {
	INSTANCE;
	
    @Override
    public Company rowMap(ResultSet res) throws SQLException {
        if (res == null) {
            throw new IllegalArgumentException();
        }
        final Company company = new Company();
        company.setId(res.getLong("id"));
        company.setName(res.getString("name"));
        return company;
    }

}
