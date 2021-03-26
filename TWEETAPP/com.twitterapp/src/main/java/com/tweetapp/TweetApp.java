package com.tweetapp;

import com.tweetapp.service.UserService;

public class TweetApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		UserService home =  new UserService();
		System.out.println("Welcome to Tweet Application");
		System.out.println("If you are a new user,please register and continue!!");
		System.out.println("please select an option to continue");
		home.userMenu();
			  

	}
}


