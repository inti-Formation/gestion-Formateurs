package com.adaming.myapp.session.dao;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.adaming.myapp.entities.SessionEtudiant;
import com.adaming.myapp.entities.Specialite;
import com.adaming.myapp.exception.AddSessionException;

public class SessionDaoImpl implements ISessionDao{
   
	@PersistenceContext
	private EntityManager em;
	
    Logger log = Logger.getLogger("SessionDaoImpl");
    
	@Override
	public SessionEtudiant addSessionStudent(SessionEtudiant se,
			Long idSpecialite) throws AddSessionException {
		Specialite sp = em.find(Specialite.class,idSpecialite);
		se.setSpecialite(sp);
		sp.getSessionEtudiant().add(se);
		if(se.getDateDebute().after(se.getDateFin())){
			throw new AddSessionException("Veuillez vous assurer que la date de Fin est postérieure à la date de départ.");
		}
		em.persist(se);
		log.info(" la session+"+se.getIdSession()+ "a bien été enregistrer");
		return se;
	}

	@Override
	public SessionEtudiant updateSessionEtudian(SessionEtudiant se,
			Long idSpecialite) {
		Specialite sp = em.find(Specialite.class,idSpecialite);
		se.setSpecialite(sp);
		em.merge(se);
		log.info(" la session+"+se.getIdSession()+ "a bien été Modifié");
		return se;
	}

	@Override
	public SessionEtudiant getSessionEtudiantById(Long idSessionEtudiant) {
		SessionEtudiant se = em.find(SessionEtudiant.class,idSessionEtudiant);
		log.info("la session"+se.getIdSession()+" a bien été recupérer");
		return se;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SessionEtudiant> getAllSessions() {
		Query query = em.createQuery("from SessionEtudiant s Order By s.dateFin DESC");
		log.info("il existe"+query.getResultList().size());
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SessionEtudiant> getAllSessionsInProgress() {
		Query query = em.createQuery("from SessionEtudiant s where s.dateFin > CURRENT_DATE");
		log.info("il existe"+query.getResultList().size()+" sessions en cours");
		return query.getResultList();
	}

}
