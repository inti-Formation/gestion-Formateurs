package com.adaming.myapp.factory.impl;

import com.adaming.myapp.entities.SessionEtudiant;
import com.adaming.myapp.factory.manager.IFactory;

public final class SessionFactoryImpl implements IFactory<SessionEtudiant> {

	@Override
	public final SessionEtudiant create(final String object) {
		if("SessionEtudiant".equalsIgnoreCase(object)){
			return new SessionEtudiant();
		}
		return null;
	}

}
