package com.adaming.myapp.formateur.dao;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.Query;

import com.adaming.myapp.entities.Formateur;
import com.adaming.myapp.entities.SessionEtudiant;
import com.adaming.myapp.exception.VerificationInDataBaseException;
import com.adaming.myapp.persistence.AbstractJpaDao;
import com.adaming.myapp.tools.LoggerConfig;

public class FormateurDaoImpl extends AbstractJpaDao<Formateur> implements IFormateurDao {
    
    
	@Override
	public List<Formateur> getAll() {
		// TODO Auto-generated method stub
		return getAllAbstractJpa();
	}

	@Override
	public Formateur getOne(Long id) {
			final String SQL = "from Formateur f join fetch f.sessionsEtudiant where f.idFormateur = :x";
			Query query = em.createQuery(SQL).setParameter("x",id);
			Formateur formateur = null;
			if(query.getResultList() != null && !query.getResultList().isEmpty()){
				 formateur = (Formateur) query.getResultList().get(0);
			}
			return formateur;
	}

	@Override
	public Formateur addFormateur(Formateur f){
		
		em.persist(f);
		LoggerConfig.logInfo("le formateur " + f.getNom()
				+ "à bien été criée avec success");
		return f;
	}

	@Override
	public void addFormateurToSession(Long idSession, Long idFormateur) {
		SessionEtudiant session = em.find(SessionEtudiant.class, idSession);
		Formateur f = em.find(Formateur.class, idFormateur);
		session.getFormateurs().add(f);
		f.getSessionsEtudiant().add(session);
		LoggerConfig.logInfo("le formateur " + f.getNom()
				+ " a bien été attribué à la session " + session.getIdSession());
	}


	@Override
	@SuppressWarnings("unchecked")
	public Formateur getFormateur(String mail) {

		Query query = em.createQuery("From Formateur f where f.mail=:x");
		query.setParameter("x", mail);
		List<Formateur> u = query.getResultList();

		return u.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getFormateuByName(String nom, Date dateDeNaissance,
			String mail) {
		Query query = em.createQuery("Select f.nom,f.dateDeNaissance,f.mail from Formateur f where f.nom =:x and f.dateDeNaissance =:y or f.mail =:z");
		query.setParameter("x",nom);
		query.setParameter("y",dateDeNaissance);
		query.setParameter("z",mail);
		query.setMaxResults(1);
		return query.getResultList();
	}

	@Override
	public  SessionEtudiant verifyExistingAffectation(final Long idFormateur,final Long idSession){
		final String SQL  = "from SessionEtudiant se join fetch se.formateurs f where f.idFormateur =:x and se.idSession =:y";
		Query query = em.createQuery(SQL).setParameter("x",idFormateur).setParameter("y",idSession);
		SessionEtudiant sessionEtudiant = null;
		if(query.getResultList() != null && !query.getResultList().isEmpty()){
			sessionEtudiant = (SessionEtudiant) query.getResultList().get(0);
		}
		return sessionEtudiant;
	}

	

}
