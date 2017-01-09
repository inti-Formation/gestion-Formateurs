package com.adaming.myapp.evenement.dao;

import java.util.Date;
import java.util.List;

import com.adaming.myapp.entities.Evenement;
import com.adaming.myapp.exception.EvenementNotFoundException;
import com.adaming.myapp.exception.VerificationInDataBaseException;

public interface IEvenementDao {

	/* add evenement */

	 Evenement addEvenement(Evenement e, Long idSession, Long idEtudiant);

	 Evenement AddWarningAndTop(Evenement e, Long idSession,
			Long idEtudiant);
	 
	 List<Object[]> getEventsExiste(final Long idEtudiant);
	 
	 //List<Object[]> getEventsTopExiste(final Long idEtudiant);

	/* toutes les evenements par semaine */

	 List<Evenement> getEvenementsRetards();
			

	 List<Evenement> getEvenementsAbsences();

	 List<Evenement> getEvenementsEntretien();

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
	 
	

}
