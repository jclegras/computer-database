package com.excilys.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.excilys.exception.PersistenceException;

public enum ComputerDatabaseConnection {
	INSTANCE;

	private Properties properties;
	private String url;

	ComputerDatabaseConnection() {
		try {
			loadConfigFile();
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	public Connection getInstance() {
		Connection connection = null;

		try {
			Class.forName(properties.getProperty("driver")).newInstance();
			connection = DriverManager.getConnection(url, properties);
		} catch (SQLException | InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			throw new PersistenceException(e);
		}

		return connection;
	}

	/*
	 * Load config.properties.
	 */
	private void loadConfigFile() throws IOException {
		properties = new Properties();
		try (final InputStream is = ComputerDatabaseConnection.class
				.getClassLoader().getResourceAsStream("config.properties")) {
			properties.load(is);
			url = properties.getProperty("url");
		}
	}
}
