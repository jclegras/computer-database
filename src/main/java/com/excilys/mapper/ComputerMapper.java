package com.excilys.mapper;

import com.excilys.model.Company;
import com.excilys.model.Computer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ComputerMapper implements Mapper<Computer> {

    @Override
    public Computer rowMap(ResultSet res) throws SQLException {
        if (res == null) {
            throw new IllegalArgumentException();
        }
        final Computer computer = new Computer();
        computer.setId(res.getLong("computer.id"));
        computer.setName(res.getString("computer.name"));
        final Timestamp introduced = res.getTimestamp("computer.introduced");
        if (introduced != null) {
            computer.setIntroduced(introduced.toLocalDateTime());
        }
        final Timestamp discontinued = res.getTimestamp("computer.discontinued");
        if (discontinued != null) {
            computer.setDiscontinued(discontinued.toLocalDateTime());
        }
        final Long companyId = res.getLong("computer.company_id");
        if (companyId > 0) {
            final Company company = new Company();
            company.setId(companyId);
            company.setName(res.getString("company.name"));
            computer.setCompany(company);
        }
        return computer;
    }

}
