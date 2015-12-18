package com.example.eyal.recycleview.common;

public class User {
	private String userName;
	private String password;
	private String phoneNumber;
	private int isMailSent;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setMailSent(int isMailSent){this.isMailSent=isMailSent;}

	public int getMailSend() {return isMailSent;}

	public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

	public String getPhoneNumber() {return phoneNumber;}
}