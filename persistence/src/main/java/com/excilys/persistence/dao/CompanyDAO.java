package com.excilys.persistence.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.exception.DAOException;
import com.excilys.exception.ExceptionMessage;
import com.excilys.model.Company;

@Repository
@SuppressWarnings("unchecked")
public class CompanyDAO implements ICompanyDAO {

    private static final String DELETE_COMPANIES = "DELETE FROM Company WHERE id IN ";
	private static final String DELETE_COMPANY = "DELETE FROM Company WHERE id = :id";
	private static final String GET_BY_ID_COMPANY = "FROM Company WHERE id = :id";
	private static final String RETRIEVE_ALL_COMPANIES = "FROM Company";

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CompanyDAO.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Company> getAll() {
		return sessionFactory.getCurrentSession()
				.createQuery(RETRIEVE_ALL_COMPANIES).list();
	}

	@Override
	public Company getById(Long id) {
		if (id == null || id <= 0) {
			LOGGER.error("ID is invalid");
			throw new DAOException(ExceptionMessage.WRONG_ID.toString());
		}
		return (Company) sessionFactory.getCurrentSession()
				.createQuery(GET_BY_ID_COMPANY).setParameter("id", id)
				.uniqueResult();
	}

	@Override
	public void delete(Long id) {
		if (id == null || id <= 0) {
			LOGGER.error("ID is invalid");
			throw new DAOException(ExceptionMessage.WRONG_ID.toString());
		}
		sessionFactory.getCurrentSession().createQuery(DELETE_COMPANY)
				.setParameter("id", id).executeUpdate();
		LOGGER.info("Entity with id {} successfully deleted", id);
	}

}
