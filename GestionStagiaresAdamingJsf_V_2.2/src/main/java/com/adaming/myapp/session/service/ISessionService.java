package com.adaming.myapp.session.service;

import java.util.List;

import com.adaming.myapp.entities.SessionEtudiant;
import com.adaming.myapp.exception.AddSessionException;

public interface ISessionService {

	 SessionEtudiant addSessionStudent(SessionEtudiant se,Long idSpecialite) throws AddSessionException;
	
	 SessionEtudiant updateSessionEtudiant(SessionEtudiant se,Long idSpecialite);
	
	 SessionEtudiant getSessionEtudiantById(Long idSessionEtudiant);
	
	 List<SessionEtudiant> getAllSessions();
	
	 List<SessionEtudiant> getAllSessionsInProgress();
}
