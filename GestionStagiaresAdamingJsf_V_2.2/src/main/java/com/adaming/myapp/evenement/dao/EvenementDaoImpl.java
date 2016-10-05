package com.adaming.myapp.evenement.dao;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;












import com.adaming.myapp.entities.Absence;
import com.adaming.myapp.entities.Entretien;
import com.adaming.myapp.entities.Etudiant;
import com.adaming.myapp.entities.Evenement;
import com.adaming.myapp.entities.Retard;
import com.adaming.myapp.entities.SessionEtudiant;

public class EvenementDaoImpl implements IEvenementDao{
   
	@PersistenceContext
	private EntityManager em;
	
	Logger logger = Logger.getLogger("EvenementDaoImpl");

	/*@Override
	public Retard addRetard(Retard r, Long idSession, Long idEtudiant) {
		SessionEtudiant se = em.find(SessionEtudiant.class,idSession);
		Etudiant e = em.find(Etudiant.class,idEtudiant);
	    r.setEtudiant(e);
	    r.setSessionEtudiant(se);
		em.persist(r);
		logger.info("le retard a bien été enregistrer "+"Session id : "+idSession+"etudiant :"+idEtudiant);
		return r;
	}

	@Override
	public Absence addAbsence(Absence a, Long idSession, Long idEtudiant) {
		SessionEtudiant se = em.find(SessionEtudiant.class,idSession);
		Etudiant e = em.find(Etudiant.class,idEtudiant);
	    a.setEtudiant(e);
	    a.setSessionEtudiant(se);
		em.persist(a);
		logger.info("l'absence a bien été enregistrer "+"Session id : "+idSession+"etudiant :"+idEtudiant);
		return a;
	}

	@Override
	public Entretien addEntretien(Entretien e, Long idSession, Long idEtudiant) {
		SessionEtudiant se = em.find(SessionEtudiant.class,idSession);
		Etudiant e1 = em.find(Etudiant.class,idEtudiant);
	    e.setEtudiant(e1);
	    e.setSessionEtudiant(se);
		em.persist(e);
		logger.info("l'entretien a bien été enregistrer "+"Session id : "+idSession+"etudiant :"+idEtudiant);
		return e;
	}
*/
	@Override
	public Evenement addEvenement(Evenement e, Long idSession, Long idEtudiant) {
		SessionEtudiant se = em.find(SessionEtudiant.class,idSession);
		Etudiant e1 = em.find(Etudiant.class,idEtudiant);
	    e.setEtudiant(e1);
	    e.setSessionEtudiant(se);
		em.persist(e);
		logger.info("l'entretien a bien été enregistrer "+"Session id : "+idSession+"etudiant :"+idEtudiant);
		return e;
	}

	
	
}
