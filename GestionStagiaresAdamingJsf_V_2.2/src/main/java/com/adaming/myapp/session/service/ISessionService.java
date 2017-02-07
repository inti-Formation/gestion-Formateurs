package com.adaming.myapp.session.service;

import java.util.List;

import com.adaming.myapp.dto.SessionDto;
import com.adaming.myapp.entities.SessionEtudiant;
import com.adaming.myapp.exception.AddSessionException;
import com.adaming.myapp.exception.VerificationInDataBaseException;

public interface ISessionService {

	 SessionEtudiant addSessionStudent(SessionEtudiant se,Long idSpecialite,Long idSite,Long idSalle) throws AddSessionException;
	
	 SessionEtudiant updateSessionEtudiant(SessionEtudiant se,
				Long idSpecialite,Long idSite,Long idSalle) throws AddSessionException;
	
	
	 SessionEtudiant getSessionEtudiantById(final Long idSessionEtudiant);
	
	 List<SessionEtudiant> getAllSessions();
	
	 List<SessionEtudiant> getAllSessionsInProgress();
	 
	 List<Object[]> getSallesDisponible(final Long idSalle);
	 
	 List<Object[]> getAllSessionsInProgressV2();
	 
	 List<Object[]> getAllSessionsV2();
	 
	 SessionEtudiant getSessionByFormateur(final Long idFormateur) throws VerificationInDataBaseException;
	 
	 SessionEtudiant getSessionByEtudiant(final Long idEtudiant) throws VerificationInDataBaseException;
}
