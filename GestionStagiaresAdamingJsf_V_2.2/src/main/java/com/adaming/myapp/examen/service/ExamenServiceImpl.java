package com.adaming.myapp.examen.service;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.entities.Examen;
import com.adaming.myapp.entities.Question;
import com.adaming.myapp.examen.dao.IExamenDao;
import com.adaming.myapp.exception.AddExamenException;
@Transactional
public class ExamenServiceImpl implements IExamenService{
    
	private IExamenDao dao;
	
	Logger log = Logger.getLogger("ExamenServiceImpl");

	public void setDao(IExamenDao dao) {
		this.dao = dao;
		log.info("<---------dao Examen Injected--------->");
	}

	@Override
	public Object getScoreExamen(Long idSession, Long idEtudiant, Long idModule) {
		// TODO Auto-generated method stub
		return dao.getScoreExamen(idSession, idEtudiant, idModule);
	}

	@Override
	public List<Examen> getAllExamensBySession(Long idSession) {
		// TODO Auto-generated method stub
		return dao.getAllExamensBySession(idSession);
	}

	@Override
	public List<Examen> getAllExamenByEtudiant(Long idEtudiant) {
		// TODO Auto-generated method stub
		return dao.getAllExamenByEtudiant(idEtudiant);
	}

	@Override
	public Examen addExamenV2(Examen ex, Long idEtudiant, Long idSession,
			Long idModule) {
		// TODO Auto-generated method stub
		return dao.addExamenV2(ex, idEtudiant, idSession, idModule);
	}

	
	

	
	
}
