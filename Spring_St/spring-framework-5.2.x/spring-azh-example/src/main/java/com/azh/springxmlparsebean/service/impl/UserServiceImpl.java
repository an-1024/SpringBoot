package com.azh.springxmlparsebean.service.impl;

import com.azh.springxmlparsebean.service.UserService;

public class UserServiceImpl implements UserService {
	@Override
	public String getName(String firstName, String lastName) {
		return String.format("I am %s %s", firstName, lastName);
	}
}
