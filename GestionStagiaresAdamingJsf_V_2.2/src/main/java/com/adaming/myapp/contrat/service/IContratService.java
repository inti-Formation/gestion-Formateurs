package com.adaming.myapp.contrat.service;

import java.util.List;

import com.adaming.myapp.entities.Contrat;
import com.adaming.myapp.exception.VerificationInDataBaseException;
/**
 * 
 * @author adel
 * @date 10/10/2016
 * @version 1.0.0
 * */
public interface IContratService {
	
	Contrat addContrat(Contrat c,Long idEtudiant) throws VerificationInDataBaseException;

    List<Contrat> getAll();
	
	Contrat getOne(final Long id);
	
	int nombreContrat();
	
	void remove(final Long idContrat);
}
