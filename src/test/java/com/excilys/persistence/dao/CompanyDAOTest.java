package com.excilys.persistence.dao;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.excilys.model.Company;
import com.excilys.util.DBUtil;

public class CompanyDAOTest {
	@BeforeClass
	public static void setUpDB() {
		System.setProperty("env", "TEST");
		try {
			DBUtil.executeSqlFile("test.sql", DBUtil.getConnection());
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
				"src/test/java/datasets/companyDAO/getAll.xml")));
		final int expectedSize = 2;
		final Company expectedCompany1 = new Company(1L, "IBM");
		final Company expectedCompany2 = new Company(2L, "Dell");
		
		// WHEN
		final List<Company> companies = CompanyDAO.INSTANCE.getAll();

		// THEN
		Assertions.assertThat(companies).isNotNull();
		Assertions.assertThat(companies.size()).isEqualTo(expectedSize);
		Assertions.assertThat(companies).contains(expectedCompany1, expectedCompany2);
	}
	
	@Test
	public void getAllWhenNoEntityGiveEmptyList() throws Exception {
		// GIVEN
		DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
				"src/test/java/datasets/companyDAO/getAllNoEntity.xml")));
		
		// WHEN
		final List<Company> companies = CompanyDAO.INSTANCE.getAll();

		// THEN
		Assertions.assertThat(companies).isNotNull();
		Assertions.assertThat(companies).isEmpty();
	}
	
	@Test
	public void getByIdWithSuccess() throws Exception {
		// GIVEN
		DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
				"src/test/java/datasets/companyDAO/getById.xml")));
		final long id = 2L;
		final long expectedId = 2L;
		final String expectedName = "Dell";
		
		// WHEN
		final Company company = CompanyDAO.INSTANCE.getById(id);

		// THEN
		Assertions.assertThat(company).isNotNull();
		Assertions.assertThat(company.getId()).isEqualTo(expectedId);
		Assertions.assertThat(company.getName()).isEqualTo(expectedName);
	}
	
	@Test
	public void getByIdWhenNotFoundGivesANullResult() throws Exception {
		// GIVEN
		DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
				"src/test/java/datasets/companyDAO/getById.xml")));
		final long id = 45456L;
		
		// WHEN
		final Company company = CompanyDAO.INSTANCE.getById(id);
		
		// THEN
		Assertions.assertThat(company).isNull();
	}
	
	@Test
	public void getByIdWithNullIDThrowsException() throws Exception {
		// GIVEN
		DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
				"src/test/java/datasets/companyDAO/getById.xml")));
		final String expectedMessage = "ID must be not null";
		
		// WHEN
		try {
			CompanyDAO.INSTANCE.getById(null);
			// THEN KO
			Assertions.fail("Must fail because of null ID");
		} catch (Exception e) {
			// THEN
			Assertions.assertThat(e).isInstanceOf(IllegalArgumentException.class);
			Assertions.assertThat(e.getMessage()).isEqualTo(expectedMessage);
		}
	}
}
