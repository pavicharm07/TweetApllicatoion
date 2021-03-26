package com.tweetapp.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.tweetapp.business.User;
import com.tweetapp.dao.AddUserDao;

public class UserService {
	Scanner sc = new Scanner(System.in);

	public void userMenu() {

		System.out.println("1. Register as a new User");
		System.out.println("2. Login");
		System.out.println("3. Forgot/Reset Password");

		int userOption = sc.nextInt();

		switch (userOption) {
		case 1:
			RegisterUser();
			break;

		case 2:
			loginUser();
			break;

		case 3:
			resetPassword();
			break;

		default:
			System.out.println("Please select a valid option!");
			userMenu();
		}
	}

	// register user
	public void RegisterUser() {
		User newUser = new User();
		AddUserDao userDao = new AddUserDao();
		Boolean exception = false;

		System.out.println("Enter First Name");
		String firstName = sc.next();
		newUser.setFirstName(firstName);
		System.out.println("Enter Last Name");
		newUser.setLastName(sc.next());
		System.out.println("Enter your gender");
		newUser.setGender(sc.next());

		System.out.println("Enter DOB in yyyy-mm-dd format");
		String dob = sc.next();
		SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-mm-dd");
		Date parsedDate = null;
		try {
			parsedDate = formattedDate.parse(dob);
			String userDate = formattedDate.format(parsedDate);
			newUser.setDob(userDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Sorry, Incorrect format. Please try again");
			exception = true;

		}
		if (exception) {
			RegisterUser();
		} else {
			System.out.println("Enter Email Id");
			newUser.setEmail(sc.next());
			System.out.println("Enter Password");
			newUser.setPassword(sc.next());
			newUser.setLogged_in(true);
			if (userDao.addNewUser(newUser)) {
				System.out.println("Registration Is SuccessFul !");
				TweetsService tweets = new TweetsService();
				tweets.tweetsMenu(newUser.getEmail());
			} else {
				System.out.println("An Error Has occured. Please try again!");
				RegisterUser();
			}
		}

	}

	// To login with username and password
	public void loginUser() {
		AddUserDao userDao = new AddUserDao();
		TweetsService tweets = new TweetsService();
		System.out.println("Enter your Email Id");
		String emailId = sc.next();
		System.out.println("Enter password");
		String password = sc.next();
		userDao.loginUserDao(emailId, password);

		if (userDao.loginUserDao(emailId, password)) {
			System.out.println("you have logged in successfully");
			tweets.tweetsMenu(emailId);
		} else {
			System.out.println("Your Password is incorrect.Please try again");
			loginUser();
		}
	}

	// reset and forgotpassword
	public void resetPassword() {
		AddUserDao userDao = new AddUserDao();
		TweetsService tweets = new TweetsService();
		System.out.println("Enter your Email Id");
		String emailId = sc.next();
		System.out.println("Enter your old password");
		String oldPassword = sc.next();
		System.out.println("Enter new password");
		String newPassword = sc.next();
		if (userDao.loginUserDao(emailId, oldPassword)) {
			if (userDao.resetPassword(emailId, newPassword)) {
				System.out.println("Password reset successful!\n");
				tweets.tweetsMenu(emailId);
			}
			;
		} else {
			System.out.println("Process failed. Please try again!\n");
			userMenu();
		}

	}

}
