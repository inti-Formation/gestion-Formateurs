package com.adaming.myapp.factory.impl;

import com.adaming.myapp.entities.Formateur;
import com.adaming.myapp.factory.manager.IFactory;

public final class FormateurFactoryImpl implements IFactory<Formateur> {

	@Override
	public final Formateur create(final String object) {
		if("Formateur".equalsIgnoreCase(object)){
			return new Formateur();
		}
		return null;
	}

}
