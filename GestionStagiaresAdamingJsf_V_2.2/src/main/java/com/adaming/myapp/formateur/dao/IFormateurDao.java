package com.adaming.myapp.formateur.dao;


import java.util.Date;
import java.util.List;
import com.adaming.myapp.entities.Formateur;
import com.adaming.myapp.entities.SessionEtudiant;
import com.adaming.myapp.persistence.IGenericDao;

public interface IFormateurDao extends IGenericDao<Formateur> {

	 Formateur addFormateur(final Formateur f);
	
	 SessionEtudiant verifyExistingAffectation(final Long idFormateur,final Long idSession);

	 void addFormateurToSession(final Long idSession, final Long idFormateur);

	 Formateur getFormateur(final String mail);
	 
	 List<Object[]> getFormateuByName(final String nom,final Date dateDeNaissance,final String mail);
	

}
