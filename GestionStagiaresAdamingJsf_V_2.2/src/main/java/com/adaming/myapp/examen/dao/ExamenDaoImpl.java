package com.adaming.myapp.examen.dao;
import com.adaming.myapp.entities.Examen;

public class ExamenDaoImpl extends ExamenAbstractJpa implements IExamenDao{

	@Override
	public Examen addExamen(final Examen examen, final Long idEtudiant, final Long idModule,
			final Long idSession) {
		// TODO Auto-generated method stub
		return addExamenAbstractJpa(examen, idEtudiant, idModule, idSession);
	}

	@Override
	public Examen verifyExistingExamen(final Long idEtdudiant, final Long idModule, final Long idSession) {
		// TODO Auto-generated method stub
		return verifyExistingExamenAbstractJpa(idEtdudiant, idModule, idSession);
	}

}
