package com.adaming.myapp.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.persistence.TemporalType;

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
	private int numberOfCurrentWarning;
	private int numberOfCurrentTop;
	private List<Evenement> currentRetards;
	private List<Evenement> currentAbsences;
	private List<Evenement> limitationAbsences;
	private List<Evenement> limitationRetards;
	private List<Evenement> currentWarning;
	private List<Evenement> currentTop;
	private List<Evenement> limitationWarning;
	private List<Evenement> limitationTop;
	private int minuteOfEvenement;
	private int hoursOfEvenement;
	private int dureeInMinute;
	private int dureeInHours;
	private Date dateOfEvenement;
	
	

	
	public void init(){
		retardNotFoundException    = new String();
		absenceNotFoundException   = new String();
		entretienNotFoundException = new String();
		/*getSessionsInProgress*/
		 getSessionEnCours();
		/*getCurrentAbsenceAndRetard*/
		getCurentsAbsencesAndRetards();
		/*getWeecklyEvenements*/
		try {
			retards   = serviceEvenement.getEvenementsRetards();
			for(Evenement evenement:retards){
				/*get durrée de retard
				long difference = evenement.getEndDate().getTime() - evenement.getStartDate().getTime();
				dureeInMinute = (int) ((difference / (1000*60)) % 60);
	            dureeInHours   = (int) ((difference / (1000*60*60)) % 24);
	            evenement.setDureeInMinute(dureeInMinute);
	            evenement.setDureeInHours(dureeInHours);*/
			}
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
                /*get durrée de retard*/
				/*long difference = e.getEndDate().getTime() - e.getStartDate().getTime();
				minuteOfEvenement = (int) ((difference / (1000*60)) % 60);
	            hoursOfEvenement   = (int) ((difference / (1000*60*60)) % 24);
	            e.setMinuteOfEvenement(minuteOfEvenement);
	            e.setHoursOfEvenement(hoursOfEvenement);*/
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
	/*get current time of evenement Warning*/
	public void getWarningForToDay(){
		if(currentWarning.size() >0){
	    	for(Evenement e:currentWarning){
                Date today = new Date();
                long differenceInMilis = today.getTime() - e.getCurentDate().getTime();
                minuteOfEvenement = (int) ((differenceInMilis / (1000*60)) % 60);
                hoursOfEvenement   = (int) ((differenceInMilis / (1000*60*60)) % 24);
                e.setMinuteOfEvenement(minuteOfEvenement);
                e.setHoursOfEvenement(hoursOfEvenement);
                
	    	}
	    }
	}
	/*get current time of evenement Top*/
	public void getTopForToDay(){
		if(currentTop.size() >0){
	    	for(Evenement e:currentTop){
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
    /*limiter l'affichage des Top journalière a 4 dans le header*/
	public void limitDisplayTop(){
		limitationTop  = new ArrayList<Evenement>();
		if(currentTop.size() >5){
			limitationTop=currentTop.subList(0,4);
			System.out.println("limitation Top "+limitationTop.size());
		}else{
			limitationTop.addAll(currentTop);
		}
	}
	/*limiter l'affichage des Des Warning journalière a 4 dans le header*/
	public void limitDisplayWarning(){
		limitationWarning  = new ArrayList<Evenement>();
		if(currentWarning.size() >5){
			limitationWarning=currentWarning.subList(0,4);
			System.out.println("limitation Warning "+limitationWarning.size());
		}else{
			limitationWarning.addAll(currentWarning);
		}
	}
    
	/*getcurrentRetardsandAbsences*/
	public void getCurentsAbsencesAndRetards(){
		currentRetards=serviceEvenement.getNumberOfCurrentsRetards();
		numberOfCurrentRetards=currentRetards.size();
		currentAbsences=serviceEvenement.getNumberOfCurrentsAbsence();
		numberOfCurrentAbsences=currentAbsences.size();
		currentWarning=serviceEvenement.getNumberOfCurrentsWarning();
		numberOfCurrentWarning=currentWarning.size();
		currentTop=serviceEvenement.getNumberOfCurrentsTop();
		numberOfCurrentTop=currentTop.size();
		
		/*get current time of evenement Retards,absence,warning,top*/
		getRetardForToDay();
		getWarningForToDay();
		getTopForToDay();
		getAbsenceForToDay();
		/*limiter l'affichage des retards, absences,warning et top, journalière a 4 dans le header*/
		limitDisplayRetards();
		limitDisplayAbsences();
		limitDisplayWarning();
		limitDisplayTop();
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

	public List<Evenement> getCurrentWarning() {
		return currentWarning;
	}

	public void setCurrentWarning(List<Evenement> currentWarning) {
		this.currentWarning = currentWarning;
	}

	public List<Evenement> getCurrentTop() {
		return currentTop;
	}

	public void setCurrentTop(List<Evenement> currentTop) {
		this.currentTop = currentTop;
	}

	public List<Evenement> getLimitationWarning() {
		return limitationWarning;
	}

	public void setLimitationWarning(List<Evenement> limitationWarning) {
		this.limitationWarning = limitationWarning;
	}

	public List<Evenement> getLimitationTop() {
		return limitationTop;
	}

	public void setLimitationTop(List<Evenement> limitationTop) {
		this.limitationTop = limitationTop;
	}

	public int getNumberOfCurrentWarning() {
		return numberOfCurrentWarning;
	}

	public void setNumberOfCurrentWarning(int numberOfCurrentWarning) {
		this.numberOfCurrentWarning = numberOfCurrentWarning;
	}

	public int getNumberOfCurrentTop() {
		return numberOfCurrentTop;
	}

	public void setNumberOfCurrentTop(int numberOfCurrentTop) {
		this.numberOfCurrentTop = numberOfCurrentTop;
	}

	public int getDureeInMinute() {
		return dureeInMinute;
	}

	public void setDureeInMinute(int dureeInMinute) {
		this.dureeInMinute = dureeInMinute;
	}

	public int getDureeInHours() {
		return dureeInHours;
	}

	public void setDureeInHours(int dureeInHours) {
		this.dureeInHours = dureeInHours;
	}

	

	
	
}
