package com.adaming.myapp.specialite.service;

import java.util.List;

import com.adaming.myapp.entities.Specialite;
import com.adaming.myapp.exception.AddSpecialiteException;

public interface ISpecialiteService {

	 Specialite addSpecialite(Specialite sp) throws AddSpecialiteException;
	
	 Specialite updateSpecialite(Specialite sp);
	
	 Specialite getSpecialiteById(Long idSpecialite);
	
	 List<Specialite> getAllSpec();
}
