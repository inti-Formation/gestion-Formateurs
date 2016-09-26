package com.adaming.myapp.specialite.dao;

import java.util.List;

import com.adaming.myapp.entities.Specialite;
import com.adaming.myapp.exception.AddSpecialiteException;

public interface ISpecialiteDao {

	public Specialite addSpecialite(Specialite sp) throws AddSpecialiteException;
	
	public Specialite updateSpecialite(Specialite sp);
	
	public Specialite getSpecialiteById(Long idSpecialite);
	
	public List<Specialite> getAllSpec();
}
