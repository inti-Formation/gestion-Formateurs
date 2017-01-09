package com.adaming.myapp.factory.impl;

import com.adaming.myapp.entities.Salle;
import com.adaming.myapp.factory.manager.IFactory;

public class SalleFactoryImpl implements IFactory<Salle> {

	@Override
	public Salle create(String object) {
		if("Salle".equalsIgnoreCase(object)){
			return new Salle();
		}
		return null;
	}

}
