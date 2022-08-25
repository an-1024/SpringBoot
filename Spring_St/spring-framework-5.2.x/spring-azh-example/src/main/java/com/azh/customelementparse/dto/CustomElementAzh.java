package com.azh.customelementparse.dto;

public class CustomElementAzh {
	private String userName;

	private String email;

	private String userPhone;


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	@Override
	public String toString() {
		return "CustomElementAzh{" +
				"userName='" + userName + '\'' +
				", email='" + email + '\'' +
				", userPhone='" + userPhone + '\'' +
				'}';
	}
}
