package com.excilys.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum ComputerDatabaseConnection {
	INSTANCE("jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull", "root", "root");
	
	private final String url;
	private final String username;
	private final String password;
	private Connection connection;
	
	
	ComputerDatabaseConnection(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	public Connection getInstance() throws SQLException {
		if (connection == null) {
			connection = DriverManager.getConnection(url, username, password);
		}
		return connection;
	}
}
