package com.adaming.myapp.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.adaming.myapp.entities.Evenement;
import com.adaming.myapp.entities.SessionEtudiant;
import com.adaming.myapp.evenement.service.IEvenementService;
import com.adaming.myapp.session.service.ISessionService;

@Component("dashboardBean")
@ViewScoped
public class DashboardBean implements Serializable{
    
	@Inject
	private IEvenementService serviceEvenement;
	@Inject
	private ISessionService serviceSession;
	
	private List<Evenement> retards;
	private List<Evenement> absences;
	private List<Evenement> entretiens;
	private List<SessionEtudiant> sessionsInProgress;
	private Date dateDebute;
	private Date dateFin;
	private Long dateDebuteInDays;
	private Long dateFinInDays;
	
	public void init(){
		getSessionEnCours();
		retards   = serviceEvenement.getEvenementsRetards();
		absences  = serviceEvenement.getEvenementsAbsences();
		entretiens= serviceEvenement.getEvenementsEntretien();
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

	public List<Evenement> getRetards() {
		return retards;
	}

	public void setRetards(List<Evenement> retards) {
		this.retards = retards;
	}

	public List<Evenement> getAbsences() {
		return absences;
	}

	public void setAbsences(List<Evenement> absences) {
		this.absences = absences;
	}

	public List<Evenement> getEntretiens() {
		return entretiens;
	}

	public void setEntretiens(List<Evenement> entretiens) {
		this.entretiens = entretiens;
	}

	public List<SessionEtudiant> getSessionsInProgress() {
		return sessionsInProgress;
	}

	public void setSessionsInProgress(List<SessionEtudiant> sessionsInProgress) {
		this.sessionsInProgress = sessionsInProgress;
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
