package com.excilys.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.excilys.exception.DAOException;
import com.excilys.exception.ExceptionMessage;
import com.excilys.model.Computer;
import com.excilys.persistence.ComputerDatabaseConnection;
import com.excilys.util.Page;

@Repository
public class ComputerDAO implements IComputerDAO {
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
	private RowMapper<Computer> computerMapper;
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

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
				new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement ps = connection.prepareStatement(CREATE_COMPUTER, 
								new String[] { "id" });
						ps.setObject(1, null);
						ps.setString(2, entity.getName());
						if (entity.getIntroduced() != null) {
							ps.setTimestamp(3,
									Timestamp.valueOf(entity.getIntroduced()));
						} else {
							ps.setTimestamp(3, null);
						}
						if (entity.getDiscontinued() != null) {
							ps.setTimestamp(4,
									Timestamp.valueOf(entity.getDiscontinued()));
						} else {
							ps.setTimestamp(4, null);
						}
						if (entity.getCompany() == null) {
							ps.setObject(5, null);
						} else {
							ps.setLong(5, entity.getCompany().getId());
						}
						return ps;
					}
				},
				keyHolder);
		entity.setId((long) keyHolder.getKey());
		LOGGER.info("Entity with id {} successfully created",
				entity.getId());
	}

	@Override
	public void update(Computer entity) {
		if (entity == null) {
			throw new DAOException(ExceptionMessage.ARG_NULL.toString());
		}
		final List<Object> args = new LinkedList<>();
		args.add(entity.getName());
		if (entity.getIntroduced() == null) {
			args.add(null);
		} else {
			args.add(Timestamp.valueOf(entity.getIntroduced()));
		}
		if (entity.getDiscontinued() == null) {
			args.add(null);
		} else {
			args.add(Timestamp.valueOf(entity.getDiscontinued()));
		}
		if (entity.getCompany() == null) {
			args.add(null);
		} else {
			args.add(entity.getCompany().getId());
		}
		args.add(entity.getId());
		jdbcTemplate.update(UPDATE_COMPUTER, args.toArray());
		LOGGER.info("Entity with id {} successfully updated",
				entity.getId());
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
