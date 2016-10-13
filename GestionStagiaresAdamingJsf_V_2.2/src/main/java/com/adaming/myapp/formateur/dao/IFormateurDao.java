package com.adaming.myapp.formateur.dao;

import java.util.List;

import com.adaming.myapp.entities.Formateur;
import com.adaming.myapp.exception.VerificationInDataBaseException;

public interface IFormateurDao {

	public Formateur addFormateur(Formateur f)
			throws VerificationInDataBaseException;

	public void addFormateurToSession(Long idSession, Long idFormateur);

	public List<Formateur> getAllFormateurs();

	public Formateur getFormateur(String mail);

}
