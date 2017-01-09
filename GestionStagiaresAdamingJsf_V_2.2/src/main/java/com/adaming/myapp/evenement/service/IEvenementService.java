package com.adaming.myapp.evenement.service;

import java.util.Date;
import java.util.List;

import com.adaming.myapp.entities.Evenement;
import com.adaming.myapp.exception.EvenementNotFoundException;
import com.adaming.myapp.exception.VerificationInDataBaseException;

public interface IEvenementService {

	/* Add warning and Top */
	Evenement AddWarningAndTop(Evenement e, Long idSession, Long idEtudiant)
			throws VerificationInDataBaseException;

	/* add evenement */
	Evenement addEvenement(Evenement e, Long idSession, Long idEtudiant)
			throws VerificationInDataBaseException;

	List<Object[]> getEventsExiste(final Long idEtudiant);



	/* toutes les evenements par semaine */
	List<Evenement> getEvenementsRetards() throws EvenementNotFoundException;

	List<Evenement> getEvenementsAbsences() throws EvenementNotFoundException;

	List<Evenement> getEvenementsEntretien() throws EvenementNotFoundException;

	/* evenement du jour */

	List<Evenement> getNumberOfCurrentsRetards();

	List<Evenement> getNumberOfCurrentsAbsence();

	List<Evenement> getNumberOfCurrentsWarning();

	List<Evenement> getNumberOfCurrentsTop();

	/* toutes les evenements */

	List<Evenement> getAllEvenementsRetards();

	List<Evenement> getAllEvenementsEntretient();

	List<Evenement> getAllEvenementsAbsences();

	List<Evenement> getAllEvenements();

	List<Evenement> getAllEvenementsBySession(Long idSession);

	List<Evenement> getAllEvenementsBetweenTwoDate(Long idSession, Date date)
			throws EvenementNotFoundException;
}
