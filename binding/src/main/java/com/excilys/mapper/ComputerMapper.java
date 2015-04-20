package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.excilys.model.Company;
import com.excilys.model.Computer;

@Component
public class ComputerMapper implements RowMapper<Computer> {

	@Override
	public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
        if (rs == null) {
            throw new IllegalArgumentException();
        }
        final Computer computer = new Computer();
        computer.setId(rs.getLong("computer.id"));
        computer.setName(rs.getString("computer.name"));
        final Timestamp introduced = rs.getTimestamp("computer.introduced");
        if (introduced != null) {
            computer.setIntroduced(introduced.toLocalDateTime());
        }
        final Timestamp discontinued = rs.getTimestamp("computer.discontinued");
        if (discontinued != null) {
            computer.setDiscontinued(discontinued.toLocalDateTime());
        }
        final Long companyId = rs.getLong("computer.company_id");
        if (companyId > 0) {
            final Company company = new Company();
            company.setId(companyId);
            company.setName(rs.getString("company.name"));
            computer.setCompany(company);
        }
        return computer;
	}

  

}
