package com.adaming.myapp.evenement.service;

import java.util.List;
import java.util.logging.Logger;

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
	public Retard addRetard(Retard r, Long idSession, Long idEtudiant) throws VerificationInDataBaseException {
		// TODO Auto-generated method stub
		return (Retard) dao.addEvenement(r, idSession, idEtudiant);
	}


	@Override
	public Absence addAbsence(Absence a, Long idSession, Long idEtudiant) throws VerificationInDataBaseException {
		// TODO Auto-generated method stub
		return (Absence) dao.addEvenement(a, idSession, idEtudiant);
	}


	@Override
	public Entretien addEntretien(Entretien e, Long idSession, Long idEtudiant) throws VerificationInDataBaseException {
		// TODO Auto-generated method stub
		return (Entretien) dao.addEvenement(e, idSession, idEtudiant);
	}


	@Override
	public List<Evenement> getEvenementsRetards() throws EvenementNotFoundException {
		// TODO Auto-generated method stub
		return dao.getEvenementsRetards();
	}


	@Override
	public List<Evenement> getEvenementsAbsences() throws EvenementNotFoundException {
		// TODO Auto-generated method stub
		return dao.getEvenementsAbsences();
	}


	@Override
	public List<Evenement> getEvenementsEntretien() throws EvenementNotFoundException {
		// TODO Auto-generated method stub
		return dao.getEvenementsEntretien();
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
	public TopEtudiant addTop(TopEtudiant t, Long idSession, Long idEtudiant)
			throws VerificationInDataBaseException {
		// TODO Auto-generated method stub
		return (TopEtudiant)dao.AddWarningAndTop(t, idSession, idEtudiant);
	}


	@Override
	public WarningEtudiant addWarning(WarningEtudiant w, Long idSession,
			Long idEtudiant) throws VerificationInDataBaseException {
		// TODO Auto-generated method stub
		return (WarningEtudiant)dao.AddWarningAndTop(w, idSession, idEtudiant);
	}


	


	



}
