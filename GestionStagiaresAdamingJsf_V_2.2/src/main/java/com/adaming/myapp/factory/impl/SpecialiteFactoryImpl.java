package com.adaming.myapp.factory.impl;

import com.adaming.myapp.entities.Specialite;
import com.adaming.myapp.factory.manager.IFactory;

public class SpecialiteFactoryImpl implements IFactory<Specialite> {

	@Override
	public Specialite create(String object) {
		if("Specialite".equalsIgnoreCase(object)){
			return new Specialite();
		}
		return null;
	}

}
