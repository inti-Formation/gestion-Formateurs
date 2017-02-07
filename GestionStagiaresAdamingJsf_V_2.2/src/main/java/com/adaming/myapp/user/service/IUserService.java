package com.adaming.myapp.user.service;

import java.util.List;

import com.adaming.myapp.entities.User;
import com.adaming.myapp.exception.GetUserException;
import com.adaming.myapp.exception.VerificationInDataBaseException;

public interface IUserService {

	 List<User> getUsersByMail(String mail);

	 User saveUser(User u);

	 User getUser(String mail) throws GetUserException;
	 
	 User getUserByMail(String mail) throws VerificationInDataBaseException;
	 
	 User customPassword(User u);
}
