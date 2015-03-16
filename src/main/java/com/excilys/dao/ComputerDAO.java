package com.excilys.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.excilys.exception.DAOException;
import com.excilys.exception.PersistenceException;
import com.excilys.mapper.ComputerMapper;
import com.excilys.model.Computer;
import com.excilys.persistence.ComputerDatabaseConnection;
import com.excilys.util.Page;

public enum ComputerDAO implements DAO<Computer, Long> {
	INSTANCE;

	@Override
	public List<Computer> getAll() throws DAOException {
		final List<Computer> computers = new ArrayList<>();
		final ComputerMapper computerMapper = new ComputerMapper();

		try (final Statement state = ComputerDatabaseConnection.INSTANCE
				.getInstance().createStatement()) {
			try (final ResultSet rs = state
					.executeQuery("SELECT * FROM computer")) {
				while (rs.next()) {
					computers.add(computerMapper.rowMap(rs));
				}
			}
		} catch (SQLException | PersistenceException e) {
			throw new DAOException(e.getMessage());
		}

		return computers;
	}
	
	public List<Computer> getAll(Page page) throws DAOException {
		final List<Computer> computers = new ArrayList<>();
		final ComputerMapper computerMapper = new ComputerMapper();
		final String sql = "SELECT * FROM computer ORDER BY %s %s LIMIT %d OFFSET %d";

		try (final Statement state = ComputerDatabaseConnection.INSTANCE
				.getInstance().createStatement()) {
			try (final ResultSet rs = state.executeQuery(String.format(sql,
					page.getProperties(), page.getSort(), page.getSize(),
					page.getOffset()))) {
				while (rs.next()) {
					computers.add(computerMapper.rowMap(rs));
				}
			}
		} catch (SQLException | PersistenceException e) {
			throw new DAOException(e.getMessage());
		}

		return computers;
	}

	@Override
	public Computer getById(Long id) throws DAOException {
		final ComputerMapper computerMapper = new ComputerMapper();
		final String sql = "SELECT * FROM computer WHERE id = ?";

		try (final PreparedStatement pStatement = ComputerDatabaseConnection.INSTANCE
				.getInstance().prepareStatement(sql)) {
			pStatement.setLong(1, id);
			try (final ResultSet rs = pStatement.executeQuery()) {
				if (rs.first()) {
					return computerMapper.rowMap(rs);
				}
			}
		} catch (SQLException | PersistenceException e) {
			throw new DAOException(e.getMessage());
		}

		return null;
	}

	@Override
	public Long create(Computer entity) throws DAOException {
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
			if (entity.getCompanyId() == 0) {
				pStatement.setObject(5, null);
			} else {
				pStatement.setLong(5, entity.getCompanyId());
			}
			pStatement.execute();
			
			try (final ResultSet generatedKeys = pStatement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					return generatedKeys.getLong(1);
				}
			}
		} catch (SQLException | PersistenceException e) {
			throw new DAOException(e.getMessage());
		}
		
		return null;
	}

	@Override
	public void update(Computer entity) throws DAOException {
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
			if (entity.getCompanyId() == 0) {
				pStatement.setObject(4, null);
			} else {
				pStatement.setLong(4, entity.getCompanyId());
			}
			pStatement.setLong(5, entity.getId());
			pStatement.execute();
		} catch (SQLException | PersistenceException e) {
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public void delete(Long id) throws DAOException {
		final String sql = "DELETE FROM computer WHERE id = ?";
		try (final PreparedStatement pStatement = ComputerDatabaseConnection.INSTANCE
				.getInstance().prepareStatement(sql)) {
			pStatement.setLong(1, id);
			pStatement.execute();
		} catch (SQLException | PersistenceException e) {
			throw new DAOException(e.getMessage());
		}
	}

}
