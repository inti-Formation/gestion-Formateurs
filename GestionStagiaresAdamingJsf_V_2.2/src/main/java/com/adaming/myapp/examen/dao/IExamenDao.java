package com.adaming.myapp.examen.dao;

import java.util.List;

import com.adaming.myapp.entities.Etudiant;
import com.adaming.myapp.entities.Examen;
import com.adaming.myapp.entities.Module;
import com.adaming.myapp.entities.Question;
import com.adaming.myapp.entities.SessionEtudiant;
import com.adaming.myapp.exception.AddExamenException;

public interface IExamenDao {

	
	 Object  getScoreExamen(Long idSession,Long idEtudiant,Long idModule);
	
	 List<Examen> getAllExamensBySession(Long idSession);
	
	 List<Examen> getAllExamenByEtudiant(Long idEtudiant);
	
	 Examen addExamenV2(Examen ex, Long idEtudiant, Long idSession,
			Long idModule);
	
	
	
	
}
