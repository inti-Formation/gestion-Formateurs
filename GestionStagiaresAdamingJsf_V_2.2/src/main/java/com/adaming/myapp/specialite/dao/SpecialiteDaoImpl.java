package com.adaming.myapp.specialite.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.adaming.myapp.entities.Specialite;
import com.adaming.myapp.exception.AddSpecialiteException;

public class SpecialiteDaoImpl extends SpecialiteAbstractJpa implements ISpecialiteDao {


	@Override
	public Specialite addSpecialite(Specialite sp)
			throws AddSpecialiteException {
		return addSpecialiteAbstractJpa(sp);
	}

	@Override
	public Specialite updateSpecialite(Specialite sp) {
		return updateSpecialiteAbstractJpa(sp);
	}

	@Override
	public Specialite getSpecialiteById(Long idSpecialite) {
		return getSpecialiteByIdAbstractJpa(idSpecialite);
	}

	@Override
	public List<Specialite> getAllSpec() {
		return getAllSpecAbstractJpa();
	}

}
