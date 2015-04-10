package com.excilys.persistence.dao;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.model.Company;
import com.excilys.persistence.ComputerDatabaseConnection;
import com.excilys.util.DBUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/test/resources/applicationContext.xml")
public class CompanyDAOTest {
	@Autowired
	private CompanyDAO companyDAO;
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
    public void getAllWithSuccess() throws Exception {
        // GIVEN
    	DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
    			"src/test/resources/datasets/companyDAO/getAll.xml")));
        final int expectedSize = 2;
        final Company expectedCompany1 = new Company(1L, "IBM");
        final Company expectedCompany2 = new Company(2L, "Dell");

        // WHEN
        final List<Company> companies = companyDAO.getAll();

        // THEN
        Assertions.assertThat(companies).isNotNull();
        Assertions.assertThat(companies.size()).isEqualTo(expectedSize);
        Assertions.assertThat(companies).contains(expectedCompany1, expectedCompany2);
    }

    @Test
    public void getAllWhenNoEntityGiveEmptyList() throws Exception {
        // GIVEN
        DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
                "src/test/resources/datasets/companyDAO/getAllNoEntity.xml")));

        // WHEN
        final List<Company> companies = companyDAO.getAll();

        // THEN
        Assertions.assertThat(companies).isNotNull();
        Assertions.assertThat(companies).isEmpty();
    }

    @Test
    public void getByIdWithSuccess() throws Exception {
        // GIVEN
        DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
                "src/test/resources/datasets/companyDAO/getById.xml")));
        final long id = 2L;
        final long expectedId = 2L;
        final String expectedName = "Dell";

        // WHEN
        final Company company = companyDAO.getById(id);

        // THEN
        Assertions.assertThat(company).isNotNull();
        Assertions.assertThat(company.getId()).isEqualTo(expectedId);
        Assertions.assertThat(company.getName()).isEqualTo(expectedName);
    }

    @Test
    public void getByIdWhenNotFoundGivesANullResult() throws Exception {
        // GIVEN
        DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
                "src/test/resources/datasets/companyDAO/getById.xml")));
        final long id = 45456L;

        // WHEN
        final Company company = companyDAO.getById(id);

        // THEN
        Assertions.assertThat(company).isNull();
    }
    
    @Test
    public void deleteSuccessfull() throws Exception {
    	// GIVEN
        DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
                "src/test/resources/datasets/companyDAO/delete.xml")));
        final long id = 1L;
    	
    	// WHEN
        companyDAO.delete(id);
    	
    	// THEN
        Assertions.assertThat(companyDAO.getAll()).isNotNull();
        Assertions.assertThat(companyDAO.getAll()).isEmpty();
    }
}
