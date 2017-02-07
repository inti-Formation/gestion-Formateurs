package com.adaming.myapp.examen.service;

import javax.management.Query;

import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.entities.Examen;
import com.adaming.myapp.examen.dao.IExamenDao;
import com.adaming.myapp.exception.VerificationInDataBaseException;
import com.adaming.myapp.tools.LoggerConfig;
@Transactional(readOnly = true)
public class ExamenServiceImpl implements IExamenService {
    
	private IExamenDao dao;
	
	public void setDao(IExamenDao dao) {
		this.dao = dao;
		LoggerConfig.logInfo("<-------Dao Examen Injecetd----->");
	}
	
	@Override
	@Transactional(readOnly = false)
	public Examen addExamen(Examen examen, Long idEtudiant, Long idModule,
			Long idSession) throws VerificationInDataBaseException {
		Examen examenExisting= verifyExistingExamen(idEtudiant, idModule, idSession);
		if(examenExisting != null){
			throw new VerificationInDataBaseException("l'examen en cours...");
		}
		
		return dao.addExamen(examen, idEtudiant, idModule, idSession);
	}

	@Override
	public Examen verifyExistingExamen(Long idEtdudiant, Long idModule, Long idSession) {
		
		return dao.verifyExistingExamen(idEtdudiant, idModule, idSession);
	}

}
