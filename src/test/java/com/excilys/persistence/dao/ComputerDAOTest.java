package com.excilys.persistence.dao;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.util.DBUtil;

public class ComputerDAOTest {

	@BeforeClass
	public static void setUpDB() {
		System.setProperty("env", "TEST");
		try {
			DBUtil.executeSqlFile("test.sql", DBUtil.getConnection());
		} catch (IOException | SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void getAllWithSuccess() throws Exception {
		// GIVEN
		DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
				"src/test/java/datasets/computerDAO/getAll.xml")));
		final int expectedSize = 1;
		final Company company1 = new Company(1, "IBM");
		final Computer computer1 = new Computer(1L, "CM-200", LocalDateTime.of(
				1991, 1, 1, 0, 0, 0), null, company1);

		// WHEN
		final List<Computer> computers = ComputerDAO.INSTANCE.getAll();

		// THEN
		Assertions.assertThat(computers).isNotNull();
		Assertions.assertThat(computers.size()).isEqualTo(expectedSize);
		Assertions.assertThat(computers).contains(computer1);
	}

	@Test
	public void getAllWhenNoEntityGiveEmptyList() throws Exception {
		// GIVEN
		DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
				"src/test/java/datasets/computerDAO/getAllNoEntity.xml")));

		// WHEN
		final List<Computer> computers = ComputerDAO.INSTANCE.getAll();

		// THEN
		Assertions.assertThat(computers).isNotNull();
		Assertions.assertThat(computers).isEmpty();
	}

	@Test
	public void getByIdWithSuccess() throws Exception {
		// GIVEN
		DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
				"src/test/java/datasets/computerDAO/getById.xml")));
		final long id = 1L;
		final long expectedId = 1L;
		final String expectedName = "CM-200";

		// WHEN
		final Computer computer = ComputerDAO.INSTANCE.getById(id);

		// THEN
		Assertions.assertThat(computer).isNotNull();
		Assertions.assertThat(computer.getId()).isEqualTo(expectedId);
		Assertions.assertThat(computer.getName()).isEqualTo(expectedName);
	}

	@Test
	public void getByIdWhenNotFoundGivesANullResult() throws Exception {
		// GIVEN
		DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
				"src/test/java/datasets/computerDAO/getById.xml")));
		final long id = 45456L;

		// WHEN
		final Computer computer = ComputerDAO.INSTANCE.getById(id);

		// THEN
		Assertions.assertThat(computer).isNull();
	}
	
	@Test
	public void getByIdWhenNullIdThrowsException() throws Exception {
		// GIVEN
		DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
				"src/test/java/datasets/computerDAO/getById.xml")));
		final String expectedMessage = "ID must be not null";

		// WHEN
		try {
			ComputerDAO.INSTANCE.getById(null);
			Assertions.fail("Must fail because of Null ID");
		} catch (Exception e) {
			Assertions.assertThat(e).isInstanceOf(IllegalArgumentException.class);
			Assertions.assertThat(e.getMessage()).isEqualTo(expectedMessage);
		}
	}

	@Test
	public void createWithSuccess() throws Exception {
		// GIVEN
		DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
				"src/test/java/datasets/computerDAO/create.xml")));
		final Computer computer = new Computer(null, "name",
				LocalDateTime.now(), null, null);
		final int expectedSize = 1;
		ComputerDAO.INSTANCE.create(computer);

		// WHEN
		final List<Computer> computers = ComputerDAO.INSTANCE.getAll();

		// THEN
		Assertions.assertThat(computers).isNotNull();
		Assertions.assertThat(computers.size()).isEqualTo(expectedSize);
		Assertions.assertThat(computers.get(0).getName()).isEqualTo("name");
		
		// CLEAN
		ComputerDAO.INSTANCE.delete(computer.getId());
	}
	
	@Test
	public void createWithNullEntityThrowsException() throws Exception {
		// GIVEN
		DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
				"src/test/java/datasets/computerDAO/create.xml")));
		final String expectedMessage = "Entity must be not null";

		// WHEN
		try {
			ComputerDAO.INSTANCE.create(null);
			// THEN KO
			Assertions.fail("Must fail because of null entity");
		} catch (Exception e) {
			Assertions.assertThat(e).isInstanceOf(IllegalArgumentException.class);
			Assertions.assertThat(e.getMessage()).isEqualTo(expectedMessage);
		}
	}

	@Test
	public void updateWithSuccess() throws Exception {
		// GIVEN
		DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
				"src/test/java/datasets/computerDAO/update.xml")));
		final String expectedName = "newName";
		Computer computer = ComputerDAO.INSTANCE.getById(1L);
		computer.setName(expectedName);
		ComputerDAO.INSTANCE.update(computer);

		// WHEN
		computer = ComputerDAO.INSTANCE.getById(1L);

		// THEN
		Assertions.assertThat(computer).isNotNull();
		Assertions.assertThat(computer.getName()).isEqualTo(expectedName);
	}
	
	@Test
	public void updateWithNullParameterThrowsException() throws Exception {
		// GIVEN
		DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
				"src/test/java/datasets/computerDAO/update.xml")));
		final String expectedMessage = "Entity must be not null";
		
		// WHEN
		try {
			ComputerDAO.INSTANCE.update(null);
			// THEN KO
			Assertions.fail("Must fail because of null entity");
		} catch (Exception e) {
			// THEN
			Assertions.assertThat(e).isInstanceOf(IllegalArgumentException.class);
			Assertions.assertThat(e.getMessage()).isEqualTo(expectedMessage);
		}
	}
	
	@Test
	public void deleteWithSuccess() throws Exception {
		// GIVEN
		DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
				"src/test/java/datasets/computerDAO/delete.xml")));
		ComputerDAO.INSTANCE.delete(1L);
		
		// WHEN
		final List<Computer> computers = ComputerDAO.INSTANCE.getAll();
		
		// THEN
		Assertions.assertThat(computers).isNotNull();
		Assertions.assertThat(computers).isEmpty();
	}
	
	@Test
	public void deleteWithWrongId() throws Exception {
		// GIVEN
		DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
				"src/test/java/datasets/computerDAO/delete.xml")));
		ComputerDAO.INSTANCE.delete(1454565L);
		final int expectedSize = 1;
		
		// WHEN
		final List<Computer> computers = ComputerDAO.INSTANCE.getAll();
		
		// THEN
		Assertions.assertThat(computers).isNotNull();
		Assertions.assertThat(computers.size()).isEqualTo(expectedSize);
	}
	
	@Test
	public void deleteWithNullIdThrowsException() throws Exception {
		// GIVEN
		DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
				"src/test/java/datasets/computerDAO/delete.xml")));
		final String expectedMessage = "ID must be not null";
		
		// WHEN
		try {
			ComputerDAO.INSTANCE.delete(null);
			Assertions.fail("Must because of null ID");
		} catch (Exception e) {
			Assertions.assertThat(e).isInstanceOf(IllegalArgumentException.class);
			Assertions.assertThat(e.getMessage()).isEqualTo(expectedMessage);
		}
	}

	@After
	public void tearDown() throws Exception {
		DBUtil.databaseTester.onTearDown();
	}

}
