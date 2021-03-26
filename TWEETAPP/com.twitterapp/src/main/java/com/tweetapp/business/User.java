package com.tweetapp.business;

public class User {

	public String first_name;
	public String last_name;
	public String gender;
	public String dob;
	public String email;
	public String password;
	public boolean logged_in;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return first_name;
	}

	public void setFirstName(String firstName) {
		this.first_name = firstName;
	}

	public String getLastName() {
		return last_name;
	}

	public void setLastName(String lastName) {
		this.last_name = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isLogged_in() {
		return logged_in;
	}

	public void setLogged_in(boolean logged_in) {
		this.logged_in = logged_in;
	}

	@Override
	public String toString() {
		return "User [first_name=" + first_name + ", last_name=" + last_name + ", gender=" + gender + ", dob=" + dob
				+ ", email=" + email + ", password=" + password + ", logged_in=" + logged_in + "]";
	}

}
