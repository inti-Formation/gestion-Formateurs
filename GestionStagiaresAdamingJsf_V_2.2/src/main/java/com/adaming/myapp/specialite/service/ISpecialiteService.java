package com.adaming.myapp.specialite.service;

import java.util.List;

import com.adaming.myapp.entities.Specialite;
import com.adaming.myapp.exception.AddSpecialiteException;
import com.adaming.myapp.exception.VerificationInDataBaseException;

public interface ISpecialiteService {

	Specialite addSpecialite(Specialite sp) throws VerificationInDataBaseException;

	Specialite updateSpecialite(Specialite sp);

	Specialite getSpecialiteById(Long idSpecialite);
	
	Specialite verifyExistingSpecialite(String name);

	List<Specialite> getAllSpecV2();
	
}
