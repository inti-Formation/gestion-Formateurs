package com.adaming.myapp.evenement.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.entities.Absence;
import com.adaming.myapp.entities.Entretien;
import com.adaming.myapp.entities.Evenement;
import com.adaming.myapp.entities.Retard;
import com.adaming.myapp.entities.TopEtudiant;
import com.adaming.myapp.entities.WarningEtudiant;
import com.adaming.myapp.evenement.dao.IEvenementDao;
import com.adaming.myapp.exception.EvenementNotFoundException;
import com.adaming.myapp.exception.VerificationInDataBaseException;

@Transactional
public class EvenementServiceImpl implements IEvenementService {

	Logger logger = Logger.getLogger("EvenementServiceImpl");

	private IEvenementDao dao;

	public void setDao(IEvenementDao dao) {
		logger.info("<---------dao Evenement injected------->");
		this.dao = dao;
	}

	@Override
	public List<Evenement> getEvenementsRetards()
			throws EvenementNotFoundException {
		List<Evenement> retards = null;
		retards = dao.getEvenementsRetards();
		if (retards.size() == 0) {
			throw new EvenementNotFoundException("Aucun retard mentionné !");
		}
		return retards;
	}

	@Override
	public List<Evenement> getEvenementsAbsences()
			throws EvenementNotFoundException {
		List<Evenement> absences = null;
		absences = dao.getEvenementsAbsences();
		if (absences.size() == 0) {
			throw new EvenementNotFoundException("Aucune absence mentionnée !");
		}
		return absences;
	}

	@Override
	public List<Evenement> getEvenementsEntretien()
			throws EvenementNotFoundException {
		List<Evenement> entretien = null;
		entretien = dao.getEvenementsAbsences();
		if (entretien.size() == 0) {
			throw new EvenementNotFoundException("Aucun entretien mentionné !");
		}
		return entretien;
	}

	@Override
	public List<Evenement> getNumberOfCurrentsRetards() {
		// TODO Auto-generated method stub
		return dao.getNumberOfCurrentsRetards();
	}

	@Override
	public List<Evenement> getNumberOfCurrentsAbsence() {
		// TODO Auto-generated method stub
		return dao.getNumberOfCurrentsAbsence();
	}

	@Override
	public List<Evenement> getAllEvenementsRetards() {
		// TODO Auto-generated method stub
		return dao.getAllEvenementsRetards();
	}

	@Override
	public List<Evenement> getNumberOfCurrentsWarning() {
		// TODO Auto-generated method stub
		return dao.getNumberOfCurrentsWarning();
	}

	@Override
	public List<Evenement> getNumberOfCurrentsTop() {
		// TODO Auto-generated method stub
		return dao.getNumberOfCurrentsTop();
	}

	@Override
	public List<Evenement> getAllEvenementsEntretient() {
		// TODO Auto-generated method stub
		return dao.getAllEvenementsEntretient();
	}

	@Override
	public List<Evenement> getAllEvenementsAbsences() {
		// TODO Auto-generated method stub
		return dao.getAllEvenementsAbsences();
	}

	@Override
	public List<Evenement> getAllEvenements() {
		// TODO Auto-generated method stub
		return dao.getAllEvenements();
	}

	

	@Override
	public List<Evenement> getAllEvenementsBySession(Long idSession) {
		return dao.getAllEvenementsBySession(idSession);
	}

	@Override
	public Evenement addEvenement(Evenement e, Long idSession, Long idEtudiant) throws VerificationInDataBaseException {
		List<Evenement> evenements = null;
		evenements = getAllEvenements();
		for (Evenement evenement : evenements) {
			if (evenement != null) {
				if (evenement.getSessionEtudiant().getIdSession() == idSession
						&& evenement.getEtudiant().getIdEtudiant() == idEtudiant
						&& evenement.getStartDate().compareTo(e.getStartDate()) == 0
						&& evenement.getEndDate().compareTo(e.getEndDate()) == 0) {
					throw new VerificationInDataBaseException(
							" cette evènement est déja signalé");
				}
			}
		}
		return dao.addEvenement(e, idSession, idEtudiant);
	}

	@Override
	public Evenement AddWarningAndTop(Evenement e, Long idSession,
			Long idEtudiant) throws VerificationInDataBaseException {
		List<Evenement> evenements = null;
		evenements = getAllEvenements();
		for (Evenement evenement : evenements) {
			if (evenement != null) {
				if (evenement.getSessionEtudiant().getIdSession() == idSession
						&& evenement.getEtudiant().getIdEtudiant() == idEtudiant
						&& !evenement.getClass().getSimpleName()
								.equals("Absence")
						&& !evenement.getClass().getSimpleName()
								.equals("Entretien")
						&& !evenement.getClass().getSimpleName()
								.equals("Retard")) {
					throw new VerificationInDataBaseException("l' etudiant "
							+ evenement.getEtudiant().getNomEtudiant() + " , "
							+ evenement.getEtudiant().getPrenomEtudiant()
							+ " est déja signalé");
				}
			}
		}
		return dao.AddWarningAndTop(e, idSession, idEtudiant);
	}

	@Override
	public List<Evenement> getAllEvenementsBetweenTwoDate(Long idSession,
			Date date) throws EvenementNotFoundException {
		List<Evenement> events = getAllEvenementsBySession(idSession);
		List<Evenement> newEvents = null;
		if(events.size() >0){
			newEvents = new ArrayList<Evenement>();
			for(Evenement e:events){
				if(e.getCurentDate().after(date) || e.getCurentDate().equals(date) ){
					newEvents.add(e);
				}
			}
			if(newEvents.size() == 0){
				throw new EvenementNotFoundException("Aucun evènement trouvé à partir de cette date");
		    }
		}
		else{
			throw new EvenementNotFoundException("Aucun evènement trouvé dans la base de donnée");
		}
		
		return newEvents;
	}

}
