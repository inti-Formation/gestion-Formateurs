package com.adaming.myapp.user.service;

import java.util.List;

import com.adaming.myapp.entities.User;
import com.adaming.myapp.exception.GetUserException;
import com.adaming.myapp.exception.VerificationInDataBaseException;
/**
 * 
 * @author adel
 * @date 10/10/2016
 * @version 1.0.0
 * */
public interface IUserService {

	 List<User> getUsersByMail(final String mail);

	 User saveUser(final User u);

	 User getUser(final String mail) throws GetUserException;
	 
	 User getUserByMail(final String mail) throws VerificationInDataBaseException;
	 
	 User customPassword(final User u);
}
