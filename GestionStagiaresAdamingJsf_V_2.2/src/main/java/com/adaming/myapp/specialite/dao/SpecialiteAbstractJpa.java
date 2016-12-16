package com.adaming.myapp.specialite.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.adaming.myapp.entities.Specialite;
import com.adaming.myapp.exception.AddSpecialiteException;

public abstract class SpecialiteAbstractJpa {

	@PersistenceContext
	private EntityManager em;

	Logger log = Logger.getLogger("SpecialiteAbstractJpa");


	public Specialite addSpecialiteAbstractJpa(Specialite sp)
			throws AddSpecialiteException {
		em.persist(sp);
		log.info("la specialite " + sp.getIdSpecialite()
				+ "a bien été enregister");
		return sp;
	}


	public Specialite updateSpecialiteAbstractJpa(Specialite sp) {
		em.merge(sp);
		log.info("la specialite " + sp.getIdSpecialite()
				+ "a bien été Modifier");
		return sp;
	}


	public Specialite getSpecialiteByIdAbstractJpa(Long idSpecialite) {
		Specialite sp = em.find(Specialite.class, idSpecialite);
		log.info("la specialite " + sp.getIdSpecialite()
				+ "a bien été recupérer");
		return sp;
	}

	/*@SuppressWarnings("unchecked")
	public List<Specialite> getAllSpecAbstractJpa() {
		Query query = em.createQuery("from Specialite");
		log.info("il existe " + query.getResultList().size()
				+ " specialites dans l'applications");
		return query.getResultList();
	}*/
	
	@SuppressWarnings("unchecked")
	public List<Specialite> getAllSpecV2AbstractJpa() {
		Query query = em.createNativeQuery("SELECT s.idSpecialite,s.designation FROM Specialite s",Specialite.class);
		log.info("il existe " + query.getResultList().size()
				+ " specialites dans l'applications");
		return query.getResultList();
	}
}
