package com.adaming.myapp.evenement.dao;

import java.util.List;

import com.adaming.myapp.entities.Absence;
import com.adaming.myapp.entities.Entretien;
import com.adaming.myapp.entities.Evenement;
import com.adaming.myapp.entities.Retard;

public interface IEvenementDao {

	public Evenement addEvenement(Evenement e,Long idSession,Long idEtudiant);
	
	public List<Evenement> getEvenementsRetards();
	
	public List<Evenement> getEvenementsAbsences();
	
	public List<Evenement> getEvenementsEntretien();
}
