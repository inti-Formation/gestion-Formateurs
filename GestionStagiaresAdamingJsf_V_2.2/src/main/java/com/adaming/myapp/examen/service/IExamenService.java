package com.adaming.myapp.examen.service;


import java.util.List;

import com.adaming.myapp.entities.Examen;
import com.adaming.myapp.entities.Question;
import com.adaming.myapp.exception.AddExamenException;


public interface IExamenService {


	 Object getScoreExamen(Long idSession,Long idEtudiant,Long idModule);
	
	 List<Examen> getAllExamensBySession(Long idSession);
	
	 List<Examen> getAllExamenByEtudiant(Long idEtudiant);
	
	 Examen addExamenV2(Examen ex, Long idEtudiant, Long idSession,
			Long idModule);
	
	
}
