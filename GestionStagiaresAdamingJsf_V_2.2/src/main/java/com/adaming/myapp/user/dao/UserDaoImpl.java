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
	public List<User> getUsersByMail(String mail) {
		return getUsersByMailAbstractJpa(mail);
	}

	

	@Override
	public User getUserByMail(String mail) {
		// TODO Auto-generated method stub
		return getUserByMailAbstractJpa(mail);
	}

	@Override
	public User customPassword(User u) {
		// TODO Auto-generated method stub
		return customPasswordAbstractJpa(u);
	}

	

}
