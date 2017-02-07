package com.adaming.myapp.factory.producer;
import com.adaming.myapp.entities.User;
import com.adaming.myapp.factory.impl.UserFactoryImpl;
import com.adaming.myapp.factory.manager.IFactory;

public final class UserFactoryProducer {

	public final static IFactory<User> getFactoryImpl(String typeImpl){
		if("UserFactoryImpl".equalsIgnoreCase(typeImpl)){
			return new UserFactoryImpl();
		}
		return null;
	}
}
