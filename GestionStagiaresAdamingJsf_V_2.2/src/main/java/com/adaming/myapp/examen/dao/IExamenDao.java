package com.adaming.myapp.examen.dao;

import com.adaming.myapp.entities.Examen;

public interface IExamenDao{

	Examen addExamen(final Examen examen,final Long idEtudiant,final Long idModule,final Long idSession);
	
	Examen verifyExistingExamen(final Long idEtdudiant,final Long idModule, final Long idSession);
}
