package com.adaming.myapp.bean;


import java.util.Date;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.adaming.myapp.entities.Formateur;
import com.adaming.myapp.exception.VerificationInDataBaseException;
import com.adaming.myapp.formateur.service.IFormateurService;
import com.adaming.myapp.session.service.ISessionService;
import com.adaming.myapp.tools.LoggerConfig;
import com.adaming.myapp.tools.Utilitaire;

/**
 * la calss AffectationFormateurBean, c'est le bean qui permet 
 * de répondre aux besoins métiers,
 * affectation de chaque formateur à une session en cours 
 * 
 * 
 * 
 * @author adel
 * @date 10/10/2016
 * @version 1.0.0
 */




@Component("affectationFormateurBean")
@ViewScoped
public class AffectationFormateurBean {
    
	/**
	 * LOGGER LOG4j 
	 * @see org.apache.log4j.Logger
	 */
    
	
	@Inject
	private IFormateurService serviceFormateur;
	@Inject
	private ISessionService serviceSession;
	
	private Long idSession;
	private Long idFormateur;
	private Formateur formateur;
	private List<Object[]> sessionsInProgress;
	private List<Formateur> formateurs;
	private long currentTime;
	
	
	// Query Operations
	
	/** 
	* la methode init permet de charger les sessions en cours
	* et aussi la liste des formateurs 
	* 
    */
	public void init(){
		getAllformateurs();
		getAllSessionsInProgress();
		LoggerConfig.logInfo("formateurs : "+formateurs);
		LoggerConfig.logInfo("Session en Cours : "+sessionsInProgress);
	}
	
	

	/** 
	* la methode affectationFormateur permet d'affecter un formateur à 
	* une session encours
	* 
	* @return la page affectationFormateurSuccess.xhtml 
	* @see com.adaming.myapp.tools.Utilitaire.displayMessageInfo
	* @see com.adaming.myapp.tools.Utilitaire.displayMessageWarning
	* 
    */
	public String affectationFormateur(){
		try {
			serviceFormateur.addFormateurToSession(idSession, idFormateur);
		} catch (VerificationInDataBaseException e) {
			Utilitaire.displayMessageWarning(e.getMessage());
			return null;
		}
		return "affectationFormateurSuccess?redirect=true";
	}
	
	/** 
	* la methode getFormateurById permet de récupérer un formateur 
	* par son identifiant
	* 
	* @parm idFormateur l'identifiant du formateur en cours
	* @return la page informationFormateur.xhtml 
	* @see com.adaming.myapp.bean.FactoryBean.FORMATEUR_FACTORY
	* @see com.adaming.myapp.tools.Utilitaire.displayMessageWarning
	* @throws @see com.adaming.myapp.exception.VerificationInDataBaseException
    */
	public String getFormateurById(Long idFormateur){
		
		formateur = FactoryBean.getFormateurFactory().create("Formateur");
		try {
			formateur = serviceFormateur.getFormateurById(idFormateur);
		} catch (VerificationInDataBaseException e) {
			Utilitaire.displayMessageWarning(e.getMessage());
			return null;
		}
		LoggerConfig.logInfo("Formateur : "+formateur);
		return "informationFormateur?redirect=true";
	}
	
	
	
	/** 
	* la methode initAndRedirect permet d'initialisé et faire la redirection
	* 
	* @return la page affectationFormateur.xhtml 
    */
	
	public String initAndRedirect(){
		init();
		reset();
		return "affectationFormateur?faces-redirect=true";
	}
	
	
	/** 
	* la methode reset permet de vider les champs
	* 
    */
	public void reset(){
		idFormateur=null;
		idSession=null;
	}
	
	
	
	/** 
	* la methode redirect permet de vider les champs et faire la redirection
	* 
	* @return la page affectationFormateur.xhtml 
    */
	
	public String redirect(){
		reset();
		return "affectationFormateur?faces-redirect=true";
	}
	
	
	
	/** 
	* la methode getAllFormateurs permet de récupérer la liste 
	* des formateurs et faire la redirection
	* 
	* @return la page liste_formateurs.xhtml 
    */
	public String getAllFormateurs(){
		sessionsInProgress=null;
		getAllformateurs();
		return "liste_formateurs?redirect=true";
	}
	
	/** 
	* la methode getAllFormateurs permet de récupérer la liste 
	* des formateurs
	* 
    */
	public void getAllformateurs(){
		formateurs=serviceFormateur.getAllFormateurs();
		final Date  CURRENT_DATE = new Date();
		currentTime = CURRENT_DATE.getTime();
	}
	
	
	/** 
	* la methode getAllSessionsInProgress permet de récupérer la liste 
	* des sessions en cours 
	* 
    */
	public void getAllSessionsInProgress(){
		sessionsInProgress=serviceSession.getAllSessionsInProgressV2();
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

	public List<Object[]> getSessionsInProgress() {
		return sessionsInProgress;
	}
	public void setSessionsInProgress(List<Object[]> sessionsInProgress) {
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
	public long getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(long currentTime) {
		this.currentTime = currentTime;
	}
	
	
}
