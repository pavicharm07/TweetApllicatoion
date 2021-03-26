package com.tweetapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tweetapp.MySqlConnector;

public class TweetsDao {
	Connection connection = MySqlConnector.getConnection();

	public Boolean postTweets(String email, String tweet) {
		Boolean success = false;
		String insertTweet = "insert into tweets(email, tweet) values(?,?)";
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(insertTweet);
			statement.setString(1, email);
			statement.setString(2, tweet);
			statement.executeUpdate();
			success = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success;

	}

	public void viewMyTweets(String email) {
		String viewTweets = "Select * from tweets where email=?";
		try {
			PreparedStatement statement = connection.prepareStatement(viewTweets);
			statement.setString(1, email);

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				System.out.println(resultSet.getString("tweet"));
			}
			if (!resultSet.next()) {
				System.out.println("No tweets yet !!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void readAllTweets() {
		String readAllTweets = "Select email, tweet from tweets";
		try {
			PreparedStatement statement = connection.prepareStatement(readAllTweets);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				System.out.println(resultSet.getString("email") + " tweeted : " + resultSet.getString("tweet"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
