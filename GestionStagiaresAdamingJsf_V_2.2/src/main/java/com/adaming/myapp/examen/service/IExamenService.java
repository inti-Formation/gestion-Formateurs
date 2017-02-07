package com.adaming.myapp.examen.service;

import com.adaming.myapp.entities.Examen;
import com.adaming.myapp.exception.VerificationInDataBaseException;

public interface IExamenService {

	Examen addExamen(Examen examen,Long idEtudiant,Long idModule,Long idSession) throws VerificationInDataBaseException;
	
	Examen verifyExistingExamen(Long idEtdudiant,Long idModule,Long idSession);
}
