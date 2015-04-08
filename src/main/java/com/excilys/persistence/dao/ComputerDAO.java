package com.excilys.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.exception.DAOException;
import com.excilys.exception.ExceptionMessage;
import com.excilys.mapper.ComputerMapper;
import com.excilys.model.Computer;
import com.excilys.persistence.ComputerDatabaseConnection;
import com.excilys.util.Page;

@Repository
public class ComputerDAO implements DAO<Computer, Long> {
	private static final String DELETE_COMPUTER = "DELETE FROM computer WHERE id = ?";
	private static final String UPDATE_COMPUTER = "UPDATE computer SET name = ?, introduced = ?, "
			+ "discontinued = ?, company_id = ? WHERE id = ?";
	private static final String CREATE_COMPUTER = "INSERT INTO computer VALUES (?, ?, ?, ?, ?)";
	private static final String GET_BY_ID_COMPUTER = "SELECT * FROM computer LEFT OUTER JOIN company"
			+ " ON computer.company_id = company.id WHERE computer.id = ?";
	private static final String GET_ALL_COMPUTERS_PAGINATED = "SELECT * FROM computer LEFT OUTER JOIN company"
			+ " ON computer.company_id = company.id"
			+ " ORDER BY %s %s LIMIT ? OFFSET ?";
	private static final String COUNT_ALL_COMPUTERS = "SELECT COUNT(*) FROM computer";
	private static final String GET_ALL_COMPUTERS = "SELECT * FROM computer LEFT OUTER JOIN company"
			+ " ON computer.company_id = company.id";
	private static final String GET_BY_NAME_COMPUTER_AND_COMPANY = "SELECT * FROM computer"
			+ " LEFT OUTER JOIN company ON computer.company_id = company.id"
			+ " WHERE UCASE(computer.name) LIKE ? or UCASE(company.name) LIKE ?";
    private static final String RETRIEVE_COMPUTERS_BY_COMPANY = "SELECT * FROM computer LEFT OUTER JOIN company"
			+ " ON computer.company_id = company.id WHERE company.id = ?";

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ComputerDAO.class);

	@Autowired
	private ComputerMapper computerMapper;
	@Autowired
	private ComputerDatabaseConnection compDtbConnection;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * Number of computers in the database.
	 *
	 * @return Number of entities
	 */
	public int count() {
		return jdbcTemplate.queryForObject(COUNT_ALL_COMPUTERS, Integer.class);
	}

	@Override
	public List<Computer> getAll() {
        return jdbcTemplate.query(GET_ALL_COMPUTERS, computerMapper);
	}

	public List<Computer> getAll(Page page) {
		if (page == null) {
			throw new DAOException(ExceptionMessage.ARG_NULL.toString());
		}
		final String request = String.format(GET_ALL_COMPUTERS_PAGINATED,
				page.getProperties(), page.getSort().toString());
		return jdbcTemplate.query(request, 
				new Object[] { page.getSize(), page.getOffset() }, computerMapper);
	}

	public List<Computer> getByName(String name) {
		if (name == null) {
			throw new DAOException(ExceptionMessage.ARG_NULL.toString());
		}
		return jdbcTemplate.query(GET_BY_NAME_COMPUTER_AND_COMPANY, 
				new Object[] { "%" + name + "%", "%" + name + "%" }, 
				computerMapper);
	}

	@Override
	public Computer getById(Long id) {
		if (id == null || id <= 0) {
			throw new DAOException(ExceptionMessage.WRONG_ID.toString());
		}
		return jdbcTemplate.queryForObject(GET_BY_ID_COMPUTER, 
				new Object[] { id }, computerMapper);
	}

	@Override
	public void create(Computer entity) {
		if (entity == null) {
			throw new DAOException(ExceptionMessage.ARG_NULL.toString());
		}
		final Connection connection = compDtbConnection.getInstance();

		try (final PreparedStatement pStatement = connection.prepareStatement(
				CREATE_COMPUTER, Statement.RETURN_GENERATED_KEYS)) {
			pStatement.setObject(1, null);
			pStatement.setString(2, entity.getName());
			if (entity.getIntroduced() != null) {
				pStatement.setTimestamp(3,
						Timestamp.valueOf(entity.getIntroduced()));
			} else {
				pStatement.setTimestamp(3, null);
			}
			if (entity.getDiscontinued() != null) {
				pStatement.setTimestamp(4,
						Timestamp.valueOf(entity.getDiscontinued()));
			} else {
				pStatement.setTimestamp(4, null);
			}
			if (entity.getCompany() == null) {
				pStatement.setObject(5, null);
			} else {
				pStatement.setLong(5, entity.getCompany().getId());
			}
			pStatement.execute();

			final ResultSet generatedKeys = pStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				entity.setId(generatedKeys.getLong(1));
			}
			LOGGER.info("Entity with id {} successfully created",
					entity.getId());
		} catch (SQLException e) {
			throw new DAOException(e);
        } finally {
        	try {
				connection.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
        }
	}

	@Override
	public void update(Computer entity) {
		if (entity == null) {
			throw new DAOException(ExceptionMessage.ARG_NULL.toString());
		}
		final Connection connection = compDtbConnection.getInstance();

		try (final PreparedStatement pStatement = connection
				.prepareStatement(UPDATE_COMPUTER)) {
			pStatement.setString(1, entity.getName());
			if (entity.getIntroduced() != null) {
				pStatement.setTimestamp(2,
						Timestamp.valueOf(entity.getIntroduced()));
			} else {
				pStatement.setTimestamp(2, null);
			}
			if (entity.getDiscontinued() != null) {
				pStatement.setTimestamp(3,
						Timestamp.valueOf(entity.getDiscontinued()));
			} else {
				pStatement.setTimestamp(3, null);
			}
			if (entity.getCompany() == null) {
				pStatement.setObject(4, null);
			} else {
				pStatement.setLong(4, entity.getCompany().getId());
			}
			pStatement.setLong(5, entity.getId());
			pStatement.execute();
			LOGGER.info("Entity with id {} successfully updated",
					entity.getId());
		} catch (SQLException e) {
			throw new DAOException(e);
        } finally {
        	try {
				connection.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
        }
	}

	@Override
	public void delete(Long id) {
		if (id == null || id <= 0) {
			throw new DAOException(ExceptionMessage.WRONG_ID.toString());
		}
		jdbcTemplate.update(DELETE_COMPUTER, new Object[] { id });
		LOGGER.info("Entity with id {} successfully deleted", id);		
	}
	
    public List<Computer> getAllByCompany(Long id) {
        if (id == null || id <= 0) {
            throw new DAOException(ExceptionMessage.WRONG_ID.toString());
        }
        return jdbcTemplate.query(RETRIEVE_COMPUTERS_BY_COMPANY, 
        		new Object[] { id }, computerMapper);
    }

}
