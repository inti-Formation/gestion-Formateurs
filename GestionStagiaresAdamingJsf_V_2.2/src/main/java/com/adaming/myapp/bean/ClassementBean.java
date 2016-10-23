package com.adaming.myapp.bean;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.adaming.myapp.entities.Etudiant;
import com.adaming.myapp.entities.Module;
import com.adaming.myapp.entities.Note;
import com.adaming.myapp.etudiant.service.IEtudiantService;
import com.adaming.myapp.module.service.IModuleService;
import com.adaming.myapp.notes.service.INotesService;

@Component("classementBean")
@Scope(value="session")
public class ClassementBean {
    
	@Inject
	private INotesService serviceNotes;
	@Inject
	private IEtudiantService serviceEtudiant;
	@Inject
	private IModuleService serviceModule;
	@Inject
	private UserAuthentificationBean userAuthentification;
	
	private Long idSession;
	private Long idEtudiant;
	private Long idModule;
	private List<Note> notes;
	private Etudiant etudiant;
	private List<Module> modules;
	
	
	/*get all notes by session*/
	public String getAllModuleBySession(){
		etudiant = new Etudiant();
		etudiant = serviceEtudiant.getEtudiant(userAuthentification.getName());
		idSession = etudiant.getSessionEtudiant().getIdSession();
		modules= new ArrayList<Module>();
		modules=serviceModule.getModulesBySession(idSession);
		return "classement?redirect=true";
	}
	
	/*get All Notes By Session And Module*/
    public void getAllNotesBySessionAndModule(){
    	etudiant = new Etudiant();
		etudiant = serviceEtudiant.getEtudiant(userAuthentification.getName());
		idSession = etudiant.getSessionEtudiant().getIdSession();
		notes=new ArrayList<Note>();
		notes=serviceNotes.getNotesBySessionAndModule(idSession, idModule);
    }

	public Long getIdSession() {
		return idSession;
	}


	public void setIdSession(Long idSession) {
		this.idSession = idSession;
	}


	public Long getIdEtudiant() {
		return idEtudiant;
	}


	public void setIdEtudiant(Long idEtudiant) {
		this.idEtudiant = idEtudiant;
	}


	public List<Note> getNotes() {
		return notes;
	}


	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}


	public Etudiant getEtudiant() {
		return etudiant;
	}


	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}


	public Long getIdModule() {
		return idModule;
	}


	public void setIdModule(Long idModule) {
		this.idModule = idModule;
	}


	public List<Module> getModules() {
		return modules;
	}


	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
	
}
