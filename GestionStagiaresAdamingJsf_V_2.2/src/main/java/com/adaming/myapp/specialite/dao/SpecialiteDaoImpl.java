package com.adaming.myapp.specialite.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.adaming.myapp.entities.Specialite;
import com.adaming.myapp.exception.AddSpecialiteException;

public class SpecialiteDaoImpl implements ISpecialiteDao {

	@PersistenceContext
	private EntityManager em;

	Logger log = Logger.getLogger("SpecialiteDaoImpl");

	@Override
	public Specialite addSpecialite(Specialite sp)
			throws AddSpecialiteException {
		List<Specialite> tabSpecialite = null;
		tabSpecialite = getAllSpec();// get All Spécialitée disponibles
		for (Specialite s : tabSpecialite) {
			if (s.getDesignation().equals(sp.getDesignation())) {
				throw new AddSpecialiteException(
						"La Spécialitée "+s.getDesignation()+" Existe Déja !!");
			}
		}
		em.persist(sp);
		log.info("la specialite " + sp.getIdSpecialite()
				+ "a bien été enregister");
		return sp;
	}

	@Override
	public Specialite updateSpecialite(Specialite sp) {
		em.merge(sp);
		log.info("la specialite " + sp.getIdSpecialite()
				+ "a bien été Modifier");
		return sp;
	}

	@Override
	public Specialite getSpecialiteById(Long idSpecialite) {
		Specialite sp = em.find(Specialite.class, idSpecialite);
		log.info("la specialite " + sp.getIdSpecialite()
				+ "a bien été recupérer");
		return sp;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Specialite> getAllSpec() {
		Query query = em.createQuery("from Specialite");
		log.info("il existe " + query.getResultList().size()
				+ " specialites dans l'applications");
		return query.getResultList();
	}

}
