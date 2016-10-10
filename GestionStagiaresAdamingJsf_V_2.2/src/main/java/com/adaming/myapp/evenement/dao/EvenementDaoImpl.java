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

public class EvenementDaoImpl implements IEvenementDao{
   
	@PersistenceContext
	private EntityManager em;
	
	Logger logger = Logger.getLogger("EvenementDaoImpl");

	@Override
	public Evenement addEvenement(Evenement e, Long idSession, Long idEtudiant) throws VerificationInDataBaseException {
		SessionEtudiant se = em.find(SessionEtudiant.class,idSession);
		Etudiant e1 = em.find(Etudiant.class,idEtudiant);
	    e.setEtudiant(e1);
	    e.setSessionEtudiant(se);
	    List<Evenement> evenements = null;
	    evenements=getAllEvenements();
	    for(Evenement evenement:evenements){
	    	if(evenement != null){
	    		if(evenement.getSessionEtudiant().getIdSession() == idSession 
	    		&& evenement.getEtudiant().getIdEtudiant() == idEtudiant
	    		&& evenement.getStartDate().compareTo(e.getStartDate()) ==0
	    		&& evenement.getEndDate().compareTo(e.getEndDate()) ==0){
	    			throw new VerificationInDataBaseException(" cette evènement est déja signalé");
	    		}
	    	}
	    }
		em.persist(e);
		logger.info("l'evenement a bien été enregistrer "+"Session id : "+idSession+"etudiant :"+idEtudiant);
		return e;
	}
	@Override
	public Evenement AddWarningAndTop(Evenement e, Long idSession,
			Long idEtudiant) throws VerificationInDataBaseException {
		SessionEtudiant se = em.find(SessionEtudiant.class,idSession);
		Etudiant e1 = em.find(Etudiant.class,idEtudiant);
	    e.setEtudiant(e1);
	    e.setSessionEtudiant(se);
	    List<Evenement> evenements = null;
	    evenements=getAllEvenements();
	    for(Evenement evenement:evenements){
	    	if(evenement != null){
	    		if(evenement.getSessionEtudiant().getIdSession() == idSession 
	    		&& evenement.getEtudiant().getIdEtudiant() == idEtudiant)
	            {
	    			throw new VerificationInDataBaseException("l' etudiant "+evenement.getEtudiant().getNomEtudiant()+" , "+evenement.getEtudiant().getPrenomEtudiant()+" est déja signalé");
	    		}
	    	}
	    }
		em.persist(e);
		logger.info("l'evenement a bien été enregistrer "+"Session id : "+idSession+"etudiant :"+idEtudiant);
		return e;
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<Evenement> getEvenementsRetards() throws EvenementNotFoundException {
		Date weeckAgo = DateUtils.addDays(new Date(), -6);
		Query query = em.createQuery("from Evenement e where TYPE_EVENEMENT =:x and e.curentDate >= :y ORDER BY e.idEvenement DESC");
		query.setParameter("y",weeckAgo);
		logger.info("weeck"+weeckAgo);
		query.setParameter("x","RETARD");
		logger.info("le size de retarad est"+query.getResultList().size());
		if(query.getResultList().size() == 0){
			throw new EvenementNotFoundException("Aucun retard mentionné !");
		}
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Evenement> getEvenementsAbsences() throws EvenementNotFoundException {
		Date weeckAgo = DateUtils.addDays(new Date(), -6);
		Query query = em.createQuery("from Evenement e where TYPE_EVENEMENT =:x and e.curentDate >= :y ORDER BY e.idEvenement DESC");
		query.setParameter("y",weeckAgo);
		logger.info("weeck"+weeckAgo);
		query.setParameter("x","ABSENCE");
		logger.info("le size de Absences est"+query.getResultList().size());
		if(query.getResultList().size() == 0){
			throw new EvenementNotFoundException("Aucune absence mentionnée !");
		}
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Evenement> getEvenementsEntretien() throws EvenementNotFoundException {
		Date weeckAgo = DateUtils.addDays(new Date(), -6);
		Query query = em.createQuery("from Evenement e where TYPE_EVENEMENT=:x and e.curentDate >= :y ORDER BY e.idEvenement DESC");
		query.setParameter("y",weeckAgo);
		logger.info("weeck"+weeckAgo);
		query.setParameter("x","ENTRETIENT");
		logger.info("le size de ENTRETIENT est"+query.getResultList().size());
		if(query.getResultList().size() == 0){
			throw new EvenementNotFoundException("Aucun entretien mentionné !");
		}
		return query.getResultList();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Evenement> getNumberOfCurrentsRetards() {
		Date tomorrow = DateUtils.addDays(new Date(), +1);
		Date yesterday = DateUtils.addDays(new Date(), -1);
		Query query = em.createQuery("from Evenement e where TYPE_EVENEMENT=:x and e.curentDate BETWEEN :y AND :t ORDER BY e.idEvenement DESC");
		query.setParameter("x","RETARD");
		query.setParameter("y",yesterday,TemporalType.DATE);
		query.setParameter("t",tomorrow,TemporalType.DATE);
		logger.info("les retards d'aujourdhuit sont :"+query.getResultList().size());
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Evenement> getNumberOfCurrentsAbsence() {
		Date tomorrow = DateUtils.addDays(new Date(), +1);
		Date yesterday = DateUtils.addDays(new Date(), -1);
		Query query = em.createQuery("from Evenement e where TYPE_EVENEMENT=:x and e.curentDate BETWEEN :y AND :t ORDER BY e.idEvenement DESC");
		query.setParameter("x","ABSENCE");
		query.setParameter("y",yesterday,TemporalType.DATE);
		query.setParameter("t",tomorrow,TemporalType.DATE);
		logger.info("les absences d'aujourdhuit sont :"+query.getResultList().size());
		return query.getResultList();
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Evenement> getNumberOfCurrentsWarning() {
		Date tomorrow = DateUtils.addDays(new Date(), +1);
		Date yesterday = DateUtils.addDays(new Date(), -1);
		Query query = em.createQuery("from Evenement e where TYPE_EVENEMENT=:x and e.curentDate BETWEEN :y AND :t ORDER BY e.idEvenement DESC");
		query.setParameter("x","WARNING");
		query.setParameter("y",yesterday,TemporalType.DATE);
		query.setParameter("t",tomorrow,TemporalType.DATE);
		logger.info("les warnings d'aujourdhuit sont :"+query.getResultList().size());
		return query.getResultList();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Evenement> getNumberOfCurrentsTop() {
		Date tomorrow = DateUtils.addDays(new Date(), +1);
		Date yesterday = DateUtils.addDays(new Date(), -1);
		Query query = em.createQuery("from Evenement e where TYPE_EVENEMENT=:x and e.curentDate BETWEEN :y AND :t ORDER BY e.idEvenement DESC");
		query.setParameter("x","TOP");
		query.setParameter("y",yesterday,TemporalType.DATE);
		query.setParameter("t",tomorrow,TemporalType.DATE);
		logger.info("les warnings d'aujourdhuit sont :"+query.getResultList().size());
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Evenement> getAllEvenementsRetards() {
		Query query = em.createQuery("from Evenement e where TYPE_EVENEMENT=:x ORDER BY e.idEvenement DESC");
		query.setParameter("x","RETARD");
		logger.info("les retards  sont :"+query.getResultList().size());
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Evenement> getAllEvenementsEntretient() {
		Query query = em.createQuery("from Evenement e where TYPE_EVENEMENT=:x ORDER BY e.idEvenement DESC");
		query.setParameter("x","ENTRETIENT");
		logger.info("les retards d'aujourdhuit sont :"+query.getResultList().size());
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Evenement> getAllEvenementsAbsences() {
		Query query = em.createQuery("from Evenement e where TYPE_EVENEMENT=:x ORDER BY e.idEvenement DESC");
		query.setParameter("x","ABSENCE");
		logger.info("les absences  sont :"+query.getResultList().size());
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Evenement> getAllEvenements() {
		Query query = em.createQuery("from Evenement e ORDER BY e.idEvenement DESC");
		logger.info("il existe :"+query.getResultList().size()+"evenements dans la base de données");
		return query.getResultList();
	}
	
	

	
	
	
}
