package com.adaming.myapp.session.dao;
import java.util.List;
import com.adaming.myapp.entities.SessionEtudiant;
import com.adaming.myapp.exception.AddSessionException;

public class SessionDaoImpl extends SessionAbstractJpa implements ISessionDao{

	@Override
	public SessionEtudiant addSessionStudent(SessionEtudiant se,
			Long idSpecialite) throws AddSessionException {
		return addSessionStudentAbstractJpa(se, idSpecialite);
	}

	@Override
	public SessionEtudiant updateSessionEtudiant(SessionEtudiant se,
			Long idSpecialite) {
		return updateSessionEtudianAbstractJpa(se, idSpecialite);
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

}
