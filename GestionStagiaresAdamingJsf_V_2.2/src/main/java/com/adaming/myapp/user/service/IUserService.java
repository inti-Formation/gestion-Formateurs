package com.adaming.myapp.user.service;

import java.util.List;

import com.adaming.myapp.entities.User;
import com.adaming.myapp.exception.GetUserException;

public interface IUserService {

	 List<User> getUserByPasswordAndMail(String mail, String password);

	 List<User> getUsersByMail(String mail);

	 User saveUser(User u);

	 String generateSessionKey(int length);

	 User getUser(String mail) throws GetUserException;

	 User updatePassword(String mail, String password, String newPassword)
			throws GetUserException;
}
