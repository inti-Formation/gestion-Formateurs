package com.adaming.myapp.user.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.adaming.myapp.entities.User;
import com.adaming.myapp.exception.GetUserException;

public abstract class UserAbstractJpa {

	@PersistenceContext
	private EntityManager em;
	
	private Logger logger = Logger.getLogger("UserAbstractJpa");
	
	
	public User saveUserAbstractJpa(User u) {
		em.persist(u);
		logger.info("l'identifiant et le mot de passe ont bien Générées"+"USER N° : "+"psseudo Name : "+u.getName()+" Password: "+u.getPassword());
		return u;
	}
	@SuppressWarnings("unchecked")
	public List<User> getUsersByMailAbstractJpa(String mail){
		List<User> u = null;
		Query query = em.createQuery("From User u where u.name=:x");
		query.setParameter("x", mail);
		u = query.getResultList();
		return u;
	}
	@SuppressWarnings("unchecked")
	public List<User> getUserByPasswordAndMailAbstractJpa(String mail, String password) {
		Query query = em.createQuery("from User u where u.name=:x and u.password =:y");
		query.setParameter("x",mail);
		query.setParameter("y",password);
		List<User> u = query.getResultList();
		return u;
	}
	
	public User getUserAbstractJpa(String mail) throws GetUserException {
		
		List<User> u = getUsersByMailAbstractJpa(mail);

		if(u.size()==0)
			throw new GetUserException("Ce mail ne correspond a aucun utilisateur !");
		
		if(u.get(0).isActived()== false)
			throw new GetUserException("Cet utilisateur n'est pas activé !");

		return u.get(0);
	}
	public User updatePasswordAbstractJpa(String mail, String password, String newPassword) throws GetUserException {
    	List<User> u = null;
		u = getUserByPasswordAndMailAbstractJpa(mail,password);

		if(u.size()==0)
			throw new GetUserException("Ce mail ne correspond a aucun utilisateur !");
		if(u.get(0).getPassword().equals(newPassword))
			throw new GetUserException("Veuillez utiliser un nouveau mot de passe !");
		User user = u.get(0);
		user.setPassword(newPassword);
		em.merge(user);
		logger.info("le passwode de "+mail+" à bien été modifié");
		return user;
	}

}
