package com.adaming.myapp.session.dao;

import java.util.List;

import com.adaming.myapp.entities.SessionEtudiant;
import com.adaming.myapp.exception.AddSessionException;

public interface ISessionDao {

	public SessionEtudiant addSessionStudent(SessionEtudiant se,Long idSpecialite) throws AddSessionException;
	
	public SessionEtudiant updateSessionEtudian(SessionEtudiant se,Long idSpecialite);
	
	public SessionEtudiant getSessionEtudiantById(Long idSessionEtudiant);
	
	public List<SessionEtudiant> getAllSessions();
	
	public List<SessionEtudiant> getAllSessionsInProgress();
	
	
}
