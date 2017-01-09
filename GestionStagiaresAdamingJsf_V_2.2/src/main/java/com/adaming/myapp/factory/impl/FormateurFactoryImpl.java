package com.adaming.myapp.factory.impl;

import com.adaming.myapp.entities.Formateur;
import com.adaming.myapp.factory.manager.IFactory;

public class FormateurFactoryImpl implements IFactory<Formateur> {

	@Override
	public Formateur create(String object) {
		if("Formateur".equalsIgnoreCase(object)){
			return new Formateur();
		}
		return null;
	}

}
