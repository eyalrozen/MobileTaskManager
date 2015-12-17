package com.example.eyal.recycleview.common;

public class User {
	private String userName;
	private String password;
	private String mail;
	private String phoneNumber;

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

	/*public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
*/
	public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

	public String getPhoneNumber() {return phoneNumber;}
}