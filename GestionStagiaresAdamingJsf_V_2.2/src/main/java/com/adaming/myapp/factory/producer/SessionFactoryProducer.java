package com.adaming.myapp.factory.producer;

import com.adaming.myapp.entities.SessionEtudiant;
import com.adaming.myapp.factory.impl.SessionFactoryImpl;
import com.adaming.myapp.factory.manager.IFactory;

public class SessionFactoryProducer {

	public static IFactory<SessionEtudiant> getFactoryImpl(String typeImpl){
		if("SessionFactoryImpl".equalsIgnoreCase(typeImpl)){
			return new SessionFactoryImpl();
		}
		return null;
	}
}
