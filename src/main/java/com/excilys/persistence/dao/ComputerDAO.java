package com.excilys.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.exception.DAOException;
import com.excilys.exception.ExceptionMessage;
import com.excilys.mapper.ComputerMapper;
import com.excilys.model.Computer;
import com.excilys.persistence.ComputerDatabaseConnection;
import com.excilys.util.Page;

public enum ComputerDAO implements DAO<Computer, Long> {
	INSTANCE;

	private static final String DELETE_COMPUTER = "DELETE FROM computer WHERE id = ?";
	private static final String UPDATE_COMPUTER = "UPDATE computer SET name = ?, introduced = ?, "
			+ "discontinued = ?, company_id = ? WHERE id = ?";
	private static final String CREATE_COMPUTER = "INSERT INTO computer VALUES (?, ?, ?, ?, ?)";
	private static final String GET_BY_ID_COMPUTER = "SELECT * FROM computer LEFT OUTER JOIN company"
			+ " ON computer.company_id = company.id WHERE computer.id = ?";
	private static final String GET_ALL_COMPUTERS_PAGINATED = "SELECT * FROM computer LEFT OUTER JOIN company"
			+ " ON computer.company_id = company.id"
			+ " ORDER BY ? ? LIMIT ? OFFSET ?";
	private static final String COUNT_ALL_COMPUTERS = "SELECT COUNT(*) FROM computer";
	private static final String GET_ALL_COMPUTERS = "SELECT * FROM computer LEFT OUTER JOIN company"
			+ " ON computer.company_id = company.id";

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ComputerDAO.class);

	private final ComputerMapper computerMapper = ComputerMapper.INSTANCE;

	/**
	 * Number of computers in the database.
	 *
	 * @return Number of entities
	 */
	public int count() {
		try (final Statement state = ComputerDatabaseConnection.INSTANCE
				.getInstance().createStatement()) {
			final ResultSet rs = state.executeQuery(COUNT_ALL_COMPUTERS);
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return 0;
	}

	@Override
	public List<Computer> getAll() {
		final List<Computer> computers = new ArrayList<>();

		try (final Statement state = ComputerDatabaseConnection.INSTANCE
				.getInstance().createStatement()) {
			final ResultSet rs = state.executeQuery(GET_ALL_COMPUTERS);
			while (rs.next()) {
				computers.add(computerMapper.rowMap(rs));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}

		return computers;
	}

	public List<Computer> getAll(Page page) {
		if (page == null) {
			throw new DAOException(ExceptionMessage.ARG_NULL.toString());
		}
		final List<Computer> computers = new ArrayList<>();

		try (final PreparedStatement pStatement = ComputerDatabaseConnection.INSTANCE
				.getInstance().prepareStatement(GET_ALL_COMPUTERS_PAGINATED)) {
			pStatement.setString(1, page.getProperties());
			pStatement.setString(2, page.getSort().toString());
			pStatement.setInt(3, page.getSize());
			pStatement.setInt(4, page.getOffset());
			final ResultSet rs = pStatement.executeQuery();
			while (rs.next()) {
				computers.add(computerMapper.rowMap(rs));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}

		return computers;
	}

	@Override
	public Computer getById(Long id) {
		if (id == null || id <= 0) {
			throw new DAOException(ExceptionMessage.WRONG_ID.toString());
		}
		try (final PreparedStatement pStatement = ComputerDatabaseConnection.INSTANCE
				.getInstance().prepareStatement(GET_BY_ID_COMPUTER)) {
			pStatement.setLong(1, id);
			final ResultSet rs = pStatement.executeQuery();
			if (rs.first()) {
				return computerMapper.rowMap(rs);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}

		return null;
	}

	@Override
	public void create(Computer entity) {
		if (entity == null) {
			throw new DAOException(ExceptionMessage.ARG_NULL.toString());
		}
		try (final PreparedStatement pStatement = ComputerDatabaseConnection.INSTANCE
				.getInstance().prepareStatement(CREATE_COMPUTER,
						Statement.RETURN_GENERATED_KEYS)) {
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
		}
	}

	@Override
	public void update(Computer entity) {
		if (entity == null) {
			throw new DAOException(ExceptionMessage.ARG_NULL.toString());
		}
		try (final PreparedStatement pStatement = ComputerDatabaseConnection.INSTANCE
				.getInstance().prepareStatement(UPDATE_COMPUTER)) {
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
		}
	}

	@Override
	public void delete(Long id) {
		if (id == null || id <= 0) {
			throw new DAOException(ExceptionMessage.WRONG_ID.toString());
		}
		try (final PreparedStatement pStatement = ComputerDatabaseConnection.INSTANCE
				.getInstance().prepareStatement(DELETE_COMPUTER)) {
			pStatement.setLong(1, id);
			pStatement.execute();
			LOGGER.info("Entity with id {} successfully deleted", id);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

}
