package com.adaming.myapp.bean;

import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.adaming.myapp.entities.Formateur;
import com.adaming.myapp.entities.SessionEtudiant;
import com.adaming.myapp.formateur.service.IFormateurService;
import com.adaming.myapp.session.service.ISessionService;

@Component("affectationFormateurBean")
@ViewScoped
public class AffectationFormateurBean {

	@Inject
	private IFormateurService serviceFormateur;
	@Inject
	private ISessionService serviceSession;
	private Long idSession;
	private Long idFormateur;
	private List<SessionEtudiant> sessionsInProgress;
	private List<Formateur> formateurs;
	
	/*@method init chargement de session inprogress and allFormateurs*/
	public void init(){
		formateurs=serviceFormateur.getAllFormateurs();
		sessionsInProgress=serviceSession.getAllSessionsInProgress();
	}
	/* method affectation formateur*/
	public String affectationFormateur(){
		serviceFormateur.addFormateurToSession(idSession, idFormateur);
		return "affectationFormateurSuccess?redirect=true";
	}
	/*method redirect*/
	public String redirect(){
		idFormateur=null;
		idSession=null;
		return "affectationFormateur?redirect=true";
	}

	public Long getIdSession() {
		return idSession;
	}

	public void setIdSession(Long idSession) {
		this.idSession = idSession;
	}

	public Long getIdFormateur() {
		return idFormateur;
	}

	public void setIdFormateur(Long idFormateur) {
		this.idFormateur = idFormateur;
	}

	public List<SessionEtudiant> getSessionsInProgress() {
		return sessionsInProgress;
	}

	public void setSessionsInProgress(List<SessionEtudiant> sessionsInProgress) {
		this.sessionsInProgress = sessionsInProgress;
	}

	public List<Formateur> getFormateurs() {
		return formateurs;
	}

	public void setFormateurs(List<Formateur> formateurs) {
		this.formateurs = formateurs;
	}
	
	
}
