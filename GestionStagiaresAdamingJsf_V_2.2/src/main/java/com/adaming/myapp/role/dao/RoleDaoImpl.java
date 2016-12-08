package com.adaming.myapp.role.dao;


import com.adaming.myapp.entities.Role;


public class RoleDaoImpl extends RoleAbstractJpa implements IRoleDao {
   
	
	@Override
	public Role saveRole(Role r, Long idUser) {
		return saveRoleAbstractJpa(r, idUser); 
	}

}
