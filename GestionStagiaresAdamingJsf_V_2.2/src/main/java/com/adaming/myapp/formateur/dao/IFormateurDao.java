package com.adaming.myapp.formateur.dao;


import com.adaming.myapp.entities.Formateur;
import com.adaming.myapp.exception.VerificationInDataBaseException;
import com.adaming.myapp.persistence.IGenericDao;

public interface IFormateurDao extends IGenericDao<Formateur> {

	 Formateur addFormateur(Formateur f)
			throws VerificationInDataBaseException;

	 void addFormateurToSession(Long idSession, Long idFormateur);

	 Formateur getFormateur(String mail);
	

}
