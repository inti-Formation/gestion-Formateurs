package com.adaming.myapp.role.dao;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.adaming.myapp.entities.Role;
import com.adaming.myapp.entities.User;

public class RoleDaoImpl implements IRoleDao {
   
	@PersistenceContext
	private EntityManager em;
	
	Logger logger = Logger.getLogger("RoleDaoImpl");
    
	@Override
	public Role saveRole(Role r, Long idUser) {
		User u = em.find(User.class, idUser);
		r.setUser(u);
		em.persist(r);
		logger.info("le role "+r.getRoleName()+" à bien été attribué à l'utilisateur "+u.getName());
		return r;
	}

}
