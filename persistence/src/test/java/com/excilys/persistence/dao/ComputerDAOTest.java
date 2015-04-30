package com.excilys.persistence.dao;

import com.excilys.ebi.spring.dbunit.test.DataSet;
import com.excilys.ebi.spring.dbunit.test.ExpectedDataSet;
import com.excilys.ebi.spring.dbunit.test.RollbackTransactionalDataSetTestExecutionListener;
import com.excilys.exception.DAOException;
import com.excilys.model.Computer;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:persistence-test-context.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        RollbackTransactionalDataSetTestExecutionListener.class})
@TransactionConfiguration
@Transactional
public class ComputerDAOTest {
    @Autowired
    private ComputerDAO computerDAO;

    @Test
    @DataSet("computer_countNotEmpty.xml")
    public void countWhenNotEmpty() {
        // GIVEN
        final long expectedSize = 3L;

        // WHEN
        final long actualSize = computerDAO.count();

        // THEN
        Assertions.assertThat(actualSize).isEqualTo(expectedSize);
    }

    @Test
    @DataSet("computer_countEmpty.xml")
    public void countWhenEmpty() {
        // GIVEN
        final long expectedSize = 0L;

        // WHEN
        final long actualSize = computerDAO.count();

        // THEN
        Assertions.assertThat(actualSize).isEqualTo(expectedSize);
    }

    @Test
    @DataSet("computer_getAllNotEmpty.xml")
    public void getAllWhenNotEmpty() {
        // GIVEN
        final int expectedSize = 3;
        final List<Computer> expectedComputers = Arrays.asList(
                Computer.builder().id(1L).name("computer1").build(),
                Computer.builder().id(2L).name("computer2").build(),
                Computer.builder().id(3L).name("computer3").build()
        );

        // WHEN
        final List<Computer> computers = computerDAO.getAll();

        // THEN
        Assertions.assertThat(computers.size()).isEqualTo(expectedSize);
        Assertions.assertThat(computers).containsAll(expectedComputers);
    }

    @Test
    @DataSet("computer_getAllEmpty.xml")
    public void getAllWhenEmpty() {
        // GIVEN

        // WHEN
        final List<Computer> computers = computerDAO.getAll();

        // THEN
        Assertions.assertThat(computers).isEmpty();
    }

    @Test
    public void getAllWhenNullPageThrowsException() {
        // GIVEN

        // WHEN
        try {
            computerDAO.getAll(null);
            // THEN KO
            fail("must throw an exception because of null ID");
        } catch (Exception ex) {
            // THEN OK
            Assertions.assertThat(ex).isInstanceOf(DAOException.class);
        }
    }

    // TODO
//    @Test
//    @DataSet("computer_getByName.xml")
//    public void searchWhenSuccess() {
//        // GIVEN
//        final String token = "comp";
//        final int expectedSize = 3;
//
//        // WHEN
//        final List<Computer> computers = computerDAO.getByName(token);
//
//        // THEN
//        Assertions.assertThat(computers.size()).isEqualTo(expectedSize);
//    }

    @Test
    @DataSet("computer_getById.xml")
    public void getByIdSuccess() {
        // GIVEN
        final long expectedId = 1L;
        final Computer expectedComputer = Computer.builder().id(expectedId)
                .name("computer").build();

        // WHEN
        final Computer computer = computerDAO.getById(expectedId);

        // THEN
        Assertions.assertThat(computer).isEqualTo(expectedComputer);
    }

    @Test
    public void getByIdWhenNullIdThrowsException() {
        // GIVEN

        // WHEN
        try {
            computerDAO.getById(null);
            fail("must throw an exception because of null ID");
            // THEN KO
        } catch (Exception ex) {
            // THEN OK
            Assertions.assertThat(ex).isInstanceOf(DAOException.class);
        }
    }

