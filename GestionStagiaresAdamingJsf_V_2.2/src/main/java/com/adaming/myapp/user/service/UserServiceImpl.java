package com.adaming.myapp.user.service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.entities.User;
import com.adaming.myapp.exception.GetUserException;
import com.adaming.myapp.exception.VerificationInDataBaseException;
import com.adaming.myapp.tools.LoggerConfig;
import com.adaming.myapp.user.dao.IUserDao;

@Transactional(readOnly=true)
public class UserServiceImpl implements IUserService{

	
    
	private IUserDao dao;
	
	public void setDao(IUserDao dao) {
		this.dao = dao;
		LoggerConfig.logInfo("<------User Dao Injected------->");
	}
	

	@Override
	@Transactional(readOnly=false)
	public User saveUser(User u) {
		return dao.saveUser(u);
	}

	
	@Override
	public User getUser(String mail) throws GetUserException {
		
		return dao.getUser(mail);
	}

	

	@Override
	public List<User> getUsersByMail(String mail) {
		// TODO Auto-generated method stub
		return dao.getUsersByMail(mail);
	}

	


	@Override
	public User getUserByMail(String mail) throws VerificationInDataBaseException {
		User user = dao.getUserByMail(mail);
		if(user == null){
				throw new VerificationInDataBaseException("Votre mail n'existe pas dans notre base de donnée");
		}
		return user;
	}


	@Override
	@Transactional(readOnly=false)
	public User customPassword(User u) {
		// TODO Auto-generated method stub
		return dao.customPassword(u);
	}

	
}
