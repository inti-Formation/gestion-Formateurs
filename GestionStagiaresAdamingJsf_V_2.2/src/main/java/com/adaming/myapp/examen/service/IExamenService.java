package com.adaming.myapp.examen.service;


import java.util.List;

import com.adaming.myapp.entities.Examen;
import com.adaming.myapp.entities.Question;
import com.adaming.myapp.exception.AddExamenException;


public interface IExamenService {


	public Object getScoreExamen(Long idSession,Long idEtudiant,Long idModule);
	
	public List<Examen> getAllExamensBySession(Long idSession);
	
	public List<Examen> getAllExamenByEtudiant(Long idEtudiant);
	
	public Examen addExamenV2(Examen ex, Long idEtudiant, Long idSession,
			Long idModule);
	
	
}
