package com.adaming.myapp.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adaming.myapp.entities.Etudiant;
import com.adaming.myapp.entities.Module;
import com.adaming.myapp.entities.Note;
import com.adaming.myapp.entities.SessionEtudiant;
import com.adaming.myapp.etudiant.service.IEtudiantService;
import com.adaming.myapp.exception.VerificationInDataBaseException;
import com.adaming.myapp.module.service.IModuleService;
import com.adaming.myapp.notes.service.INotesService;
import com.adaming.myapp.session.service.ISessionService;
import com.adaming.myapp.tools.LoggerConfig;
import com.adaming.myapp.tools.Utilitaire;
/**
 * 
 * @author adel
 * @date 10/10/2016
 * @version 1.0.0
 * */
@SuppressWarnings("serial")
@Component("noteBean")
@ViewScoped
public class NoteBean implements Serializable{
    
	/**
	 * LOGGER LOG4j 
	 * @see org.apache.log4j.Logger
	 */
   
    
	
	@Inject
	private ISessionService serviceSession;
	@Inject
	private IEtudiantService serviceEtudiant;
	@Inject
	private INotesService serviceNotes;
	@Inject
	private IModuleService serviceModule;

	private Long idSession;
	private Long idModule;
	private Long idEtudiant;
	private List<Object[]> sessions;
	private List<Object[]> etudiants;
	private List<Object[]> modules;
	private List<Object[]> notes;
	
	
	
	/*@method load page notes*/
	public String init(){
		sessions=serviceSession.getAllSessionsV2();
		LoggerConfig.logInfo("Session : "+sessions);
		return "notes?faces-redirect=true";
	}
	
	/* @method get All Students By Session */
	public void getAllStudentsBySession() {
		try {
			etudiants = serviceEtudiant.getEtudiantBySession(idSession);
		} catch (VerificationInDataBaseException e) {
			Utilitaire.displayMessageWarning(e.getMessage());
		}
		LoggerConfig.logInfo("Etudiants :"+etudiants);
	}
	
	/* @@method get All Modules By Session */
	public void getAllModulesBySession() {
		modules= new ArrayList<Object[]>();
		modules= serviceModule.getModulesBySessionV2(idSession);
		LoggerConfig.logInfo("Modules By Sessions :"+modules);
	}
	/*@ methode get all Notes by sessions and Module*/
	public void getAllNotesByModule(){
	  notes=serviceNotes.getNotesBySessionAndModule(idSession,idModule);
	  LoggerConfig.logInfo("Notes :"+notes);
	}
	
	public Long getIdSession() {
		return idSession;
	}
	public void setIdSession(Long idSession) {
		this.idSession = idSession;
	}
	public Long getIdModule() {
		return idModule;
	}
	public void setIdModule(Long idModule) {
		this.idModule = idModule;
	}
	public Long getIdEtudiant() {
		return idEtudiant;
	}
	public void setIdEtudiant(Long idEtudiant) {
		this.idEtudiant = idEtudiant;
	}
    
	

	public List<Object[]> getNotes() {
		return notes;
	}

	public void setNotes(List<Object[]> notes) {
		this.notes = notes;
	}

	public List<Object[]> getModules() {
		return modules;
	}

	public void setModules(List<Object[]> modules) {
		this.modules = modules;
	}

	public List<Object[]> getSessions() {
		return sessions;
	}

	public void setSessions(List<Object[]> sessions) {
		this.sessions = sessions;
	}

	public List<Object[]> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(List<Object[]> etudiants) {
		this.etudiants = etudiants;
	}
	
	
	
}
