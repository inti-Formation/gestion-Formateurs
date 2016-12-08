package com.adaming.myapp.formateur.service;

import java.util.List;

import com.adaming.myapp.entities.Formateur;
import com.adaming.myapp.exception.VerificationInDataBaseException;

public interface IFormateurService {

	 Formateur addFormateur(Formateur f)
			throws VerificationInDataBaseException;

	 void addFormateurToSession(Long idSession, Long idFormateur);

	 List<Formateur> getAllFormateurs();

	 Formateur getFormateur(String mail);
	
	 Formateur getFormateurById(Long idFormateur);
}
