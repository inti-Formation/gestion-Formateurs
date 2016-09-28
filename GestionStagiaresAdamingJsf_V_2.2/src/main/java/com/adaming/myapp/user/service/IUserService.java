package com.adaming.myapp.user.service;

import com.adaming.myapp.entities.User;
import com.adaming.myapp.exception.GetUserException;

public interface IUserService {

	public User saveUser(User u);
	
	public String generateSessionKey(int length);
	
	public User getUser(String mail) throws GetUserException;
}
