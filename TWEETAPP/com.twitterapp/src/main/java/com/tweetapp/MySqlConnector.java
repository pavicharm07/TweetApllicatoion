package com.tweetapp;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class MySqlConnector {
	static Connection connection = null;
	static {
		try {
			Properties properties = new Properties();
			FileInputStream inputFile = new FileInputStream(
					"C:\\com.twitterapp\\src\\main\\resources\\application.properties");
			properties.load(inputFile);
			inputFile.close();
			String url = properties.getProperty("connection-url");
			String username = properties.getProperty("user");
			String password = properties.getProperty("password");
			String driverName = properties.getProperty("driver");

			Class.forName(driverName);
			connection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return connection;
	}
}