package com.adaming.myapp.session.dao;
import java.util.List;

import com.adaming.myapp.dto.SessionDto;
import com.adaming.myapp.entities.SessionEtudiant;
import com.adaming.myapp.exception.AddSessionException;

public class SessionDaoImpl extends SessionAbstractJpa implements ISessionDao{

	@Override
	public SessionEtudiant addSessionStudent(SessionEtudiant se,
			Long idSpecialite, Long idSite, Long idSalle)
			throws AddSessionException {
		// TODO Auto-generated method stub
		return addSessionStudentAbstractJpa(se, idSpecialite, idSite, idSalle);
	}

	@Override
	public SessionEtudiant updateSessionEtudiant(SessionEtudiant se,
			Long idSpecialite,Long idSite,Long idSalle) {
		return updateSessionEtudianAbstractJpa(se,idSpecialite,idSite,idSalle);
	}

	@Override
	public SessionEtudiant getSessionEtudiantById(Long idSessionEtudiant) {
		return getSessionEtudiantByIdAbstractJpa(idSessionEtudiant);
	}

	@Override
	public List<SessionEtudiant> getAllSessions() {
		return getAllSessionsAbstractJpa();
	}

	@Override
	public List<SessionEtudiant> getAllSessionsInProgress() {
		return getAllSessionsInProgressAbstractJpa();
	}

	@Override
	public List<Object[]> getSallesDisponible(Long idSalle) {
		// TODO Auto-generated method stub
		return getSallesDisponibleAbstracJpa(idSalle);
	}

	@Override
	public List<Object[]> getAllSessionsInProgressV2() {
		return getAllSessionsInProgressAbstractJpaV2();
	}

	@Override
	public List<Object[]> getAllSessionsV2() {
		// TODO Auto-generated method stub
		return getAllSessionsAbstractJpaV2();
	}

	@Override
	public SessionEtudiant getSessionByFormateur(Long idFormateur) {
		// TODO Auto-generated method stub
		return getSessionByFormateurAbstractJpa(idFormateur);
	}

	@Override
	public SessionEtudiant getSessionByEtudiant(Long idEtudiant) {
		// TODO Auto-generated method stub
		return getSessionByEtudiantAbstractJpa(idEtudiant);
	}

	
	
	

	

}
