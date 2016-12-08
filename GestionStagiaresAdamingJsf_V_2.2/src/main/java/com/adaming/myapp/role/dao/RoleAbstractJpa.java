package com.adaming.myapp.role.dao;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.adaming.myapp.entities.Role;
import com.adaming.myapp.entities.User;

public abstract class RoleAbstractJpa {

	@PersistenceContext
	private EntityManager em;
	
	Logger logger = Logger.getLogger("RoleAbstractJpa");

	public Role saveRoleAbstractJpa(Role r, Long idUser) {
		User u = em.find(User.class, idUser);
		r.setUser(u);
		em.persist(r);
		logger.info("le role "+r.getRoleName()+" à bien été attribué à l'utilisateur "+u.getName());
		return r;
	}
}
