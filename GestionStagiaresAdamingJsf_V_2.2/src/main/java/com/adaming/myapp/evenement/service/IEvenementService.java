package com.adaming.myapp.evenement.service;

import java.util.List;

import com.adaming.myapp.entities.Absence;
import com.adaming.myapp.entities.Entretien;
import com.adaming.myapp.entities.Evenement;
import com.adaming.myapp.entities.Retard;

public interface IEvenementService {
	
	public Retard addRetard(Retard r,Long idSession,Long idEtudiant);
	
	public Absence addAbsence(Absence a,Long idSession,Long idEtudiant);
	
	public Entretien addEntretien(Entretien e,Long idSession,Long idEtudiant);
	
	/*get all*/
	
	public List<Evenement> getEvenementsRetards();
	
	public List<Evenement> getEvenementsAbsences();
	
	public List<Evenement> getEvenementsEntretien();
	
	
}
