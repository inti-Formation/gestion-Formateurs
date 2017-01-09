package com.adaming.myapp.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.adaming.myapp.entities.Formateur;
import com.adaming.myapp.entities.SessionEtudiant;
import com.adaming.myapp.formateur.service.IFormateurService;
import com.adaming.myapp.session.service.ISessionService;

@Component("affectationFormateurBean")
@ViewScoped
public class AffectationFormateurBean {
    
	/**
	 * LOGGER LOG4j 
	 * @see org.apache.log4j.Logger
	 */
    private final Logger LOGGER  = Logger.getLogger("AffectationFormateurBean");
	
	@Inject
	private IFormateurService serviceFormateur;
	@Inject
	private ISessionService serviceSession;
	
	private Long idSession;
	private Long idFormateur;
	private Formateur formateur;
	private List<SessionEtudiant> sessionsInProgress;
	private List<Formateur> formateurs;
	
	/*@method init chargement de session inprogress and allFormateurs*/
	public void init(){
		getAllformateurs();
		getAllSessionsInProgress();
		LOGGER.info("formateurs : "+formateurs);
		LOGGER.info("Session en Cours : "+sessionsInProgress);
	}
	/* method affectation formateur*/
	public String affectationFormateur(){
		serviceFormateur.addFormateurToSession(idSession, idFormateur);
		return "affectationFormateurSuccess?redirect=true";
	}
	/*method get formateur by id*/
	public String getFormateurById(Long idFormateur){
		formateur = FactoryBean.getFormateurFactory().create("Formateur");
		formateur = serviceFormateur.getFormateurById(idFormateur);
		LOGGER.info("Formateur : "+formateur);
		return "informationFormateur?redirect=true";
	}
	/*method redirect*/
	public String redirect(){
		idFormateur=null;
		idSession=null;
		return "affectationFormateur?faces-redirect=true";
	}
	
	/*methode get all formateurs*/
	public String getAllFormateurs(){
		sessionsInProgress=null;
		getAllformateurs();
		final Date CURRENT_DATE= new Date();
		if(formateurs.size()>0){
			sessionsInProgress=new ArrayList<SessionEtudiant>();
			for(Formateur f:formateurs){
				sessionsInProgress=f.getSessionsEtudiant();
				for(SessionEtudiant s:sessionsInProgress){
					if(s.getDateFin().getTime()<=CURRENT_DATE.getTime()){
						s.setEtatSession("TERMINE");
						LOGGER.info("Etat session : "+s.getEtatSession());
					}else{
						s.setEtatSession("EN COURS");
						LOGGER.info("Etat session : "+s.getEtatSession());
					}
				}
			}
		}
		return "liste_formateurs?redirect=true";
	}
	
	/*@method get all formateurs*/
	public void getAllformateurs(){
		formateurs=serviceFormateur.getAllFormateurs();
	}
	
	/*@method get all Sessions In Progress*/
	public void getAllSessionsInProgress(){
		sessionsInProgress=serviceSession.getAllSessionsInProgress();
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
	public Formateur getFormateur() {
		return formateur;
	}
	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}
	
	
}
