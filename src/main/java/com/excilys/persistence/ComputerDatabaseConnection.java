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

	private String url;
	private String username;
	private String password;

	public Connection getInstance() throws PersistenceException {
		Connection connection = null;
		
		try {
			loadConfigFile();
			connection = DriverManager.getConnection(url,
					username, password);
		} catch (SQLException | IOException e) {
			throw new PersistenceException(e.getMessage());
		}
		
		return connection;
	}

	private synchronized void loadConfigFile() throws IOException {
		if (url == null) {
			final Properties properties = new Properties();
			try (final InputStream is = ComputerDatabaseConnection.class
					.getClassLoader().getResourceAsStream("config.properties")) {
				properties.load(is);
				url = properties.getProperty("url");
				username = properties.getProperty("username");
				password = properties.getProperty("password");
			}	
		}
	}
}
