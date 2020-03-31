package com.revature.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	public static final String url = "jdbc:postgresql://my-database-1.ch8dgjpaxstr.us-east-2.rds.amazonaws.com:5432/jdbcdb";
	public static final String username = "jdbc_user";
	public static final String password = "password";
	
	public static Connection connect() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
}
