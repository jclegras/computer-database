package com.excilys.persistence.dao;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
import com.excilys.model.Computer;
import com.excilys.persistence.ComputerDatabaseConnection;
import com.excilys.util.DBUtil;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/test/resources/applicationContext.xml")
public class ComputerDAOTest {
	@Autowired
	private IComputerDAO computerDAO;
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
    public void countWhenSeveralEntities() throws Exception {
        // GIVEN
        DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
                "src/test/resources/datasets/computerDAO/count.xml")));
        final int expectedNbComputers = 3;

        // WHEN
        final long nbComputers = computerDAO.count();

        // THEN
        Assertions.assertThat(nbComputers).isEqualTo(expectedNbComputers);
    }

    @Test
    public void countWhenNoEntity() throws Exception {
        // GIVEN
        DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
                "src/test/resources/datasets/computerDAO/getAllNoEntity.xml")));

        // WHEN
        final long nbComputers = computerDAO.count();

        // THEN
        Assertions.assertThat(nbComputers).isZero();
    }

    @Test
    public void getAllWithSuccess() throws Exception {
        // GIVEN
        DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
                "src/test/resources/datasets/computerDAO/getAll.xml")));
        final int expectedSize = 1;
        final Company company1 = new Company(1, "IBM");
        final Computer computer1 = new Computer(1L, "CM-200", LocalDateTime.of(
                1991, 1, 1, 0, 0, 0), null, company1);

        // WHEN
        final List<Computer> computers = computerDAO.getAll();

        // THEN
        Assertions.assertThat(computers).isNotNull();
        Assertions.assertThat(computers.size()).isEqualTo(expectedSize);
        Assertions.assertThat(computers).contains(computer1);
    }

    @Test
    public void getAllWhenNoEntityGiveEmptyList() throws Exception {
        // GIVEN
        DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
                "src/test/resources/datasets/computerDAO/getAllNoEntity.xml")));

        // WHEN
        final List<Computer> computers = computerDAO.getAll();

        // THEN
        Assertions.assertThat(computers).isNotNull();
        Assertions.assertThat(computers).isEmpty();
    }

    @Test
    public void getByIdWithSuccess() throws Exception {
        // GIVEN
        DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
                "src/test/resources/datasets/computerDAO/getById.xml")));
        final long id = 1L;
        final long expectedId = 1L;
        final String expectedName = "CM-200";

        // WHEN
        final Computer computer = computerDAO.getById(id);

        // THEN
        Assertions.assertThat(computer).isNotNull();
        Assertions.assertThat(computer.getId()).isEqualTo(expectedId);
        Assertions.assertThat(computer.getName()).isEqualTo(expectedName);
    }

    @Test
    public void getByIdWhenNotFoundGivesANullResult() throws Exception {
        // GIVEN
        DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
                "src/test/resources/datasets/computerDAO/getById.xml")));
        final long id = 45456L;

        // WHEN
        final Computer computer = computerDAO.getById(id);

        // THEN
        Assertions.assertThat(computer).isNull();
    }

    @Test
    public void createWithSuccess() throws Exception {
        // GIVEN
        DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
                "src/test/resources/datasets/computerDAO/create.xml")));
        final Computer computer = new Computer(0, "name",
                LocalDateTime.now(), null, null);
        final int expectedSize = 1;
        computerDAO.create(computer);

        // WHEN
        final List<Computer> computers = computerDAO.getAll();

        // THEN
        Assertions.assertThat(computers).isNotNull();
        Assertions.assertThat(computers.size()).isEqualTo(expectedSize);
        Assertions.assertThat(computers.get(0).getName()).isEqualTo("name");

        // CLEAN
        computerDAO.delete(1L);
    }

    @Test
    public void updateWithSuccess() throws Exception {
        // GIVEN
        DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
                "src/test/resources/datasets/computerDAO/update.xml")));
        final String expectedName = "newName";
        Computer computer = computerDAO.getById(1L);
        computer.setName(expectedName);
        computerDAO.update(computer);

        // WHEN
        computer = computerDAO.getById(1L);

        // THEN
        Assertions.assertThat(computer).isNotNull();
        Assertions.assertThat(computer.getName()).isEqualTo(expectedName);
    }

    @Test
    public void deleteWithSuccess() throws Exception {
        // GIVEN
        DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
                "src/test/resources/datasets/computerDAO/delete.xml")));
        computerDAO.delete(1L);

        // WHEN
        final List<Computer> computers = computerDAO.getAll();

        // THEN
        Assertions.assertThat(computers).isNotNull();
        Assertions.assertThat(computers).isEmpty();
    }

    @Test
    public void deleteWithWrongId() throws Exception {
        // GIVEN
        DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
                "src/test/resources/datasets/computerDAO/delete.xml")));
        computerDAO.delete(1454565L);
        final int expectedSize = 1;

        // WHEN
        final List<Computer> computers = computerDAO.getAll();

        // THEN
        Assertions.assertThat(computers).isNotNull();
        Assertions.assertThat(computers.size()).isEqualTo(expectedSize);
    }

}
