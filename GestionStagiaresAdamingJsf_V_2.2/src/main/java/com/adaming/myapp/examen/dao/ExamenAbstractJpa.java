package com.adaming.myapp.examen.dao;

import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.adaming.myapp.entities.Etudiant;
import com.adaming.myapp.entities.Examen;
import com.adaming.myapp.entities.Module;
import com.adaming.myapp.entities.SessionEtudiant;

public abstract class ExamenAbstractJpa {

	/**
	 * @see javax.persistence.EntityManager
	 **/
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Logger @see java.util.logging.Logger
	 **/

	public Examen addExamenAbstractJpa(final Examen examen, final Long idEtudiant,
			final Long idModule, final Long idSession) {

		Etudiant etudiant = entityManager.find(Etudiant.class, idEtudiant);
		Module module = entityManager.find(Module.class, idModule);
		SessionEtudiant session = entityManager.find(SessionEtudiant.class,
				idSession);
		examen.setEtudiant(etudiant);
		examen.setModule(module);
		examen.setSessionEtudiant(session);
		entityManager.persist(examen);
		return examen;
	}

	public Examen verifyExistingExamenAbstractJpa(final Long idEtdudiant, final Long idModule,
			final Long idSession) {
		final String SQL = "Select distinct e from Examen e join e.etudiant et join e.module m join e.sessionEtudiant se "
				+ "where et.idEtudiant =:x and m.idModule =:y and se.idSession =:z";
		Query query = entityManager.createQuery(SQL)
				.setParameter("x", idEtdudiant).setParameter("y", idModule)
				.setParameter("z", idSession);
		Examen examen = null;
		if(query.getResultList() != null && !query.getResultList().isEmpty()){
			 examen = (Examen) query.getResultList().get(0);
		 }
		return examen;
	}

}
