package com.excilys.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.model.Company;
import com.excilys.persistence.ComputerDatabaseConnection;
import com.excilys.persistence.dao.CompanyDAO;
import com.excilys.util.DBUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/application-context.xml")
@ActiveProfiles("TEST")
public class ComputerServiceTest {
	@Autowired
	private CompanyDAO companyDAO;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ComputerDatabaseConnection computerDatabaseConnection;
	
    @Before
    public void setUpDB() {
        try {
        	DBUtil.executeSqlFile("test.sql", computerDatabaseConnection.getInstance());
        } catch (IOException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @After
    public void tearDown() throws Exception {
        DBUtil.databaseTester.onTearDown();
    }
	
    @Test
    public void deleteSuccessfull() throws Exception {
        // GIVEN
        DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
                "src/test/resources/datasets/companyService/delete.xml")));
        final long id = 1L;
        final int expectedNbCompanies = 1;
        final long expectedCompanyId = 2L;
        
        // WHEN
        companyService.delete(id);
        
        // THEN
        final List<Company> companies = companyDAO.getAll();
        Assertions.assertThat(companies).isNotNull();
        Assertions.assertThat(companies.size()).isEqualTo(expectedNbCompanies);
        Assertions.assertThat(companies.get(0).getId()).isEqualTo(expectedCompanyId);
    }
}
