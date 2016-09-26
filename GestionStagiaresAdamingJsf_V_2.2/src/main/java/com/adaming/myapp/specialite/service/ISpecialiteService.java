package com.adaming.myapp.specialite.service;

import java.util.List;

import com.adaming.myapp.entities.Specialite;
import com.adaming.myapp.exception.AddSpecialiteException;

public interface ISpecialiteService {

	public Specialite addSpecialite(Specialite sp) throws AddSpecialiteException;
	
	public Specialite updateSpecialite(Specialite sp);
	
	public Specialite getSpecialiteById(Long idSpecialite);
	
	public List<Specialite> getAllSpec();
}
