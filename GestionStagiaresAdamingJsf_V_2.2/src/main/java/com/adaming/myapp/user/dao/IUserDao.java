package com.adaming.myapp.user.dao;

import java.util.List;

import com.adaming.myapp.entities.User;
import com.adaming.myapp.exception.GetUserException;
/**
 * 
 * @author adel
 * @date 10/10/2016
 * @version 1.0.0
 * */
public interface IUserDao {
	
	
    
	 List<User> getUsersByMail(final String mail);
	
	 User saveUser(final User u);

	 User getUser(final String mail) throws GetUserException;
	 
	 User getUserByMail(final String mail);
	 
	 User customPassword(final User u);

}