package com.adaming.myapp.factory.impl;

import com.adaming.myapp.entities.Role;
import com.adaming.myapp.factory.manager.IFactory;

public class RoleFactoryImpl implements IFactory<Role>{

	@Override
	public Role create(String object) {
		if("Role".equalsIgnoreCase(object)){
			return new Role();
		}
		return null;
	}

}
