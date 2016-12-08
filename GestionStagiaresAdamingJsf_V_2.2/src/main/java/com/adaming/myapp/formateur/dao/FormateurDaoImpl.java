package com.adaming.myapp.formateur.dao;

import java.util.List;
import java.util.logging.Logger;
import javax.persistence.Query;
import com.adaming.myapp.entities.Formateur;
import com.adaming.myapp.entities.SessionEtudiant;
import com.adaming.myapp.exception.VerificationInDataBaseException;
import com.adaming.myapp.persistence.AbstractJpaDao;

public class FormateurDaoImpl extends AbstractJpaDao<Formateur> implements IFormateurDao {

	private final Logger LOGGER = Logger.getLogger("FormateurDaoImpl");
    
	@Override
	public List<Formateur> getAll() {
		// TODO Auto-generated method stub
		return getAllAbstractJpa();
	}

	@Override
	public Formateur getOne(Long id) {
		// TODO Auto-generated method stub
		return getOneAbstractJpa(id);
	}

	
	
	@Override
	public Formateur addFormateur(Formateur f)
			throws VerificationInDataBaseException {
		
		em.persist(f);
		LOGGER.info("le formateur " + f.getNom()
				+ "à bien été criée avec success");
		return f;
	}

	@Override
	public void addFormateurToSession(Long idSession, Long idFormateur) {
		SessionEtudiant session = em.find(SessionEtudiant.class, idSession);
		Formateur f = em.find(Formateur.class, idFormateur);
		session.getFormateurs().add(f);
		f.getSessionsEtudiant().add(session);
		LOGGER.info("le formateur " + f.getNom()
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

}
