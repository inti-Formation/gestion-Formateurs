package com.adaming.myapp.evenement.dao;

import com.adaming.myapp.entities.Absence;
import com.adaming.myapp.entities.Entretien;
import com.adaming.myapp.entities.Evenement;
import com.adaming.myapp.entities.Retard;

public interface IEvenementDao {

	/*public Retard addRetard(Retard r,Long idSession,Long idEtudiant);
	
	public Absence addAbsence(Absence a,Long idSession,Long idEtudiant);
	
	public Entretien addEntretien(Entretien e,Long idSession,Long idEtudiant);*/
	
	public Evenement addEvenement(Evenement e,Long idSession,Long idEtudiant);
	
}
