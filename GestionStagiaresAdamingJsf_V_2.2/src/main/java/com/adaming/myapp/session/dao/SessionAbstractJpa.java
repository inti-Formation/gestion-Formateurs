package com.adaming.myapp.session.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.adaming.myapp.entities.SessionEtudiant;
import com.adaming.myapp.entities.Specialite;
import com.adaming.myapp.exception.AddSessionException;

public abstract class SessionAbstractJpa {

	@PersistenceContext
	private EntityManager em;
	
    Logger log = Logger.getLogger("SessionAbstractJpa");
    

	public SessionEtudiant addSessionStudentAbstractJpa(SessionEtudiant se,
			Long idSpecialite) throws AddSessionException {
		Specialite sp = em.find(Specialite.class,idSpecialite);
		se.setSpecialite(sp);
		sp.getSessionEtudiant().add(se);
		em.persist(se);
		log.info(" la session+"+se.getIdSession()+ "a bien été enregistrer");
		return se;
	}


	public SessionEtudiant updateSessionEtudianAbstractJpa(SessionEtudiant se,
			Long idSpecialite) {
		Specialite sp = em.find(Specialite.class,idSpecialite);
		se.setSpecialite(sp);
		em.merge(se);
		log.info(" la session+"+se.getIdSession()+ "a bien été Modifié");
		return se;
	}


	public SessionEtudiant getSessionEtudiantByIdAbstractJpa(Long idSessionEtudiant) {
		SessionEtudiant se = em.find(SessionEtudiant.class,idSessionEtudiant);
		log.info("la session"+se.getIdSession()+" a bien été recupérer");
		return se;
	}


	@SuppressWarnings("unchecked")
	public List<SessionEtudiant> getAllSessionsAbstractJpa() {
		Query query = em.createQuery("from SessionEtudiant s Order By s.dateFin DESC");
		log.info("il existe"+query.getResultList().size());
		return query.getResultList();
	}


	@SuppressWarnings("unchecked")
	public List<SessionEtudiant> getAllSessionsInProgressAbstractJpa() {
		Query query = em.createQuery("from SessionEtudiant s where s.dateFin > CURRENT_DATE");
		log.info("il existe"+query.getResultList().size()+" sessions en cours");
		return query.getResultList();
	}

}
