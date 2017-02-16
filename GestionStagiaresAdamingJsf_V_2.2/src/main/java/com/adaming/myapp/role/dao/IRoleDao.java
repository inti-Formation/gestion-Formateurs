package com.adaming.myapp.role.dao;

import com.adaming.myapp.entities.Role;
/**
 * 
 * @author adel
 * @date 10/10/2016
 * @version 1.0.0
 * */
public interface IRoleDao {

	 Role saveRole(final Role r,final Long idUser);
	
}
