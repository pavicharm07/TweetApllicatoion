package com.tweetapp.business;

public class UserTweets {

	String email;
	String Tweet;

	public UserTweets() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTweet() {
		return Tweet;
	}

	public void setTweet(String tweet) {
		Tweet = tweet;
	}

	@Override
	public String toString() {
		return "UserTweets [email=" + email + ", Tweet=" + Tweet + "]";
	}

}