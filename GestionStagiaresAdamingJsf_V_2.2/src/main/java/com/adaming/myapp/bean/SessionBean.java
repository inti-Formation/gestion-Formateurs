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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adaming.myapp.entities.SessionEtudiant;
import com.adaming.myapp.exception.AddSessionException;
import com.adaming.myapp.session.service.ISessionService;
import com.adaming.myapp.user.service.IUserService;


@Component("sessionBean")
@ViewScoped
public class SessionBean implements Serializable{

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
			dateDebute=null;
			dateFin=null;
			lieu="";
			idSpecialite=null;
		} catch (AddSessionException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Warning",e.getMessage()));
			dateDebute=null;
			dateFin=null;		
		}

	}
	
	/*@method get CurrentSession*/
	public void getCurrentSession(Long idSession){
		sessionEtudiant=serviceSession.getSessionEtudiantById(idSession);
	}
	
	/*@method update SessionEtudiant*/
	public String edit(SessionEtudiant sessionEtudiant,Long idSpecialite){
		serviceSession.updateSessionEtudian(sessionEtudiant,idSpecialite);
	    return "session_update_success?redirect=true";
	}
	
    /*init in load Page*/
	public void getAllSessions() throws ParseException {
		getSessionEnCours();
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
			/*String dayDebut=Long.toString(dateDebuteInDays);
			String dayFin=Long.toString(dateFinInDays);
			s.setDateDebuteInDays(dayDebut);
			s.setDateFinInDays(dayFin);
			System.out.println("date debut : "+dayDebut);
			System.out.println("date fIN :"+dayFin);*/
			
			/*la date du jour */
			Date currentDay = new Date();
			long currentDate= currentDay.getTime()/(24*60*60*1000);
			
			/*nombre de jours de la formation*/
			long differenceDate = dateFinInDays-dateDebuteInDays;
			String dayFin=Long.toString(differenceDate);
			s.setDateFinInDays(dayFin);
			System.out.println("difference "+differenceDate);
			
			/*nombre de jours entre le début et le jour courant */
			long differenceTwo =currentDate-dateDebuteInDays;
			String differenceTwoStr=Long.toString(differenceTwo);
			s.setDateDebuteInDays(differenceTwoStr);
			
			System.out.println("la difference entre debut et current day"+differenceTwo);
			
			/**/
			//System.out.println("date de jour "+curentDayString);
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
