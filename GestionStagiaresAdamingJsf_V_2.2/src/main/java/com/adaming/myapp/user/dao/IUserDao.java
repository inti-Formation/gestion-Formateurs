package com.adaming.myapp.user.dao;

import java.util.List;

import com.adaming.myapp.entities.User;
import com.adaming.myapp.exception.GetUserException;

public interface IUserDao {
	
	
    
	 List<User> getUsersByMail(String mail);
	
	 User saveUser(User u);

	 User getUser(String mail) throws GetUserException;
	 
	 User getUserByMail(String mail);
	 
	 User customPassword(User u);

}