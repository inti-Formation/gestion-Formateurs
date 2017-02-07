package com.adaming.myapp.specialite.dao;

import java.util.List;
import com.adaming.myapp.entities.Specialite;
public class SpecialiteDaoImpl extends SpecialiteAbstractJpa implements ISpecialiteDao {


	@Override
	public Specialite addSpecialite(Specialite sp)
    {
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
	public List<Specialite> getAllSpecV2() {
		// TODO Auto-generated method stub
		return getAllSpecV2AbstractJpa();
	}

	@Override
	public Specialite verifyExistingSpecialite(String name) {
		// TODO Auto-generated method stub
		return verifyExistingSpecialiteAbstractJpa(name);
	}

	

}
