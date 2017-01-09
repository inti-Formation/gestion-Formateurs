package com.adaming.myapp.factory.impl;

import com.adaming.myapp.entities.User;
import com.adaming.myapp.factory.manager.IFactory;

public class UserFactoryImpl implements IFactory<User> {

	@Override
	public User create(String object) {
		if("User".equals(object)){
			return new User();
		}
		return null;
	}

}
