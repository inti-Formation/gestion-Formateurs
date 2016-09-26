package com.adaming.myapp.user.dao;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.adaming.myapp.entities.User;

public class UserDaoImpl implements IUserDao{
    
	@PersistenceContext
	private EntityManager em;
	
	private Logger logger = Logger.getLogger("UserDaoImpl");
	
	@Override
	public User saveUser(User u) {
		em.persist(u);
		logger.info("l'identifiant et le mot de passe ont bien Générées"+"USER N° : "+"psseudo Name : "+u.getName()+" Password: "+u.getPassword());
		return u;
	}

}
