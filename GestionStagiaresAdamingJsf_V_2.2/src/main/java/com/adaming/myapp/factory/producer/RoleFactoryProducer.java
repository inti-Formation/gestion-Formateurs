package com.adaming.myapp.factory.producer;


import com.adaming.myapp.entities.Role;
import com.adaming.myapp.factory.impl.RoleFactoryImpl;
import com.adaming.myapp.factory.manager.IFactory;

public class RoleFactoryProducer {

	public static IFactory<Role> getFactoryImpl(String typeImpl){
		if("RoleFactoryImpl".equalsIgnoreCase(typeImpl)){
			return new RoleFactoryImpl();
		}
		return null;
	}

}
