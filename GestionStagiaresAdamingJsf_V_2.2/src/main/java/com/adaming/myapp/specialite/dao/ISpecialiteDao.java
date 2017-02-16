package com.adaming.myapp.specialite.dao;

import java.util.List;

import com.adaming.myapp.entities.Module;
import com.adaming.myapp.entities.Specialite;
import com.adaming.myapp.exception.AddSpecialiteException;

public interface ISpecialiteDao {

	 Specialite addSpecialite(final Specialite sp);
	
	 Specialite updateSpecialite(final Specialite sp);
	
	 Specialite getSpecialiteById(final Long idSpecialite);
	
	 Specialite verifyExistingSpecialite(final String name);
	 
	 List<Specialite> getAllSpecV2();
}
