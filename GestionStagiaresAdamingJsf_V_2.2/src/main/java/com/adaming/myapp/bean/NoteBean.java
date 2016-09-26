package com.adaming.myapp.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adaming.myapp.entities.Etudiant;
import com.adaming.myapp.entities.Examen;
import com.adaming.myapp.entities.Module;
import com.adaming.myapp.entities.Note;
import com.adaming.myapp.entities.SessionEtudiant;
import com.adaming.myapp.etudiant.service.IEtudiantService;
import com.adaming.myapp.examen.service.IExamenService;
import com.adaming.myapp.exception.AddNoteException;
import com.adaming.myapp.module.service.IModuleService;
import com.adaming.myapp.notes.service.INotesService;
import com.adaming.myapp.session.service.ISessionService;

@Component("noteBean")
@ViewScoped
public class NoteBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7472770450954788623L;
	
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
	private List<SessionEtudiant> sessions;
	private List<Etudiant> etudiants;
	private List<Module> modules;
	private Object noteEtudiant;
	Set<Module> modulesNotDuplicate =null;
	private List<Note> notes;
	
	
	
	/*@method load page notes*/
	public void init(){
		sessions=serviceSession.getAllSessions();
	}
	
	/* @method get All Students By Session */
	public void getAllStudentsBySession() {
		etudiants = serviceEtudiant.getEtudiantBySession(idSession);
	}
	
	/* @@method get All Modules By Session */
	public void getAllModulesBySession() {
		modules= new ArrayList<Module>();
		modules= serviceModule.getModulesBySession(idSession);
	}
	/*@ methode get all Notes by sessions and Module*/
	public void getAllNotesByModule(){
	  notes=serviceNotes.getNotesBySessionAndModule(idSession,idModule);
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

	public List<SessionEtudiant> getSessions() {
		return sessions;
	}

	public void setSessions(List<SessionEtudiant> sessions) {
		this.sessions = sessions;
	}

	public List<Etudiant> getEtudiants() {
		return etudiants;
	}
	public void setEtudiants(List<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}
	

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public Set<Module> getModulesNotDuplicate() {
		return modulesNotDuplicate;
	}

	public void setModulesNotDuplicate(Set<Module> modulesNotDuplicate) {
		this.modulesNotDuplicate = modulesNotDuplicate;
	}

	public Object getNoteEtudiant() {
		return noteEtudiant;
	}

	public void setNoteEtudiant(Object noteEtudiant) {
		this.noteEtudiant = noteEtudiant;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
	
	
	
}
