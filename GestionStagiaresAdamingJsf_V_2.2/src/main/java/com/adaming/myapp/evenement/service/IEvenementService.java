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
	List<Object[]> getEvenementsRetards() throws EvenementNotFoundException;

	List<Object[]> getEvenementsAbsences() throws EvenementNotFoundException;

	List<Object[]> getEvenementsEntretien() throws EvenementNotFoundException;

	/* evenement du jour */

	 List<Object[]> getDailyCountOfRetards();

	 List<Object[]> getDailyCountOfAbsence();

	 List<Object[]> getDailyCountOfWarning();

	 List<Object[]> getDailyCountOfTop();
	 
	 /* evenement du jour nombres */
	 long getNumberOfRetards();

	 long getNumberOfAbsence();

	 long getNumberOfWarning();

	 long getNumberOfTop();

	/* toutes les evenements */


	List<Evenement> getAllEvenements();

	List<Evenement> getAllEvenementsBySession(Long idSession);

	List<Evenement> getAllEvenementsBetweenTwoDate(Long idSession, Date date)
			throws EvenementNotFoundException;
	
	/**@verification evenement */
	 Evenement verifyExistingEvent(Long idEtudiant);
}
