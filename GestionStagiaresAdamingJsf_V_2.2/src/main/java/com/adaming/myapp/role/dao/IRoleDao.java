package com.adaming.myapp.role.dao;

import com.adaming.myapp.entities.Role;

public interface IRoleDao {

	public Role saveRole(Role r,Long idUser);
	
}
