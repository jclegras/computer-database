package com.excilys.persistence.dao;

import com.excilys.ebi.spring.dbunit.test.DataSet;
import com.excilys.ebi.spring.dbunit.test.ExpectedDataSet;
import com.excilys.ebi.spring.dbunit.test.RollbackTransactionalDataSetTestExecutionListener;
import com.excilys.exception.DAOException;
import com.excilys.model.Company;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:persistence-test-context.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        RollbackTransactionalDataSetTestExecutionListener.class })
@TransactionConfiguration
@Transactional
public class CompanyDAOTest {
    @Autowired
    private CompanyDAO companyDAO;
	
	@Test
    @DataSet("company_getAllNotEmpty.xml")
	public void getAllWhenNotEmpty() {
		// GIVEN
        final int expectedSize = 3;
        final List<Company> expectedCompanies = Arrays.asList(
                Company.builder().id(1L).name("company1").build(),
                Company.builder().id(2L).name("company2").build(),
                Company.builder().id(3L).name("company3").build()
        );

        // WHEN
        final List<Company> companies = companyDAO.getAll();

        // THEN
        Assertions.assertThat(companies.size()).isEqualTo(expectedSize);
        Assertions.assertThat(companies).containsAll(expectedCompanies);
	}

    @Test
    @DataSet("company_getAllEmpty.xml")
    public void getAllWhenEmpty() {
        // GIVEN

        // WHEN
        final List<Company> companies = companyDAO.getAll();

        // THEN
        Assertions.assertThat(companies).isNotNull();
        Assertions.assertThat(companies).isEmpty();
    }

    @Test
    @DataSet("company_getById.xml")
    public void getByIdSuccess() {
        // GIVEN
        final long expectedId = 1L;
        final Company expectedCompany = Company.builder().id(1L)
                .name("company1").build();

        // WHEN
        final Company company = companyDAO.getById(expectedId);

        // THEN
        Assertions.assertThat(company).isEqualTo(expectedCompany);
    }

    @Test
    public void getByIdWhenNullIdThrowsException() {
        // GIVEN

        // WHEN
        try {
            companyDAO.getById(null);
            // THEN KO
            Assert.fail("must throw an exception because of null ID");
        } catch (DAOException ex) {
            // THEN OK
            Assertions.assertThat(ex).isInstanceOf(DAOException.class);
        }
    }

    @Test
    public void getByIdWhenWrongIdThrowsException() {
        // GIVEN

        // WHEN
        try {
            companyDAO.getById(-1L);
            // THEN KO
            Assert.fail("must throw an exception because of illegal ID");
        } catch (DAOException ex) {
            // THEN OK
            Assertions.assertThat(ex).isInstanceOf(DAOException.class);
        }
    }

    @Test
    public void getByIdReturnsNullWhenNotExistsID() {
        // GIVEN

        // WHEN
        final Company company = companyDAO.getById(7879465L);

        // THEN
        Assertions.assertThat(company).isNull();
    }

    @Test
    @DataSet("company_delete.xml")
    @ExpectedDataSet("company_empty.xml")
    public void deleteSuccess() {
        // GIVEN
        final long id = 1L;

        // WHEN
        companyDAO.delete(id);
    }

    @Test
    public void deleteWhenNullIdThrowsException() {
        // GIVEN

        // WHEN
        try {
            companyDAO.delete(null);
            // THEN KO
            Assert.fail("must throw an exception because of null ID");
        } catch (DAOException ex) {
            // THEN OK
            Assertions.assertThat(ex).isInstanceOf(DAOException.class);
        }
    }

    @Test
    public void deleteWhenWrongIdThrowsException() {
        // GIVEN

        // WHEN
        try {
            companyDAO.delete(-1L);
            // THEN KO
            Assert.fail("must throw an exception because of illegal ID");
        } catch (DAOException ex) {
            // THEN OK
            Assertions.assertThat(ex).isInstanceOf(DAOException.class);
        }
    }

    @Test
    @DataSet("company_delete.xml")
    @ExpectedDataSet("company_delete.xml")
    public void deleteSuccessWhenNotExistsID() {
        // GIVEN

        // WHEN
        companyDAO.delete(7879465L);
    }
}
