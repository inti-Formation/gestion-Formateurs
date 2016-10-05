package com.adaming.myapp.evenement.service;

import java.util.logging.Logger;

import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.entities.Absence;
import com.adaming.myapp.entities.Entretien;
import com.adaming.myapp.entities.Evenement;
import com.adaming.myapp.entities.Retard;
import com.adaming.myapp.evenement.dao.IEvenementDao;
@Transactional
public class EvenementServiceImpl implements IEvenementService {
    
	Logger logger = Logger.getLogger("EvenementServiceImpl");
	
	private IEvenementDao dao;


	public void setDao(IEvenementDao dao) {
		logger.info("<---------dao Evenement injected------->");
		this.dao = dao;
	}


	@Override
	public Retard addRetard(Retard r, Long idSession, Long idEtudiant) {
		// TODO Auto-generated method stub
		return (Retard) dao.addEvenement(r, idSession, idEtudiant);
	}


	@Override
	public Absence addAbsence(Absence a, Long idSession, Long idEtudiant) {
		// TODO Auto-generated method stub
		return (Absence) dao.addEvenement(a, idSession, idEtudiant);
	}


	@Override
	public Entretien addEntretien(Entretien e, Long idSession, Long idEtudiant) {
		// TODO Auto-generated method stub
		return (Entretien) dao.addEvenement(e, idSession, idEtudiant);
	}


	

}
