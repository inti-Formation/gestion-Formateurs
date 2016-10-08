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
import com.adaming.myapp.exception.EvenementNotFoundException;
import com.adaming.myapp.session.service.ISessionService;

@Component("dashboardBean")
@Scope("session")
public class DashboardBean implements Serializable{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
	private String retardNotFoundException;
	private String absenceNotFoundException;
	private String entretienNotFoundException;
	private int numberOfCurrentAbsences;
	private int numberOfCurrentRetards;
	private List<Evenement> currentRetards;
	private List<Evenement> currentAbsences;

	
	public void init(){
		retardNotFoundException    = new String();
		absenceNotFoundException   = new String();
		entretienNotFoundException = new String();
		/*getSessionsInProgress*/
		 getSessionEnCours();
		/*getCurrentAbsenceAndRetard*/
		getCurentsAbsencesAndRetards();
		/*getWeecklyEvenemenets*/
		try {
			retards   = serviceEvenement.getEvenementsRetards();
			setRetardNotFoundException("");
		} catch (EvenementNotFoundException e) {
			setRetardNotFoundException(e.getMessage());
			setRetards(null);
		}
		try {
			absences  = serviceEvenement.getEvenementsAbsences();
			setAbsenceNotFoundException("");
		} catch (EvenementNotFoundException e) {
			setAbsenceNotFoundException(e.getMessage());
			setAbsences(null);
		}
		try {
			entretiens= serviceEvenement.getEvenementsEntretien();
			setEntretienNotFoundException("");
		} catch (EvenementNotFoundException e) {
			setEntretienNotFoundException(e.getMessage());
			setEntretiens(null);
		}
		
	}
	
	/*getcurrentRetardsandAbsences*/
	public void getCurentsAbsencesAndRetards(){
		currentRetards=serviceEvenement.getNumberOfCurrentsRetards();
		numberOfCurrentRetards=currentRetards.size();
		System.out.println("nombre retards"+numberOfCurrentRetards);
		currentAbsences=serviceEvenement.getNumberOfCurrentsAbsence();
		numberOfCurrentAbsences=currentAbsences.size();
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

	public String getRetardNotFoundException() {
		return retardNotFoundException;
	}

	public void setRetardNotFoundException(String retardNotFoundException) {
		this.retardNotFoundException = retardNotFoundException;
	}

	public String getAbsenceNotFoundException() {
		return absenceNotFoundException;
	}

	public void setAbsenceNotFoundException(String absenceNotFoundException) {
		this.absenceNotFoundException = absenceNotFoundException;
	}

	public String getEntretienNotFoundException() {
		return entretienNotFoundException;
	}

	public void setEntretienNotFoundException(String entretienNotFoundException) {
		this.entretienNotFoundException = entretienNotFoundException;
	}

	public int getNumberOfCurrentAbsences() {
		return numberOfCurrentAbsences;
	}

	public void setNumberOfCurrentAbsences(int numberOfCurrentAbsences) {
		this.numberOfCurrentAbsences = numberOfCurrentAbsences;
	}

	public int getNumberOfCurrentRetards() {
		return numberOfCurrentRetards;
	}

	public void setNumberOfCurrentRetards(int numberOfCurrentRetards) {
		this.numberOfCurrentRetards = numberOfCurrentRetards;
	}

	public List<Evenement> getCurrentRetards() {
		return currentRetards;
	}

	public void setCurrentRetards(List<Evenement> currentRetards) {
		this.currentRetards = currentRetards;
	}

	public List<Evenement> getCurrentAbsences() {
		return currentAbsences;
	}

	public void setCurrentAbsences(List<Evenement> currentAbsences) {
		this.currentAbsences = currentAbsences;
	}
	
	
}
