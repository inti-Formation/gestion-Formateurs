package com.adaming.myapp.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.joda.time.DateTime;
import org.joda.time.Duration;
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
	private List<Evenement> limitationAbsences;
	private List<Evenement> limitationRetards;
	private int minuteOfEvenement;
	private int hoursOfEvenement;
	private Date dateOfEvenement;
	
	

	
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
	
	/*get current time of evenement Retards*/
	public void getRetardForToDay(){
		if(currentRetards.size() >0){
	    	for(Evenement e:currentRetards){
                Date today = new Date();
                long differenceInMilis = today.getTime() - e.getCurentDate().getTime();
                minuteOfEvenement = (int) ((differenceInMilis / (1000*60)) % 60);
                hoursOfEvenement   = (int) ((differenceInMilis / (1000*60*60)) % 24);
                e.setMinuteOfEvenement(minuteOfEvenement);
                e.setHoursOfEvenement(hoursOfEvenement);
                
	    	}
	    }
	}
	
	/*get current time of evenement absences*/
	public void getAbsenceForToDay(){
		if(currentAbsences.size() >0){
	    	for(Evenement e:currentAbsences){
                Date today = new Date();
                long differenceInMilis = today.getTime() - e.getCurentDate().getTime();
                minuteOfEvenement = (int) ((differenceInMilis / (1000*60)) % 60);
                hoursOfEvenement   = (int) ((differenceInMilis / (1000*60*60)) % 24);
                e.setMinuteOfEvenement(minuteOfEvenement);
                e.setHoursOfEvenement(hoursOfEvenement);
                
	    	}
	    }
	}
	
	/*limiter l'affichage des retards journalière a 4 dans le header*/
	public void limitDisplayRetards(){
		limitationRetards  = new ArrayList<Evenement>();
		if(currentRetards.size() >5){
			limitationRetards=currentRetards.subList(0,4);
			System.out.println("limitation retards "+limitationRetards.size());
		}else{
			limitationRetards.addAll(currentRetards);
		}
	}
	
	/*limiter l'affichage des absences journalière a 4 dans le header*/
    public void limitDisplayAbsences(){
    	limitationAbsences = new ArrayList<Evenement>();
    	if(currentAbsences.size() >5){
			limitationAbsences=currentAbsences.subList(0,4);
			System.out.println("limitation absences "+limitationAbsences.size());
		}else{
			limitationAbsences.addAll(currentAbsences);
		}
	}
    
	/*getcurrentRetardsandAbsences*/
	public void getCurentsAbsencesAndRetards(){
		currentRetards=serviceEvenement.getNumberOfCurrentsRetards();
		numberOfCurrentRetards=currentRetards.size();
		currentAbsences=serviceEvenement.getNumberOfCurrentsAbsence();
		numberOfCurrentAbsences=currentAbsences.size();
		
		/*get current time of evenement Retards*/
		getRetardForToDay();
	    /*get current time of evenement absences*/
		getAbsenceForToDay();
		/*limiter l'affichage des retards et absences journalière a 4 dans le header*/
		limitDisplayRetards();
		limitDisplayAbsences();
	}
	
	/*@ method pour avoir les jours d'une sessions (progress bar in css)*/
	public void getSessionEnCours(){
		sessionsInProgress=serviceSession.getAllSessionsInProgress();
		for(SessionEtudiant s:sessionsInProgress){
			dateDebuteInDays=s.getDateDebute().getTime()/ (24 * 60 * 60 * 1000);
			dateFinInDays=s.getDateFin().getTime()/ (24 * 60 * 60 * 1000);

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

	public List<Evenement> getLimitationAbsences() {
		return limitationAbsences;
	}

	public void setLimitationAbsences(List<Evenement> limitationAbsences) {
		this.limitationAbsences = limitationAbsences;
	}

	public List<Evenement> getLimitationRetards() {
		return limitationRetards;
	}

	public void setLimitationRetards(List<Evenement> limitationRetards) {
		this.limitationRetards = limitationRetards;
	}

	

	public Date getDateOfEvenement() {
		return dateOfEvenement;
	}

	public void setDateOfEvenement(Date dateOfEvenement) {
		this.dateOfEvenement = dateOfEvenement;
	}

	public int getMinuteOfEvenement() {
		return minuteOfEvenement;
	}

	public void setMinuteOfEvenement(int minuteOfEvenement) {
		this.minuteOfEvenement = minuteOfEvenement;
	}

	public int getHoursOfEvenement() {
		return hoursOfEvenement;
	}

	public void setHoursOfEvenement(int hoursOfEvenement) {
		this.hoursOfEvenement = hoursOfEvenement;
	}

	

	
	
}
