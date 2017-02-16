package com.adaming.myapp.user.service;

import java.util.List;

import com.adaming.myapp.entities.User;
import com.adaming.myapp.exception.GetUserException;
import com.adaming.myapp.exception.VerificationInDataBaseException;

public interface IUserService {

	 List<User> getUsersByMail(final String mail);

	 User saveUser(final User u);

	 User getUser(final String mail) throws GetUserException;
	 
	 User getUserByMail(final String mail) throws VerificationInDataBaseException;
	 
	 User customPassword(final User u);
}
