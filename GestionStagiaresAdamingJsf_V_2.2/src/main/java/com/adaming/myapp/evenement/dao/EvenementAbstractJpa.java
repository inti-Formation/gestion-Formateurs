package com.adaming.myapp.evenement.dao;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.time.DateUtils;

import com.adaming.myapp.entities.Etudiant;
import com.adaming.myapp.entities.Evenement;
import com.adaming.myapp.entities.SessionEtudiant;
import com.adaming.myapp.exception.EvenementNotFoundException;
import com.adaming.myapp.exception.VerificationInDataBaseException;

public abstract class EvenementAbstractJpa {
	
	@PersistenceContext
	private EntityManager em;

	Logger logger = Logger.getLogger("EvenementAbstractJpa");
	

	public Evenement addEvenementAbstractJpa(Evenement e, Long idSession, Long idEtudiant)
			throws VerificationInDataBaseException {
		SessionEtudiant se = em.find(SessionEtudiant.class, idSession);
		Etudiant e1 = em.find(Etudiant.class, idEtudiant);
		e.setEtudiant(e1);
		e.setSessionEtudiant(se);
		em.persist(e);
		logger.info("l'evenement a bien été enregistrer " + "Session id : "
				+ idSession + "etudiant :" + idEtudiant);
		return e;
	}

	
	public Evenement addWarningAndTopAbstractJpa(Evenement e, Long idSession,
			Long idEtudiant) throws VerificationInDataBaseException {
		SessionEtudiant se = em.find(SessionEtudiant.class, idSession);
		Etudiant e1 = em.find(Etudiant.class, idEtudiant);
		e.setEtudiant(e1);
		e.setSessionEtudiant(se);
		em.persist(e);
		logger.info("l'evenement a bien été enregistrer " + "Session id : "
				+ idSession + "etudiant :" + idEtudiant);
		return e;
	}

	
	@SuppressWarnings("unchecked")
	public List<Evenement> getEvenementsRetardsAbstractJpa()
			throws EvenementNotFoundException {
		Date weeckAgo = DateUtils.addDays(new Date(), -6);
		Query query = em
				.createQuery("from Evenement e where TYPE_EVENEMENT =:x and e.curentDate >= :y ORDER BY e.idEvenement DESC");
		query.setParameter("y", weeckAgo);
		logger.info("weeck" + weeckAgo);
		query.setParameter("x", "RETARD");
		logger.info("le size de retarad est" + query.getResultList().size());
		
		return query.getResultList();
	}

	
	@SuppressWarnings("unchecked")
	public List<Evenement> getEvenementsAbsencesAbstractJpa()
			throws EvenementNotFoundException {
		Date weeckAgo = DateUtils.addDays(new Date(), -6);
		Query query = em
				.createQuery("from Evenement e where TYPE_EVENEMENT =:x and e.curentDate >= :y ORDER BY e.idEvenement DESC");
		query.setParameter("y", weeckAgo);
		logger.info("weeck" + weeckAgo);
		query.setParameter("x", "ABSENCE");
		logger.info("le size de Absences est" + query.getResultList().size());
		
		return query.getResultList();
	}


	@SuppressWarnings("unchecked")
	public List<Evenement> getEvenementsEntretienAbstractJpa()
			throws EvenementNotFoundException {
		Date weeckAgo = DateUtils.addDays(new Date(), -6);
		Query query = em
				.createQuery("from Evenement e where TYPE_EVENEMENT=:x and e.curentDate >= :y ORDER BY e.idEvenement DESC");
		query.setParameter("y", weeckAgo);
		logger.info("weeck" + weeckAgo);
		query.setParameter("x", "ENTRETIENT");
		logger.info("le size de ENTRETIENT est" + query.getResultList().size());
		
		return query.getResultList();
	}


