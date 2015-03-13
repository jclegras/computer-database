package com.excilys.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.mapper.ComputerMapper;
import com.excilys.model.Computer;
import com.excilys.persistence.ComputerDatabaseConnection;

public enum ComputerDAO implements DAO<Computer, Long> {
	INSTANCE;
	
	@Override
	public List<Computer> getAll() {
		final List<Computer> computers = new ArrayList<>();
		final ComputerMapper computerMapper = new ComputerMapper();
		
		try {
			try (final Statement state = ComputerDatabaseConnection.INSTANCE.getInstance().createStatement()) {
				try (final ResultSet rs = state.executeQuery("SELECT * FROM computer")) {
					while (rs.next()) {
						computers.add(computerMapper.rowMap(rs));
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return computers;
	}

	@Override
	public Computer getById(Long id) {
		final ComputerMapper computerMapper = new ComputerMapper();
		final String sql = "SELECT * FROM computer WHERE id = ?";
		
		try {
			try (final PreparedStatement pStatement = ComputerDatabaseConnection.INSTANCE.getInstance().prepareStatement(sql)) {
				pStatement.setLong(1, id);
				try (final ResultSet rs = pStatement.executeQuery()) {
					if (rs.first()) {
						return computerMapper.rowMap(rs);
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Long create(Computer entity) {
		final String sql = "INSERT INTO computer VALUES (?, ?, ?, ?, ?)";
		
		try {
			try (final PreparedStatement pStatement = ComputerDatabaseConnection.INSTANCE.getInstance().prepareStatement(sql)) {
				pStatement.setLong(1, entity.getId());
				if (entity.getName() != null) {
					pStatement.setString(2, entity.getName());
				}
//				if (entity.getIntroduced() != null) {
//					pStatement.setTimestamp(3, entity.getIntroduced());	
//				}
//				if (entity.getDiscontinued() != null) {
//					pStatement.setTimestamp(4, entity.getDiscontinued());	
//				}
				if (entity.getCompany() != null) {
					pStatement.setLong(5, entity.getCompany().getId());	
				}
				pStatement.execute();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(Computer entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
