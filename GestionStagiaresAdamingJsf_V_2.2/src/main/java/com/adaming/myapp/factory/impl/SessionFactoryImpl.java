package com.adaming.myapp.factory.impl;

import com.adaming.myapp.entities.SessionEtudiant;
import com.adaming.myapp.factory.manager.IFactory;

public class SessionFactoryImpl implements IFactory<SessionEtudiant> {

	@Override
	public SessionEtudiant create(String object) {
		if("SessionEtudiant".equalsIgnoreCase(object)){
			return new SessionEtudiant();
		}
		return null;
	}

}
