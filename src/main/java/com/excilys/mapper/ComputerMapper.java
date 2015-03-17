package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.excilys.model.Company;
import com.excilys.model.Computer;

public class ComputerMapper implements Mapper<Computer> {

	@Override
	public Computer rowMap(ResultSet res) throws SQLException {
		if (res == null) {
			throw new IllegalArgumentException();
		}
		final Computer computer = new Computer();
		computer.setId(res.getLong("id"));
		computer.setName(res.getString("name"));
		final Timestamp introduced = res.getTimestamp("introduced");
		if (introduced != null) {
			computer.setIntroduced(introduced.toLocalDateTime());
		}
		final Timestamp discontinued = res.getTimestamp("discontinued");
		if (discontinued != null) {
			computer.setDiscontinued(discontinued.toLocalDateTime());
		}
		final Long companyId = res.getLong("company_id");
		if (companyId > 0) {
			final Company company = new Company();
			company.setId(companyId);
			company.setName(res.getString("compa.name"));
			computer.setCompany(company);
		}
		return computer;
	}

}
