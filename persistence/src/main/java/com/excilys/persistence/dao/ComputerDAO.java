package com.excilys.persistence.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.exception.DAOException;
import com.excilys.exception.ExceptionMessage;
import com.excilys.model.Computer;
import com.excilys.persistence.util.Page;

@Repository
@SuppressWarnings("unchecked")
public class ComputerDAO implements IComputerDAO {
	private static final String DELETE_COMPUTER = "DELETE FROM Computer WHERE id = :id";
	private static final String GET_BY_ID_COMPUTER = "SELECT computer FROM Computer computer "
			+ " LEFT OUTER JOIN computer.company company"
			+ " WHERE computer.id = :id";
	private static final String GET_ALL_COMPUTERS_PAGINATED = "	SELECT computer FROM Computer computer "
			+ "LEFT OUTER JOIN computer.company company ORDER BY %s %s";
	private static final String COUNT_ALL_COMPUTERS = "SELECT COUNT(*) FROM Computer";
	private static final String GET_ALL_COMPUTERS = "FROM Computer";
	private static final String GET_BY_NAME_COMPUTER_AND_COMPANY = "SELECT computer FROM Computer computer"
			+ " LEFT OUTER JOIN computer.company company"
			+ " WHERE UPPER(computer.name) LIKE :computerName"
			+ " or UPPER(company.name) LIKE :companyName";
	private static final String RETRIEVE_COMPUTERS_BY_COMPANY = "SELECT computer FROM Computer computer"
			+ " LEFT OUTER JOIN computer.company company"
			+ " WHERE company.id = :id";

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ComputerDAO.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Long count() {
		return (Long) sessionFactory.getCurrentSession()
				.createQuery(COUNT_ALL_COMPUTERS).uniqueResult();
	}

	@Override
	public List<Computer> getAll() {
		return sessionFactory.getCurrentSession()
				.createQuery(GET_ALL_COMPUTERS).list();
	}

	@Override
	public List<Computer> getAll(Page page) {
		if (page == null) {
			throw new DAOException(ExceptionMessage.ARG_NULL.toString());
		}
		final String statement = String.format(GET_ALL_COMPUTERS_PAGINATED,
				page.getProperties(), page.getSort().toString());
		return sessionFactory.getCurrentSession().createQuery(statement)
				.setMaxResults(page.getSize()).setFirstResult(page.getOffset())
				.list();
	}

	@Override
	public List<Computer> getByName(String name) {
		if (name == null) {
			throw new DAOException(ExceptionMessage.ARG_NULL.toString());
		}
		final Query query = sessionFactory.getCurrentSession().createQuery(
				GET_BY_NAME_COMPUTER_AND_COMPANY);
		query.setParameter("computerName", "%" + name + "%").setParameter(
				"companyName", "%" + name + "%");
		return query.list();
	}

	@Override
	public Computer getById(Long id) {
		if (id == null || id <= 0) {
			throw new DAOException(ExceptionMessage.WRONG_ID.toString());
		}
		return (Computer) sessionFactory.getCurrentSession()
				.createQuery(GET_BY_ID_COMPUTER).setParameter("id", id)
				.uniqueResult();
	}

	@Override
	public void create(Computer entity) {
		if (entity == null) {
			throw new DAOException(ExceptionMessage.ARG_NULL.toString());
		}
		sessionFactory.getCurrentSession().save(entity);
		LOGGER.info("Entity with id {} successfully created", entity.getId());
	}

	@Override
	public void update(Computer entity) {
		if (entity == null) {
			throw new DAOException(ExceptionMessage.ARG_NULL.toString());
		}
		sessionFactory.getCurrentSession().update(entity);
		LOGGER.info("Entity with id {} successfully updated", entity.getId());
	}

	@Override
	public void delete(Long id) {
		if (id == null || id <= 0) {
			throw new DAOException(ExceptionMessage.WRONG_ID.toString());
		}
		sessionFactory.getCurrentSession().createQuery(DELETE_COMPUTER)
				.setParameter("id", id).executeUpdate();
		LOGGER.info("Entity with id {} successfully deleted", id);
	}

	@Override
	public List<Computer> getAllByCompany(Long id) {
		if (id == null || id <= 0) {
			throw new DAOException(ExceptionMessage.WRONG_ID.toString());
		}
		return sessionFactory.getCurrentSession().createQuery(RETRIEVE_COMPUTERS_BY_COMPANY)
			.setParameter("id", id).list();
	}

}
