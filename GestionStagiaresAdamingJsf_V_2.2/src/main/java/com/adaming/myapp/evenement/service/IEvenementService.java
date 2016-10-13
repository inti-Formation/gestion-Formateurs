package com.adaming.myapp.evenement.service;

import java.util.List;

import com.adaming.myapp.entities.Absence;
import com.adaming.myapp.entities.Entretien;
import com.adaming.myapp.entities.Evenement;
import com.adaming.myapp.entities.Retard;
import com.adaming.myapp.entities.TopEtudiant;
import com.adaming.myapp.entities.WarningEtudiant;
import com.adaming.myapp.exception.EvenementNotFoundException;
import com.adaming.myapp.exception.VerificationInDataBaseException;

public interface IEvenementService {

	public Retard addRetard(Retard r, Long idSession, Long idEtudiant)
			throws VerificationInDataBaseException;

	public Absence addAbsence(Absence a, Long idSession, Long idEtudiant)
			throws VerificationInDataBaseException;

	public Entretien addEntretien(Entretien e, Long idSession, Long idEtudiant)
			throws VerificationInDataBaseException;

	public TopEtudiant addTop(TopEtudiant t, Long idSession, Long idEtudiant)
			throws VerificationInDataBaseException;

	public WarningEtudiant addWarning(WarningEtudiant w, Long idSession,
			Long idEtudiant) throws VerificationInDataBaseException;

	/* toutes les evenements par semaine */

	public List<Evenement> getEvenementsRetards()
			throws EvenementNotFoundException;

	public List<Evenement> getEvenementsAbsences()
			throws EvenementNotFoundException;

	public List<Evenement> getEvenementsEntretien()
			throws EvenementNotFoundException;

	/* evenement du jour */

	public List<Evenement> getNumberOfCurrentsRetards();

	public List<Evenement> getNumberOfCurrentsAbsence();

	public List<Evenement> getNumberOfCurrentsWarning();

	public List<Evenement> getNumberOfCurrentsTop();

	/* toutes les evenements */

	public List<Evenement> getAllEvenementsRetards();

	public List<Evenement> getAllEvenementsEntretient();

	public List<Evenement> getAllEvenementsAbsences();

	public List<Evenement> getAllEvenements();

	public List<Evenement> getAllEvenementsBySession(Long idSession);

}
