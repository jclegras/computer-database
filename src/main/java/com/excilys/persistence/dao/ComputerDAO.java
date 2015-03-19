package com.excilys.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.excilys.exception.DAOException;
import com.excilys.mapper.ComputerMapper;
import com.excilys.model.Computer;
import com.excilys.persistence.ComputerDatabaseConnection;
import com.excilys.util.Page;

public enum ComputerDAO implements DAO<Computer, Long> {
	INSTANCE;

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
		if (id == null) {
			throw new IllegalArgumentException("ID must be not null");
		}
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
		if (entity == null) {
			throw new IllegalArgumentException("Entity must be not null");
		}
		final String sql = "INSERT INTO computer VALUES (?, ?, ?, ?, ?)";

		try (final PreparedStatement pStatement = ComputerDatabaseConnection.INSTANCE
				.getInstance().prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS)) {
			pStatement.setObject(1, null);
			if (entity.getName() != null) {
				pStatement.setString(2, entity.getName());
			}
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
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void update(Computer entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Entity must be not null");
		}
		final String sql = "UPDATE computer SET name = ?, introduced = ?, "
				+ "discontinued = ?, company_id = ? WHERE id = ?";
		try (final PreparedStatement pStatement = ComputerDatabaseConnection.INSTANCE
				.getInstance().prepareStatement(sql)) {
			if (entity.getName() != null) {
				pStatement.setString(1, entity.getName());
			}
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
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void delete(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("ID must be not null");
		}
		final String sql = "DELETE FROM computer WHERE id = ?";
		try (final PreparedStatement pStatement = ComputerDatabaseConnection.INSTANCE
				.getInstance().prepareStatement(sql)) {
			pStatement.setLong(1, id);
			pStatement.execute();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

}
