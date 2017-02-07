package com.adaming.myapp.factory.impl;

import com.adaming.myapp.entities.Salle;
import com.adaming.myapp.factory.manager.IFactory;

public final class SalleFactoryImpl implements IFactory<Salle> {

	@Override
	public final Salle create(final String object) {
		if("Salle".equalsIgnoreCase(object)){
			return new Salle();
		}
		return null;
	}

}
