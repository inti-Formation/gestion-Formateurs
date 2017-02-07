package com.adaming.myapp.factory.impl;

import com.adaming.myapp.entities.Reponses;
import com.adaming.myapp.factory.manager.IFactory;

public final class ReponsesFactoryImpl implements IFactory<Reponses>{

	@Override
	public final Reponses create(final String object) {
		if("Reponses".equalsIgnoreCase(object))
		{
			return new Reponses();
		}
		return null;
	}

}
