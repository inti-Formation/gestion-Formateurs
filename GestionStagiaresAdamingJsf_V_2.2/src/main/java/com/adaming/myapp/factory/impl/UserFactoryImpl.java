package com.adaming.myapp.factory.impl;

import com.adaming.myapp.entities.User;
import com.adaming.myapp.factory.manager.IFactory;

public final class UserFactoryImpl implements IFactory<User> {

	@Override
	public final User create(final String object) {
		if("User".equals(object)){
			return new User();
		}
		return null;
	}

}
