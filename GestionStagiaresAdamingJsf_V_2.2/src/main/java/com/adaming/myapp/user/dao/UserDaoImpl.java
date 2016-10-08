package com.adaming.myapp.user.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.adaming.myapp.entities.Etudiant;
import com.adaming.myapp.entities.User;
import com.adaming.myapp.exception.GetUserException;

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

	@Override
	public User getUser(String mail) throws GetUserException {
		// TODO Auto-generated method stub
		Query query = em.createQuery("From User u where u.name=:x");
		query.setParameter("x", mail);
		List<User> u = query.getResultList();

		if(u.size()==0)
			throw new GetUserException("Ce mail ne correspond a aucun utilisateur !");
		
		if(u.get(0).isActived()== false)
			throw new GetUserException("Cet utilisateur n'est pas activé !");
		


		return u.get(0);
	}

	@Override
	public User updatePassword(String mail, String password, String newPassword) throws GetUserException {
		Query query = em.createQuery("from User u where u.name=:x and u.password =:y");
		query.setParameter("x",mail);
		query.setParameter("y",password);
		List<User> u = query.getResultList();

		if(u.size()==0)
			throw new GetUserException("Ce mail ne correspond a aucun utilisateur !");
		if(u.get(0).getPassword().equals(newPassword))
			throw new GetUserException("Vuillez utiliser un nouveau mot de passe !");
		User user = u.get(0);
		user.setPassword(newPassword);
		em.merge(user);
		logger.info("le passwode de "+mail+" à bien été modifié");
		return user;
	}

	

}
