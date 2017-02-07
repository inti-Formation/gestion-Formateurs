package com.adaming.myapp.examen.dao;

import com.adaming.myapp.entities.Examen;

public interface IExamenDao{

	Examen addExamen(Examen examen,Long idEtudiant,Long idModule,Long idSession);
	
	Examen verifyExistingExamen(Long idEtdudiant,Long idModule,Long idSession);
}
