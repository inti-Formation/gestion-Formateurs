package com.adaming.myapp.user.service;

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
	
	@Transactional
	@Override
	public User saveUser(User u) {
		return dao.saveUser(u);
	}

	@Override
	public String generateSessionKey(int length) {
		
		String alphabet = 
		        new String("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"); //9
		int n = alphabet.length(); //10

		String result = new String(); 
		Random r = new Random(); //11

		for (int i=0; i<length; i++) //12
		    result = result + alphabet.charAt(r.nextInt(n)); //13

		return result;
	}

	@Override
	public User getUser(String mail) throws GetUserException {
		return dao.getUser(mail);
	}
    @Transactional
	@Override
	public User updatePassword(String mail, String password, String newPassword)
			throws GetUserException {
		// TODO Auto-generated method stub
		return dao.updatePassword(mail, password, newPassword);
	}

	
}
