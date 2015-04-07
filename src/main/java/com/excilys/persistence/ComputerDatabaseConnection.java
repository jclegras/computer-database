package com.excilys.persistence;

import com.excilys.exception.PersistenceException;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Component
public class ComputerDatabaseConnection {

	@Autowired
	private BasicDataSource dataSource;

	private final ThreadLocal<Connection> CONNECTION = new ThreadLocal<Connection>() {
		@Override
	    protected Connection initialValue() {
	        try {
				return dataSource.getConnection();
			} catch (SQLException e) {
				throw new PersistenceException(e);
			}
	    }
	};

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
		return CONNECTION.get();
	}
}
