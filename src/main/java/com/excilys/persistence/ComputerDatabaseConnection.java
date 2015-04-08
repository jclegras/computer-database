package com.excilys.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.exception.PersistenceException;

@Component
public class ComputerDatabaseConnection {

	@Autowired
	private BasicDataSource dataSource;

	/**
	 * @return A connection to the database
	 */
	public Connection getInstance() {
		return getConnection();
	}

	/*
	 * Retrieve a connection for the current thread.
	 */
	private Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}
}
