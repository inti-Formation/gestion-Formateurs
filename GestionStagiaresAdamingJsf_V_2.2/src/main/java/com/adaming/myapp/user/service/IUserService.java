package com.adaming.myapp.user.service;

import com.adaming.myapp.entities.User;

public interface IUserService {

	public User saveUser(User u);
	
	public String generateSessionKey(int length);
}
