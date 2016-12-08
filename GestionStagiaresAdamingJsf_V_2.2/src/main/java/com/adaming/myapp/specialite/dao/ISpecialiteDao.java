package com.adaming.myapp.specialite.dao;

import java.util.List;

import com.adaming.myapp.entities.Specialite;
import com.adaming.myapp.exception.AddSpecialiteException;

public interface ISpecialiteDao {

	 Specialite addSpecialite(Specialite sp) throws AddSpecialiteException;
	
	 Specialite updateSpecialite(Specialite sp);
	
	 Specialite getSpecialiteById(Long idSpecialite);
	
	 List<Specialite> getAllSpec();
}
