package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.model.Computer;

public class ComputerMapper implements Mapper<Computer> {

	@Override
	public Computer rowMap(ResultSet res) throws SQLException {
		final Computer computer = new Computer();
		computer.setId(res.getLong("id"));
		computer.setName(res.getString("name"));
		if (res.getTimestamp("introduced") != null) {
			computer.setIntroduced(res.getTimestamp("introduced").toLocalDateTime());	
		}
		if (res.getTimestamp("discontinued") != null) {
			computer.setDiscontinued(res.getTimestamp("discontinued").toLocalDateTime());
		}
		computer.setCompanyId(res.getLong("company_id"));
		return computer;
	}

}
