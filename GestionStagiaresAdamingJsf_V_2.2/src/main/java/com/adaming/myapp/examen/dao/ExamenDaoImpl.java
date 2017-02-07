package com.adaming.myapp.examen.dao;
import com.adaming.myapp.entities.Examen;

public class ExamenDaoImpl extends ExamenAbstractJpa implements IExamenDao{

	@Override
	public Examen addExamen(Examen examen, Long idEtudiant, Long idModule,
			Long idSession) {
		// TODO Auto-generated method stub
		return addExamenAbstractJpa(examen, idEtudiant, idModule, idSession);
	}

	@Override
	public Examen verifyExistingExamen(Long idEtdudiant, Long idModule, Long idSession) {
		// TODO Auto-generated method stub
		return verifyExistingExamenAbstractJpa(idEtdudiant, idModule, idSession);
	}

}