	@SuppressWarnings("unchecked")
	public List<Evenement> getNumberOfCurrentsRetardsAbstractJpa() {
		Date tomorrow = DateUtils.addDays(new Date(), +1);
		Date yesterday = DateUtils.addDays(new Date(), -1);
		Query query = em
				.createQuery("from Evenement e where TYPE_EVENEMENT=:x and e.curentDate BETWEEN :y AND :t ORDER BY e.idEvenement DESC");
		query.setParameter("x", "RETARD");
		query.setParameter("y", yesterday, TemporalType.DATE);
		query.setParameter("t", tomorrow, TemporalType.DATE);
		logger.info("les retards d'aujourdhuit sont :"
				+ query.getResultList().size());
		return query.getResultList();
	}

	
	@SuppressWarnings("unchecked")
	public List<Evenement> getNumberOfCurrentsAbsenceAbstractJpa() {
		Date tomorrow = DateUtils.addDays(new Date(), +1);
		Date yesterday = DateUtils.addDays(new Date(), -1);
		Query query = em
				.createQuery("from Evenement e where TYPE_EVENEMENT=:x and e.curentDate BETWEEN :y AND :t ORDER BY e.idEvenement DESC");
		query.setParameter("x", "ABSENCE");
		query.setParameter("y", yesterday, TemporalType.DATE);
		query.setParameter("t", tomorrow, TemporalType.DATE);
		logger.info("les absences d'aujourdhuit sont :"
				+ query.getResultList().size());
		return query.getResultList();
	}

	
	@SuppressWarnings("unchecked")
	public List<Evenement> getNumberOfCurrentsWarningAbstractJpa() {
		Date tomorrow = DateUtils.addDays(new Date(), +1);
		Date yesterday = DateUtils.addDays(new Date(), -1);
		Query query = em
				.createQuery("from Evenement e where TYPE_EVENEMENT=:x and e.curentDate BETWEEN :y AND :t ORDER BY e.idEvenement DESC");
		query.setParameter("x", "WARNING");
		query.setParameter("y", yesterday, TemporalType.DATE);
		query.setParameter("t", tomorrow, TemporalType.DATE);
		logger.info("les warnings d'aujourdhuit sont :"
				+ query.getResultList().size());
		return query.getResultList();
	}

	
	@SuppressWarnings("unchecked")
	public List<Evenement> getNumberOfCurrentsTopAbstractJpa() {
		Date tomorrow = DateUtils.addDays(new Date(), +1);
		Date yesterday = DateUtils.addDays(new Date(), -1);
		Query query = em
				.createQuery("from Evenement e where TYPE_EVENEMENT=:x and e.curentDate BETWEEN :y AND :t ORDER BY e.idEvenement DESC");
		query.setParameter("x", "TOP");
		query.setParameter("y", yesterday, TemporalType.DATE);
		query.setParameter("t", tomorrow, TemporalType.DATE);
		logger.info("les warnings d'aujourdhuit sont :"
				+ query.getResultList().size());
		return query.getResultList();
	}


	@SuppressWarnings("unchecked")
	public List<Evenement> getAllEvenementsRetardsAbstractJpa() {
		Query query = em
				.createQuery("from Evenement e where TYPE_EVENEMENT=:x ORDER BY e.idEvenement DESC");
		query.setParameter("x", "RETARD");
		logger.info("les retards  sont :" + query.getResultList().size());
		return query.getResultList();
	}

	
	@SuppressWarnings("unchecked")
	public List<Evenement> getAllEvenementsEntretientAbstractJpa() {
		Query query = em
				.createQuery("from Evenement e where TYPE_EVENEMENT=:x ORDER BY e.idEvenement DESC");
		query.setParameter("x", "ENTRETIENT");
		logger.info("les retards d'aujourdhuit sont :"
				+ query.getResultList().size());
		return query.getResultList();
	}

	
	@SuppressWarnings("unchecked")
	public List<Evenement> getAllEvenementsAbsencesAbstractJpa() {
		Query query = em
				.createQuery("from Evenement e where TYPE_EVENEMENT=:x ORDER BY e.idEvenement DESC");
		query.setParameter("x", "ABSENCE");
		logger.info("les absences  sont :" + query.getResultList().size());
		return query.getResultList();
	}

	
	@SuppressWarnings("unchecked")
	public List<Evenement> getAllEvenementsAbstractJpa() {
		Query query = em
				.createQuery("from Evenement e ORDER BY e.idEvenement DESC");
		logger.info("il existe :" + query.getResultList().size()
				+ "evenements dans la base de données");
		return query.getResultList();
	}


	@SuppressWarnings("unchecked")
	public List<Evenement> getAllEvenementsBySessionAbstractJpa(Long idSession) {
		Query query = em
				.createQuery("from Evenement e WHERE e.sessionEtudiant.idSession=:x ORDER BY e.idEvenement DESC");
		query.setParameter("x", idSession);
		logger.info("il existe :" + query.getResultList().size()
				+ "evenements dans la session");
		return query.getResultList();
	}

	
}
