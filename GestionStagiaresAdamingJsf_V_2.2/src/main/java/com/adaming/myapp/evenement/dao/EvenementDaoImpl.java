package com.adaming.myapp.evenement.dao;


import java.util.List;

import com.adaming.myapp.entities.Evenement;

public class EvenementDaoImpl extends EvenementAbstractJpa implements
		IEvenementDao {

	@Override
	public Evenement addEvenement(Evenement e, Long idSession, Long idEtudiant) {

		return addEvenementAbstractJpa(e, idSession, idEtudiant);
	}

	@Override
	public Evenement AddWarningAndTop(Evenement e, Long idSession,
			Long idEtudiant) {

		return addWarningAndTopAbstractJpa(e, idSession, idEtudiant);
	}

	@Override
	public List<Evenement> getEvenementsRetards() {
		return getEvenementsRetardsAbstractJpa();
	}

	@Override
	public List<Evenement> getEvenementsAbsences() {
		return getEvenementsAbsencesAbstractJpa();
	}

	@Override
	public List<Evenement> getEvenementsEntretien() {
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

	@Override
	public List<Object[]> getEventsExiste(Long idEtudiant) {

		return getEventsExisteAbstractJpa(idEtudiant);
	}

/*	@Override
	public List<Object[]> getEventsTopExiste(Long idEtudiant) {
		// TODO Auto-generated method stub
		return getEventsTopExiste(idEtudiant);
	}*/

}
