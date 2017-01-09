package com.adaming.myapp.user.service;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.entities.User;
import com.adaming.myapp.exception.GetUserException;
import com.adaming.myapp.user.dao.IUserDao;


public class UserServiceImpl implements IUserService{

	private Logger logger = Logger.getLogger("UserServiceImpl");
    
	private IUserDao dao;
	
	public void setDao(IUserDao dao) {
		this.dao = dao;
		logger.info("<------User Dao Injected------->");
	}
	

	@Override
	@Transactional(readOnly=false)
	public User saveUser(User u) {
		return dao.saveUser(u);
	}

	
	@Override
	@Transactional(readOnly=true)
	public User getUser(String mail) throws GetUserException {
		
		return dao.getUser(mail);
	}

	@Override
	@Transactional(readOnly=false)
	public User updatePassword(String mail, String password, String newPassword)
			throws GetUserException {
		// TODO Auto-generated method stub
		return dao.updatePassword(mail, password, newPassword);
	}

	@Override
	@Transactional(readOnly=true)
	public List<User> getUsersByMail(String mail) {
		// TODO Auto-generated method stub
		return dao.getUsersByMail(mail);
	}

	@Override
	@Transactional(readOnly=true)
	public List<User> getUserByPasswordAndMail(String mail, String password) {
		// TODO Auto-generated method stub
		return dao.getUserByPasswordAndMail(mail, password);
	}

	
}
