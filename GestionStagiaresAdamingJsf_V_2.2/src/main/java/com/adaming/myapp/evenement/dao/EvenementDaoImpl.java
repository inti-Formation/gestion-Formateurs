package com.adaming.myapp.evenement.dao;

import java.util.Date;
import java.util.List;

import com.adaming.myapp.entities.Evenement;
import com.adaming.myapp.exception.EvenementNotFoundException;
import com.adaming.myapp.exception.VerificationInDataBaseException;

public class EvenementDaoImpl extends EvenementAbstractJpa implements
		IEvenementDao {

	@Override
	public Evenement addEvenement(Evenement e, Long idSession, Long idEtudiant)
			throws VerificationInDataBaseException {

		return addEvenementAbstractJpa(e, idSession, idEtudiant);
	}

	@Override
	public Evenement AddWarningAndTop(Evenement e, Long idSession,
			Long idEtudiant) throws VerificationInDataBaseException {

		return addWarningAndTopAbstractJpa(e, idSession, idEtudiant);
	}

	@Override
	public List<Evenement> getEvenementsRetards()
			throws EvenementNotFoundException {

		return getEvenementsRetardsAbstractJpa();
	}

	@Override
	public List<Evenement> getEvenementsAbsences()
			throws EvenementNotFoundException {

		return getEvenementsAbsencesAbstractJpa();
	}

	@Override
	public List<Evenement> getEvenementsEntretien()
			throws EvenementNotFoundException {

		return getEvenementsEntretienAbstractJpa();
	}

	@Override
	public List<Evenement> getNumberOfCurrentsRetards() {

		return getNumberOfCurrentsRetardsAbstractJpa();
	}

	@Override
	public List<Evenement> getNumberOfCurrentsAbsence() {

		return getNumberOfCurrentsAbsenceAbstractJpa();
	}

	@Override
	public List<Evenement> getNumberOfCurrentsWarning() {

		return getNumberOfCurrentsWarningAbstractJpa();
	}

	@Override
	public List<Evenement> getNumberOfCurrentsTop() {

		return getNumberOfCurrentsTopAbstractJpa();
	}

	@Override
	public List<Evenement> getAllEvenementsRetards() {

		return getAllEvenementsRetardsAbstractJpa();
	}

	@Override
	public List<Evenement> getAllEvenementsEntretient() {

		return getAllEvenementsEntretientAbstractJpa();
	}

	@Override
	public List<Evenement> getAllEvenementsAbsences() {

		return getAllEvenementsAbsencesAbstractJpa();
	}

	@Override
	public List<Evenement> getAllEvenements() {

		return getAllEvenementsAbstractJpa();
	}

	@Override
	public List<Evenement> getAllEvenementsBySession(Long idSession) {

		return getAllEvenementsBySessionAbstractJpa(idSession);
	}

	

}
