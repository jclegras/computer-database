package com.excilys.service;

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

import com.excilys.ebi.spring.dbunit.test.DataSet;
import com.excilys.ebi.spring.dbunit.test.ExpectedDataSet;
import com.excilys.ebi.spring.dbunit.test.RollbackTransactionalDataSetTestExecutionListener;
import com.excilys.persistence.dao.ICompanyDAO;
import com.excilys.persistence.dao.IComputerDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-test-context.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        RollbackTransactionalDataSetTestExecutionListener.class })
@TransactionConfiguration
@Transactional
public class CompanyServiceTest {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private IComputerDAO computerDAO;
    @Autowired
    private ICompanyDAO companyDAO;

    @Test
    @DataSet("company_delete.xml")
    @ExpectedDataSet("empty.xml")
    public void deleteInCascadeSuccess() {
        // GIVEN
        final long id = 1L;
        
        // WHEN
        companyService.delete(id);
    }

    // TODO
//    @Test
//    @DataSet("company_delete.xml")
//    public void deleteInCascadeRollbacksWhenRuntimeException() {
//        // GIVEN
//        final long id = 1L;
//        companyService.setCompanyDAO(null);
//
//        // WHEN
//        try {
//            companyService.delete(id);	
//        } catch (Exception ex) {
//        }
//    	Assertions.assertThat(computerDAO.count()).isEqualTo(3);
//    }
}