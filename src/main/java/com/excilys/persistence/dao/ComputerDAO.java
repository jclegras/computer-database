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
import com.excilys.mapper.ComputerMapper;
import com.excilys.model.Computer;
import com.excilys.persistence.ComputerDatabaseConnection;
import com.excilys.util.Page;

public enum ComputerDAO implements DAO<Computer, Long> {
	INSTANCE;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ComputerDAO.class);

	/**
	 * Number of computers in the database.
	 *
	 * @return Number of entities
	 */
	public int count() {
		final String sql = "SELECT COUNT(*) FROM computer";
		try (final Statement state = ComputerDatabaseConnection.INSTANCE
				.getInstance().createStatement()) {
			final ResultSet rs = state.executeQuery(sql);
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
		final String sql = "SELECT * FROM computer LEFT OUTER JOIN company"
				+ " ON computer.company_id = company.id";
		final List<Computer> computers = new ArrayList<>();
		final ComputerMapper computerMapper = new ComputerMapper();

		try (final Statement state = ComputerDatabaseConnection.INSTANCE
				.getInstance().createStatement()) {
			final ResultSet rs = state.executeQuery(sql);
			while (rs.next()) {
				computers.add(computerMapper.rowMap(rs));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}

		return computers;
	}

	public List<Computer> getAll(Page page) {
		final List<Computer> computers = new ArrayList<>();
		final ComputerMapper computerMapper = new ComputerMapper();
		final String sql = "SELECT * FROM computer LEFT OUTER JOIN company"
				+ " ON computer.company_id = company.id"
				+ " ORDER BY ? ? LIMIT ? OFFSET ?";

		try (final PreparedStatement pStatement = ComputerDatabaseConnection.INSTANCE
				.getInstance().prepareStatement(sql)) {
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
		final ComputerMapper computerMapper = new ComputerMapper();
		final String sql = "SELECT * FROM computer LEFT OUTER JOIN company"
				+ " ON computer.company_id = company.id WHERE computer.id = ?";

		try (final PreparedStatement pStatement = ComputerDatabaseConnection.INSTANCE
				.getInstance().prepareStatement(sql)) {
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
		final String sql = "INSERT INTO computer VALUES (?, ?, ?, ?, ?)";

		try (final PreparedStatement pStatement = ComputerDatabaseConnection.INSTANCE
				.getInstance().prepareStatement(sql,
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
		final String sql = "UPDATE computer SET name = ?, introduced = ?, "
				+ "discontinued = ?, company_id = ? WHERE id = ?";
		try (final PreparedStatement pStatement = ComputerDatabaseConnection.INSTANCE
				.getInstance().prepareStatement(sql)) {
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
		final String sql = "DELETE FROM computer WHERE id = ?";
		try (final PreparedStatement pStatement = ComputerDatabaseConnection.INSTANCE
				.getInstance().prepareStatement(sql)) {
			pStatement.setLong(1, id);
			pStatement.execute();
			LOGGER.info("Entity with id {} successfully deleted", id);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

}
