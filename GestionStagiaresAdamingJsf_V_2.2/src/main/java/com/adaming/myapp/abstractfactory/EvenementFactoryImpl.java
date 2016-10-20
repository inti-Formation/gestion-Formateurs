package com.adaming.myapp.abstractfactory;

import com.adaming.myapp.entities.Absence;
import com.adaming.myapp.entities.Entretien;
import com.adaming.myapp.entities.Evenement;
import com.adaming.myapp.entities.Retard;
import com.adaming.myapp.entities.TopEtudiant;
import com.adaming.myapp.entities.WarningEtudiant;

public class EvenementFactoryImpl extends Evenement implements IEvenementFactory{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Evenement createEvent(String typeEvent) {
		if("Retard".equalsIgnoreCase(typeEvent)){
			return new Retard(startDate, endDate, typeEvent, curentDate);
		}
		if("Absence".equalsIgnoreCase(typeEvent)){
			return new Absence(startDate, endDate, typeEvent, curentDate);
		}
		if("Entretien".equalsIgnoreCase(typeEvent)){
			return new Entretien(startDate, endDate, typeEvent, curentDate);
		}
		if("TopEtudiant".equalsIgnoreCase(typeEvent)){
			return new TopEtudiant(startDate, endDate, typeEvent, curentDate);
		}
		if("WarningEtudiant".equalsIgnoreCase(typeEvent)){
			return new WarningEtudiant(startDate, endDate, typeEvent, curentDate);
		}
		return null;
	}

}
