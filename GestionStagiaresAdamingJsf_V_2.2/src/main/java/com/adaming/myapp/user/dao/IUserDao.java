package com.adaming.myapp.user.dao;

import com.adaming.myapp.entities.User;
import com.adaming.myapp.exception.GetUserException;

public interface IUserDao {

	public User saveUser(User u);

	public User getUser(String mail) throws GetUserException;

	public User updatePassword(String mail, String password, String newPassword) throws GetUserException;

}