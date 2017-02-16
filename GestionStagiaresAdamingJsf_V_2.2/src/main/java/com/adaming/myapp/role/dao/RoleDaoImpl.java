package com.adaming.myapp.role.dao;


import com.adaming.myapp.entities.Role;


public class RoleDaoImpl extends RoleAbstractJpa implements IRoleDao {
   
	
	@Override
	public Role saveRole(final Role r, final Long idUser) {
		return saveRoleAbstractJpa(r, idUser); 
	}

}
