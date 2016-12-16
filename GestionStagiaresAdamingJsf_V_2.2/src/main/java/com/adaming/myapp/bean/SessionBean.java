package com.adaming.myapp.bean;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adaming.myapp.entities.SessionEtudiant;
import com.adaming.myapp.exception.AddSessionException;
import com.adaming.myapp.session.service.ISessionService;
import com.adaming.myapp.user.service.IUserService;


@SuppressWarnings("serial")
@Component("sessionBean")
@ViewScoped
public class SessionBean implements Serializable{
	
	/**
	 * LOGGER log4J
	 * @see org.apache.log4j.Logger
	 * */
    private final Logger LOGGER  = Logger.getLogger("SessionBean");
    
	@Inject
	private ISessionService serviceSession;

	private Long idSession;
	private Long idSpecialite;
	private Date dateDebute;
	private Date dateFin;
	private Long dateDebuteInDays;
	private Long dateFinInDays;
	private List<SessionEtudiant> sessions;
	private List<SessionEtudiant> sessionsInProgress;
	private String lieu;
	private String succes;
	private String addSessionException;
	private SessionEtudiant sessionEtudiant;

    /*@method add session*/
	public void addSession() throws ParseException {
		SessionEtudiant se = new SessionEtudiant(dateDebute,
				dateFin, lieu);
		try {
			serviceSession.addSessionStudent(se, idSpecialite);
			getAllSessions();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Success",
					"La Prochaine Session aura lieu à " + lieu
					+ " à bien été enregistrer avec succes "));
			reset();
		} catch (AddSessionException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Warning",e.getMessage()));
			dateDebute=null;
			dateFin=null;		
		}

	}
	
	/*vider les champs aprés l'insertion de chaque session*/
	public void reset(){
		dateDebute=null;
		dateFin=null;
		lieu="";
		idSpecialite=null;
	}
	
	/*@method get CurrentSession*/
	public void getCurrentSession(Long idSession){
		sessionEtudiant=serviceSession.getSessionEtudiantById(idSession);
	}
	
	/*@method update SessionEtudiant*/
	public String edit(SessionEtudiant sessionEtudiant,Long idSpecialite){
		serviceSession.updateSessionEtudiant(sessionEtudiant,idSpecialite);
	    return "session_update_success?redirect=true";
	}
	
    /*init in load Page*/
	public void getAllSessions() throws ParseException {
		//getSessionEnCours();
		sessions = serviceSession.getAllSessions();
		Date curentDate= new Date();
		for(SessionEtudiant s:sessions){
			if(s.getDateFin().getTime()<=curentDate.getTime()){
				s.setEtatSession("TERMINE");
			}else{
				s.setEtatSession("EN COURS");
			}
		}
	}
	/*@ method pour avoir les jours d'une sessions (progress bar in css)*/
	public void getSessionEnCours(){
		sessionsInProgress=serviceSession.getAllSessionsInProgress();
		for(SessionEtudiant s:sessionsInProgress){
			dateDebuteInDays=s.getDateDebute().getTime()/ (24 * 60 * 60 * 1000);
			dateFinInDays=s.getDateFin().getTime()/ (24 * 60 * 60 * 1000);
			
			/*la date du jour */
			final Date currentDay = new Date();
			final long currentDate= currentDay.getTime()/(24*60*60*1000);
			
			/*nombre de jours de la formation*/
			final long differenceDate = dateFinInDays-dateDebuteInDays;
			final String dayFin=Long.toString(differenceDate);
			s.setDateFinInDays(dayFin);
			LOGGER.debug("difference "+differenceDate);
			
			/*nombre de jours entre le début et le jour courant */
			final long differenceTwo =currentDate-dateDebuteInDays;
			final String differenceTwoStr=Long.toString(differenceTwo);
			s.setDateDebuteInDays(differenceTwoStr);
			LOGGER.debug("la difference entre debut et current day"+differenceTwo);
			
		}
	}
	

	public Long getIdSession() {
		return idSession;
	}

	public void setIdSession(Long idSession) {
		this.idSession = idSession;
	}

	public Date getDateDebute() {
		return dateDebute;
	}

	public void setDateDebute(Date dateDebute) {
		this.dateDebute = dateDebute;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public SessionEtudiant getSessionEtudiant() {
		return sessionEtudiant;
	}

	public void setSessionEtudiant(SessionEtudiant sessionEtudiant) {
		this.sessionEtudiant = sessionEtudiant;
	}

	public Long getIdSpecialite() {
		return idSpecialite;
	}

	public void setIdSpecialite(Long idSpecialite) {
		this.idSpecialite = idSpecialite;
	}

	public String getSucces() {
		return succes;
	}

	public void setSucces(String succes) {
		this.succes = succes;
	}

	public String getAddSessionException() {
		return addSessionException;
	}

	public void setAddSessionException(String addSessionException) {
		this.addSessionException = addSessionException;
	}

	public List<SessionEtudiant> getSessions() {
		return sessions;
	}

	public void setSessions(List<SessionEtudiant> sessions) {
		this.sessions = sessions;
	}

	public List<SessionEtudiant> getSessionsInProgress() {
		return sessionsInProgress;
	}

	public void setSessionsInProgress(List<SessionEtudiant> sessionsInProgress) {
		this.sessionsInProgress = sessionsInProgress;
	}

	public Long getDateDebuteInDays() {
		return dateDebuteInDays;
	}

	public void setDateDebuteInDays(Long dateDebuteInDays) {
		this.dateDebuteInDays = dateDebuteInDays;
	}

	public Long getDateFinInDays() {
		return dateFinInDays;
	}

	public void setDateFinInDays(Long dateFinInDays) {
		this.dateFinInDays = dateFinInDays;
	}

	

}
