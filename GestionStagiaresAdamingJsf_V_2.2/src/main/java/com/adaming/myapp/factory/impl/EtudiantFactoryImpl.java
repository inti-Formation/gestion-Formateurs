package com.adaming.myapp.factory.impl;
import com.adaming.myapp.entities.Etudiant;
import com.adaming.myapp.factory.manager.IFactory;

public  class EtudiantFactoryImpl implements IFactory<Etudiant> {

	@Override
	public Etudiant create(String object) {
		if("Etudiant".equalsIgnoreCase(object)){
			return new Etudiant();
		}
		return null;
	}

}
