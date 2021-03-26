package com.tweetapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.tweetapp.MySqlConnector;
import com.tweetapp.business.User;

public class AddUserDao {
	Connection connection = MySqlConnector.getConnection();

	public Boolean addNewUser(User newUser) {
		String addNewUserQuery = "insert into user(first_name, last_name,gender, email, dob, password,logged_in) values(?,?,?,?,?,?,?)";
		Boolean success = false;
		try {
			PreparedStatement statement = connection.prepareStatement(addNewUserQuery);
			statement.setString(1, newUser.getFirstName());
			statement.setString(2, newUser.getLastName());
			statement.setString(3, newUser.getGender());
			statement.setString(4, newUser.getEmail());
			statement.setString(5, newUser.getDob());
			statement.setString(6, newUser.getPassword());
			statement.setBoolean(7, newUser.logged_in);
			statement.executeUpdate();
			success = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			success = false;
		}

		return success;
	}

	public Boolean loginUserDao(String email, String password) {
		String checkCredentials = "Update user set logged_in =? where email=? and password=?";
		Boolean isValid = false;
		try {
			PreparedStatement statement = connection.prepareStatement(checkCredentials);
			statement.setBoolean(1, true);
			statement.setString(2, email);
			statement.setString(3, password);
			int resultSet = statement.executeUpdate();
			if (resultSet > 0) {
				isValid = true;
			}

		} catch (SQLException e) {
			isValid = false;
		}
		return isValid;

	}

	public Boolean resetPassword(String email, String password) {
		String resetQuery = "update user set password = ?, logged_in=? where email=?";
		Boolean success = false;
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(resetQuery);
			statement.setString(1, password);
			statement.setBoolean(2, true);
			statement.setString(3, email);
			statement.executeUpdate();
			success = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success;

	}

	public Boolean logout(String email) {
		String logoutQuery = "Update user set logged_in=? where email=?";
		Boolean success = false;
		try {
			PreparedStatement statement = connection.prepareStatement(logoutQuery);
			statement.setBoolean(1, false);
			statement.setString(2, email);
			success = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success;
	}
}
