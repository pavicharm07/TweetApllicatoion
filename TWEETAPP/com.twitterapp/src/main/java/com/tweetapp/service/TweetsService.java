package com.tweetapp.service;

import java.util.Scanner;

import com.tweetapp.dao.AddUserDao;
import com.tweetapp.dao.TweetsDao;

public class TweetsService {

	Scanner sc = new Scanner(System.in);

	public void tweetsMenu(String email) {

		System.out.println("What would you like to do?");
		System.out.println("1.Post a tweet");
		System.out.println("2.View my Tweets");
		System.out.println("3.View all tweets");
		System.out.println("4.Reset password");
		System.out.println("5.Logout");

		int userOption = sc.nextInt();

		switch (userOption) {
		case 1:
			postTweets(email);
			break;

		case 2:
			readMyTweets(email);
			break;

		case 3:
			readAllTweets(email);
			break;

		case 4:
			updatePassword();
			break;

		case 5:
			logout(email);
			break;

		default:
			System.out.println("Please select a option to proceed");
			tweetsMenu(email);
			break;
		}
	}

	//post a tweet
	public void postTweets(String email) {
		TweetsDao tweetsDao = new TweetsDao();
		System.out.println("post your tweet here and press Enter");
		sc.nextLine();
		String userTweet = sc.nextLine();
		if (tweetsDao.postTweets(email, userTweet)) {
			System.out.println("Nice, your post tweeted!");
			tweetsMenu(email);
		}

	}

	public void readMyTweets(String email) {
		TweetsDao tweetsDao = new TweetsDao();
		tweetsDao.viewMyTweets(email);
		tweetsMenu(email);
	}

	public void readAllTweets(String email) {
		TweetsDao tweetsDao = new TweetsDao();
		tweetsDao.readAllTweets();
		tweetsMenu(email);

	}

	public void updatePassword() {
		AddUserDao userDao = new AddUserDao();
		UserService userService = new UserService();
		System.out.println("Enter your Email Id");
		String email = sc.next();
		System.out.println("Enter old password");
		String oldPassword = sc.next();
		System.out.println("Enter new password");
		String newPassword = sc.next();
		if (!userDao.loginUserDao(email, oldPassword)) {
			if (userDao.resetPassword(email, newPassword)) {
				System.out.println("Your Password has been Updated Successfully !\n");
				tweetsMenu(email);
			}
		}

	}

	public void logout(String email) {
		AddUserDao userDao = new AddUserDao();

		if (userDao.logout(email)) {
			System.out.println("You have successfully logged out !");
			UserService userService = new UserService();
			userService.userMenu();
		}
	}

}
