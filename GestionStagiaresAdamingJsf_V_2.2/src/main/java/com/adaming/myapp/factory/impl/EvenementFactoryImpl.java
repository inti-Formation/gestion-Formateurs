package com.adaming.myapp.factory.impl;

import com.adaming.myapp.entities.Absence;
import com.adaming.myapp.entities.Entretien;
import com.adaming.myapp.entities.Evenement;
import com.adaming.myapp.entities.Retard;
import com.adaming.myapp.entities.TopEtudiant;
import com.adaming.myapp.entities.WarningEtudiant;
import com.adaming.myapp.factory.manager.IFactory;

public final class EvenementFactoryImpl  implements IFactory<Evenement>{

	@Override
	public final Evenement create(final String object) {
		if("Retard".equalsIgnoreCase(object)){
			return new Retard();
		}
		if("Absence".equalsIgnoreCase(object)){
			return new Absence();
		}
		if("Entretien".equalsIgnoreCase(object)){
			return new Entretien();
		}
		if("TopEtudiant".equalsIgnoreCase(object)){
			return new TopEtudiant();
		}
		if("WarningEtudiant".equalsIgnoreCase(object)){
			return new WarningEtudiant();
		}
		return null;
	}

	
}
