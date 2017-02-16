package com.adaming.myapp.role.dao;

import com.adaming.myapp.entities.Role;

public interface IRoleDao {

	 Role saveRole(final Role r,final Long idUser);
	
}
