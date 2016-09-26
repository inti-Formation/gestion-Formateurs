package com.adaming.myapp.session.service;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.entities.SessionEtudiant;
import com.adaming.myapp.exception.AddSessionException;
import com.adaming.myapp.session.dao.ISessionDao;
@Transactional
public class SessionServiceImpl implements ISessionService{
    
	Logger log = Logger.getLogger("SessionServiceImpl");
	
	private ISessionDao dao;

	public void setDao(ISessionDao dao) {
		this.dao = dao;
		log.info("<------------Dao Session Injected------>");
	}

	@Override
	public SessionEtudiant addSessionStudent(SessionEtudiant se,
			Long idSpecialite) throws AddSessionException {
		// TODO Auto-generated method stub
		return dao.addSessionStudent(se, idSpecialite);
	}

	@Override
	public SessionEtudiant updateSessionEtudian(SessionEtudiant se,
			Long idSpecialite) {
		// TODO Auto-generated method stub
		return dao.updateSessionEtudian(se, idSpecialite);
	}

	@Override
	public SessionEtudiant getSessionEtudiantById(Long idSessionEtudiant) {
		// TODO Auto-generated method stub
		return dao.getSessionEtudiantById(idSessionEtudiant);
	}

	@Override
	public List<SessionEtudiant> getAllSessions() {
		// TODO Auto-generated method stub
		return dao.getAllSessions();
	}

	@Override
	public List<SessionEtudiant> getAllSessionsInProgress() {
		// TODO Auto-generated method stub
		return dao.getAllSessionsInProgress();
	}

}
