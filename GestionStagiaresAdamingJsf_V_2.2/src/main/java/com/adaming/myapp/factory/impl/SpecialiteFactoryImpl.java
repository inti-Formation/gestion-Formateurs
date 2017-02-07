package com.adaming.myapp.factory.impl;

import com.adaming.myapp.entities.Specialite;
import com.adaming.myapp.factory.manager.IFactory;

public final class SpecialiteFactoryImpl implements IFactory<Specialite> {

	@Override
	public final Specialite create(final String object) {
		if("Specialite".equalsIgnoreCase(object)){
			return new Specialite();
		}
		return null;
	}

}
