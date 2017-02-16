package com.adaming.myapp.contrat.dao;

import com.adaming.myapp.entities.Contrat;
import com.adaming.myapp.persistence.IGenericDao;
/**
 * 
 * @author adel
 * @date 10/10/2016
 * @version 1.0.0
 * */

public interface IContratDao extends IGenericDao<Contrat> {

	Contrat addContrat(Contrat c,Long idEtudiant);
	
	int nombreContrat();
	
	void remove(Long idContrat);
	
}
