package com.adaming.myapp.user.dao;

import java.util.List;

import com.adaming.myapp.entities.User;
import com.adaming.myapp.exception.GetUserException;

public interface IUserDao {
	
	 List<User> getUserByPasswordAndMail(String mail, String password);
    
	 List<User> getUsersByMail(String mail);
	
	 User saveUser(User u);

	 User getUser(String mail) throws GetUserException;

	 User updatePassword(String mail, String password, String newPassword) throws GetUserException;

}