package com.adaming.myapp.user.dao;

import java.util.List;

import com.adaming.myapp.entities.User;
import com.adaming.myapp.exception.GetUserException;

public class UserDaoImpl extends UserAbstractJpa implements IUserDao{
    
	
	@Override
	public User saveUser(User u) {
		return saveUserAbstractJpa(u);
	}

	@Override
	public User getUser(String mail) throws GetUserException {
		return getUserAbstractJpa(mail);
	}

	@Override
	public User updatePassword(String mail, String password, String newPassword) throws GetUserException {
		return updatePasswordAbstractJpa(mail, password, newPassword);
	}

	@Override
	public List<User> getUsersByMail(String mail) {
		return getUsersByMailAbstractJpa(mail);
	}

	@Override
	public List<User> getUserByPasswordAndMail(String mail, String password) {
		// TODO Auto-generated method stub
		return getUserByPasswordAndMailAbstractJpa(mail, password);
	}

	

}
