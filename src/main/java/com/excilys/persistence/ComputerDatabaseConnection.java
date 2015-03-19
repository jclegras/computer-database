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
			connection = DriverManager.getConnection(url, properties);
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}

		return connection;
	}

	/*
	 * Load config.properties.
	 */
	private void loadConfigFile() throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		String config = null;
		if ("TEST".equals(System.getProperty("env"))) {
			config = "config-test";
		} else {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			config = "config";
		}
		properties = new Properties();
		try (final InputStream is = ComputerDatabaseConnection.class
				.getClassLoader().getResourceAsStream(config + ".properties")) {
			properties.load(is);
			url = properties.getProperty("url");
		}
	}
}
