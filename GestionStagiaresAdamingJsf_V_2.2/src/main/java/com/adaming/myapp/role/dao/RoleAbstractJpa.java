package com.adaming.myapp.role.dao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.adaming.myapp.entities.Role;
import com.adaming.myapp.entities.User;
import com.adaming.myapp.tools.LoggerConfig;

public abstract class RoleAbstractJpa {

	@PersistenceContext
	private EntityManager em;

	public Role saveRoleAbstractJpa(Role r, Long idUser) {
		User u = em.find(User.class, idUser);
		r.setUser(u);
		em.persist(r);
		LoggerConfig.logInfo("le role "+r.getRoleName()+" à bien été attribué à l'utilisateur "+u.getName());
		return r;
	}
}
