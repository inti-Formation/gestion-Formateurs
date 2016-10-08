package com.adaming.myapp.evenement.dao;

import java.util.List;

import com.adaming.myapp.entities.Absence;
import com.adaming.myapp.entities.Entretien;
import com.adaming.myapp.entities.Evenement;
import com.adaming.myapp.entities.Retard;
import com.adaming.myapp.exception.EvenementNotFoundException;

public interface IEvenementDao {

	public Evenement addEvenement(Evenement e,Long idSession,Long idEtudiant);
	
	
	public List<Evenement> getEvenementsRetards() throws EvenementNotFoundException;
	
	public List<Evenement> getEvenementsAbsences() throws EvenementNotFoundException;
	
	public List<Evenement> getEvenementsEntretien() throws EvenementNotFoundException;

	public List<Evenement> getNumberOfCurrentsRetards();
	
	public List<Evenement> getNumberOfCurrentsAbsence();
}
