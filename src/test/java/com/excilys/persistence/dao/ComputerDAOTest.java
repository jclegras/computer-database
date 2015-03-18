package com.excilys.persistence.dao;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.After;
import org.junit.BeforeClass;

import com.excilys.util.DBUtil;

public class ComputerDAOTest {

	@BeforeClass
	public static void setUpDB() {
		System.setProperty("env", "TEST");
		try {
			DBUtil.executeSqlFile("test.sql", DBUtil.getConnection());
		} catch (IOException | SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@After 
	public void tearDown() throws Exception {
		DBUtil.databaseTester.onTearDown();
	}

}