    @Test
    public void getByIdWhenWrongIdThrowsException() {
        // GIVEN

        // WHEN
        try {
            computerDAO.getById(-1L);
            fail("must throw an exception because of illegal ID");
            // THEN KO
        } catch (Exception ex) {
            // THEN OK
            Assertions.assertThat(ex).isInstanceOf(DAOException.class);
        }
    }

    @Test
    public void createSuccess() {
        // GIVEN
        final Computer expectedComputer = Computer.builder().id(1L)
                .name("computer").build();

        // WHEN
        computerDAO.create(expectedComputer);

        // THEN
        final Computer computer = computerDAO.getById(expectedComputer.getId());
        Assertions.assertThat(computer).isEqualTo(expectedComputer);

        // CLEAN
        computerDAO.delete(computer.getId());
    }

    @Test
    @DataSet("computer_update.xml")
    public void updateSuccess() {
        // GIVEN
        final Computer computerToUpdate = computerDAO.getById(1L);
        final String newName = "nameUpdated";

        // WHEN
        computerToUpdate.setName(newName);
        computerDAO.update(computerToUpdate);

        // THEN
        final Computer computerUpdated = computerDAO.getById(1L);
        Assertions.assertThat(computerUpdated.getName()).isEqualTo(newName);
    }

    @Test
    @DataSet("computer_delete.xml")
    @ExpectedDataSet("computer_empty.xml")
    public void deleteSuccess() {
        // GIVEN
        final long id = 1L;

        // WHEN
        computerDAO.delete(id);
    }

    @Test
    public void deleteWhenNullIdThrowsException() {
        // GIVEN

        // WHEN
        try {
            computerDAO.delete(null);
            fail("must throw an exception because of null ID");
        } catch (Exception ex) {
            Assertions.assertThat(ex).isInstanceOf(DAOException.class);
        }
    }

    @Test
    public void deleteWhenWrongIdThrowsException() {
        // GIVEN

        // WHEN
        try {
            computerDAO.delete(-1L);
            // THEN KO
            fail("must throw an exception because of illegal ID");
        } catch (Exception ex) {
            // THEN OK
            Assertions.assertThat(ex).isInstanceOf(DAOException.class);
        }
    }

    @Test
    @DataSet("computer_getAllByCompany.xml")
    public void getByCompanySuccess() {
        // GIVEN
        final long companyId = 1L;
        final int expectedSize = 3;

        // WHEN
        final List<Computer> computers = computerDAO.getAllByCompany(companyId);

        // THEN
        Assertions.assertThat(computers.size()).isEqualTo(expectedSize);
    }

    @Test
    @DataSet("computer_getAllByCompany.xml")
    public void getByCompanyReturnsEmptyListWhenNoComputerForACompany() {
        // GIVEN
        final long companyId = 3L;

        // WHEN
        final List<Computer> computers = computerDAO.getAllByCompany(companyId);

        // THEN
        Assertions.assertThat(computers).isEmpty();
    }

    @Test
    public void getByCompanyReturnsEmptyListWhenNoCompanyForGivenID() {
        // GIVEN
        final long companyId = 34546L;

        // WHEN
        final List<Computer> computers = computerDAO.getAllByCompany(companyId);

        // THEN
        Assertions.assertThat(computers).isEmpty();
    }

    @Test
    public void getByCompanyThrowsExceptionWhenNullID() {
        // GIVEN

        // WHEN
        try {
            computerDAO.getAllByCompany(null);
            // THEN KO
            fail("must throw an exception because of null ID");
        } catch (Exception ex) {
            // THEN OK
            Assertions.assertThat(ex).isInstanceOf(DAOException.class);
        }
    }

    @Test
    public void getByCompanyThrowsExceptionWhenWrongID() {
        // GIVEN

        // WHEN
        try {
            computerDAO.getAllByCompany(-1L);
            // THEN KO
            fail("must throw an exception because of illegal ID");
        } catch (Exception ex) {
            // THEN OK
            Assertions.assertThat(ex).isInstanceOf(DAOException.class);
        }
    }
}
