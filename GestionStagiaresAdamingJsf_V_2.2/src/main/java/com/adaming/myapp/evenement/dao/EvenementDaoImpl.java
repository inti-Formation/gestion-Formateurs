package com.adaming.myapp.evenement.dao;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.time.DateUtils;

import com.adaming.myapp.entities.Etudiant;
import com.adaming.myapp.entities.Evenement;
import com.adaming.myapp.entities.SessionEtudiant;

public class EvenementDaoImpl implements IEvenementDao{
   
	@PersistenceContext
	private EntityManager em;
	
	Logger logger = Logger.getLogger("EvenementDaoImpl");

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

	@Override
	@SuppressWarnings("unchecked")
	public List<Evenement> getEvenementsRetards() {
		Date weeckAgo = DateUtils.addDays(new Date(), -6);
		Query query = em.createQuery("from Evenement e where TYPE_EVENEMENT =:x and e.curentDate >= :y");
		query.setParameter("y",weeckAgo);
		logger.info("weeck"+weeckAgo);
		query.setParameter("x","RETARD");
		logger.info("le size de retarad est"+query.getResultList().size());
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Evenement> getEvenementsAbsences() {
		Date weeckAgo = DateUtils.addDays(new Date(), -6);
		Query query = em.createQuery("from Evenement e where TYPE_EVENEMENT =:x and e.curentDate >= :y");
		query.setParameter("y",weeckAgo);
		logger.info("weeck"+weeckAgo);
		query.setParameter("x","ABSENCE");
		logger.info("le size de Absences est"+query.getResultList().size());
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Evenement> getEvenementsEntretien() {
		Date weeckAgo = DateUtils.addDays(new Date(), -6);
		Query query = em.createQuery("from Evenement e where TYPE_EVENEMENT=:x and e.curentDate >= :y");
		query.setParameter("y",weeckAgo);
		logger.info("weeck"+weeckAgo);
		query.setParameter("x","ENTRETIENT");
		logger.info("le size de ENTRETIENT est"+query.getResultList().size());
		return query.getResultList();
	}

	
	
}
