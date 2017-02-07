package com.adaming.myapp.session.dao;

import java.util.List;

import com.adaming.myapp.dto.SessionDto;
import com.adaming.myapp.entities.SessionEtudiant;
import com.adaming.myapp.exception.AddSessionException;

public interface ISessionDao {

	 SessionEtudiant addSessionStudent(SessionEtudiant se,Long idSpecialite,Long idSite,Long idSalle) throws AddSessionException;
	
	 SessionEtudiant updateSessionEtudiant(SessionEtudiant se,
				Long idSpecialite,Long idSite,Long idSalle);
	
	 SessionEtudiant getSessionEtudiantById(Long idSessionEtudiant);
	
	 List<SessionEtudiant> getAllSessions();
	
	 List<SessionEtudiant> getAllSessionsInProgress();
	 
     List<Object[]> getSallesDisponible(final Long idSalle);
     
     List<Object[]> getAllSessionsInProgressV2();
     
     List<Object[]> getAllSessionsV2();
     
     SessionEtudiant getSessionByFormateur(final Long idFormateur);
     
     SessionEtudiant getSessionByEtudiant(final Long idEtudiant);
	
	 
}
