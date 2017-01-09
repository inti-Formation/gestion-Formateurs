package com.adaming.myapp.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.adaming.myapp.entities.Etudiant;
import com.adaming.myapp.entities.Evenement;
import com.adaming.myapp.entities.Module;
import com.adaming.myapp.entities.Note;
import com.adaming.myapp.entities.SessionEtudiant;
import com.adaming.myapp.etudiant.service.IEtudiantService;
import com.adaming.myapp.evenement.service.IEvenementService;
import com.adaming.myapp.exception.EvenementNotFoundException;
import com.adaming.myapp.module.service.IModuleService;
import com.adaming.myapp.notes.service.INotesService;
import com.adaming.myapp.session.service.ISessionService;

@SuppressWarnings("serial")
@Component("dashboardBean")
@Scope("session")
public class DashboardBean implements Serializable {
    
	/**
	 * LOGGER LOG4j 
	 * @see org.apache.log4j.Logger
	 */
    private final Logger LOGGER  = Logger.getLogger("DashboardBean");
    
	@Inject
	private IEvenementService serviceEvenement;
	@Inject
	private ISessionService serviceSession;
	@Inject
	private IModuleService serviceModule;
	@Inject
	private INotesService serviceNote;
	@Inject
    private IEtudiantService serviceEtudiant;
	
	private Etudiant etudiant;
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
	private List<Module> modules;
	private List<Note> notes;
	private String etatModule;
	/**/
	private String monthOfEvenement;
	private String dayOfEvenement;
	private int minuteOfEvenement;
	private int hoursOfEvenement;
	private int dureeInMinute;
	private int dureeInHours;
	private Date dateOfEvenement;
	private String typeEvenement;
	private String[] typesEvenement = { "Retard", "Absence", "Entretien","WarningEtudiant","TopEtudiant"};
	private Long idSession;
	private List<Evenement> evenementsSession;
   

	public void init() {
		retardNotFoundException = new String();
		absenceNotFoundException = new String();
		entretienNotFoundException = new String();
		/** getSessionsInProgress */
		getSessionEnCours();
		/** getCurrentAbsenceAndRetard */
		getCurentsAbsencesAndRetards();
		/** getWeecklyEvenements */
		try {
			retards = serviceEvenement.getEvenementsRetards();
			for (Evenement evenement : retards) {
				/* get durrée de retard */
				final long DIFFERENCE = evenement.getEndDate().getTime()
						- evenement.getStartDate().getTime();
				dureeInMinute = (int) ((DIFFERENCE / (1000 * 60)) % 60);
				dureeInHours = (int) ((DIFFERENCE / (1000 * 60 * 60)) % 24);
				LOGGER.info("duree Minute" + dureeInMinute);
				LOGGER.info("duree hours" + dureeInHours);
				evenement.setDureeInMinute(dureeInMinute);
				evenement.setDureeInHours(dureeInHours);
			}
			setRetardNotFoundException("");
		} catch (EvenementNotFoundException e) {
			setRetardNotFoundException(e.getMessage());
			setRetards(null);
		}
		try {
			absences = serviceEvenement.getEvenementsAbsences();
			setAbsenceNotFoundException("");
		} catch (EvenementNotFoundException e) {
			setAbsenceNotFoundException(e.getMessage());
			setAbsences(null);
		}
		try {
			entretiens = serviceEvenement.getEvenementsEntretien();
			setEntretienNotFoundException("");
		} catch (EvenementNotFoundException e) {
			setEntretienNotFoundException(e.getMessage());
			setEntretiens(null);
		}

	}
	
	/**@methode remplir la session en cours */
	public void sessionEnCours(){
		sessionsInProgress = serviceSession.getAllSessionsInProgress();
	}
	
	/**get etudiant by id*/
	public String getStudentById(Long idStudent){
		etudiant= FactoryBean.getEtudiantFactory().create("Etudiant");
		etudiant=serviceEtudiant.getStudentById(idStudent);
		return "informtaionEtudiant?redirect=true";
	}

	/* get current time of evenement Retards */
	public void getRetardForToDay() {
		if (currentRetards.size() > 0) {
			final Date CURRENT_DATE = new Date();
			Calendar calendar = Calendar.getInstance();
			for (Evenement e : currentRetards) {
				/*date de l'evenement en mois et jours*/
				calendar.setTime(e.getCurentDate());
				LOGGER.info("calendar "+calendar);
				monthOfEvenement= calendar.getDisplayName(calendar.MONTH,Calendar.LONG,Locale.FRANCE);
				final int dayOfMonth  = calendar.get(Calendar.DAY_OF_MONTH);
				dayOfEvenement  = String.valueOf(dayOfMonth); 
				e.setDayOfEvenement(dayOfEvenement);
				e.setMonthOfEvenement(monthOfEvenement);
				LOGGER.info("day "+e.getDayOfEvenement());
				LOGGER.info("month  "+e.getMonthOfEvenement());
				/* get duree d'envois de l'evenement */
				
				getTimeForSending(CURRENT_DATE, e);
				/* get durrée de retard */
				final long DIFFERENCE = e.getEndDate().getTime()
						- e.getStartDate().getTime();
				dureeInMinute = (int) ((DIFFERENCE / (1000 * 60)) % 60);
				dureeInHours = (int) ((DIFFERENCE / (1000 * 60 * 60)) % 24);
				LOGGER.debug("duree Minute" + dureeInMinute);
				LOGGER.debug("duree hours" + dureeInHours);
				e.setDureeInMinute(dureeInMinute);
				e.setDureeInHours(dureeInHours);
			}
		}
	}

	/**
	 * @param CURRENT_DATE new Date
	 * @param e evenement
	 * @refactoring
	 * @method permet de récupérer la date d'envois d'un èvenement(jour,Month,minute,heure,seconde)
	 */
	private void getTimeForSending(final Date CURRENT_DATE, Evenement e) {
		final long DIFFERENCE_IN_MILIS = CURRENT_DATE.getTime()
				- e.getCurentDate().getTime();
		minuteOfEvenement = (int) ((DIFFERENCE_IN_MILIS / (1000 * 60)) % 60);
		hoursOfEvenement = (int) ((DIFFERENCE_IN_MILIS / (1000 * 60 * 60)) % 24);
		e.setMinuteOfEvenement(minuteOfEvenement);
		e.setHoursOfEvenement(hoursOfEvenement);
	}

	/* get current time of evenement absences */
	private void getAbsenceForToDay() {
		if (currentAbsences.size() > 0) {
			final Date CURRENT_DATE = new Date();
			for (Evenement e : currentAbsences) {
				getTimeForSending(CURRENT_DATE, e);
			}
		}
	}

	/* get current time of evenement Warning */
	private void getWarningForToDay() {
		final Date CURRENT_DATE = new Date();
		if (currentWarning.size() > 0) {
			for (Evenement e : currentWarning) {
				getTimeForSending(CURRENT_DATE, e);
			}
		}
	}

	/* get current time of evenement Top */
	private void getTopForToDay() {
		if (currentTop.size() > 0) {
			final Date CURRENT_DATE = new Date();
			for (Evenement e : currentTop) {
				getTimeForSending(CURRENT_DATE, e);
			}
		}
	}

	/* limiter l'affichage des retards journalière a 4 dans le header */
	private void limitDisplayRetards() {
		limitationRetards = new ArrayList<Evenement>();
		if (currentRetards.size() > 5) {
			limitationRetards = currentRetards.subList(0, 4);
			LOGGER.debug("limitation retards " + limitationRetards.size());
		} else {
			limitationRetards.addAll(currentRetards);
		}
	}

	/* limiter l'affichage des absences journalière a 4 dans le header */
	private void limitDisplayAbsences() {
		limitationAbsences = new ArrayList<Evenement>();
		if (currentAbsences.size() > 5) {
			limitationAbsences = currentAbsences.subList(0, 4);
			LOGGER.debug("limitation absences "+ limitationAbsences.size());
		} else {
			limitationAbsences.addAll(currentAbsences);
		}
	}

	/* limiter l'affichage des Top journalière a 4 dans le header */
	private void limitDisplayTop() {
		limitationTop = new ArrayList<Evenement>();
		if (currentTop.size() > 5) {
			limitationTop = currentTop.subList(0, 4);
			LOGGER.debug("limitation Top " + limitationTop.size());
		} else {
			limitationTop.addAll(currentTop);
		}
	}

	/* limiter l'affichage des Des Warning journalière a 4 dans le header */
	private void limitDisplayWarning() {
		limitationWarning = new ArrayList<Evenement>();
		if (currentWarning.size() > 5) {
			limitationWarning = currentWarning.subList(0, 4);
			LOGGER.debug("limitation Warning " + limitationWarning.size());
		} else {
			limitationWarning.addAll(currentWarning);
		}
	}

	/* getcurrentRetardsandAbsences */
	public void getCurentsAbsencesAndRetards() {
		currentRetards = serviceEvenement.getNumberOfCurrentsRetards();
		numberOfCurrentRetards = currentRetards.size();
		currentAbsences = serviceEvenement.getNumberOfCurrentsAbsence();
		numberOfCurrentAbsences = currentAbsences.size();
		currentWarning = serviceEvenement.getNumberOfCurrentsWarning();
		numberOfCurrentWarning = currentWarning.size();
		currentTop = serviceEvenement.getNumberOfCurrentsTop();
		numberOfCurrentTop = currentTop.size();

		/* get current time of evenement Retards,absence,warning,top */
		getRetardForToDay();
		getWarningForToDay();
		getTopForToDay();
		getAbsenceForToDay();
		/*
		 * limiter l'affichage des retards, absences,warning et top, journalière
		 * a 4 dans le header
		 */
		limitDisplayRetards();
		limitDisplayAbsences();
		limitDisplayWarning();
		limitDisplayTop();
	}

	/* @ method pour avoir les jours d'une sessions (progress bar in css) */
	public void getSessionEnCours() {
		/*remplire la session en cours */
		sessionEnCours();
		Date currentDay = new Date();
		/*parcourir chaque session en cours */
		for (SessionEtudiant s : sessionsInProgress) {
			dateDebuteInDays = s.getDateDebute().getTime()
					/ (24 * 60 * 60 * 1000);
			dateFinInDays = s.getDateFin().getTime() / (24 * 60 * 60 * 1000);

			/* la date du jour */
			final long currentDate = currentDay.getTime() / (24 * 60 * 60 * 1000);

			/* nombre de jours de la formation */
			final long differenceDate = dateFinInDays - dateDebuteInDays;
			String dayFin = Long.toString(differenceDate);
			s.setDateFinInDays(dayFin);
			

			/* nombre de jours entre le début et le jour courant */
			final long differenceTwo = currentDate - dateDebuteInDays;
			String differenceTwoStr = Long.toString(differenceTwo);
			s.setDateDebuteInDays(differenceTwoStr);
		}
	}

	/* Methode pour obtenir les Evenements d'une session */
	public void getEvenementEnCours() {
        /*get modules By session*/
		getModulesBySession();
		/*evenement by session*/
		evenementsSession = serviceEvenement
				.getAllEvenementsBySession(idSession);

		for (Evenement e : evenementsSession) {
			typeEvenement = e.getClass().getSimpleName();
			e.setTypeEvenement(typeEvenement);
		}

	}
	/*get modules by session */
	public void getModulesBySession(){
		modules=serviceModule.getModulesBySession(idSession);
		for(Module m:modules){
			notes=serviceNote.getNotesBySessionAndModule(idSession, m.getIdModule());
			for(Note n:notes){
				if(n.getSessionEtudiant().getIdSession() == idSession
				   && n.getModule().getIdModule() == m.getIdModule()
				   && n.getScore() != null){
				    m.setEtatModule("Validé");
				    LOGGER.debug("validé"+etatModule);
				}
			}
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

	public String getTypeEvenement() {
		return typeEvenement;
	}

	public void setTypeEvenement(String typeEvenement) {
		this.typeEvenement = typeEvenement;
	}

	public String[] getTypesEvenement() {
		return typesEvenement;
	}

	public void setTypesEvenement(String[] typesEvenement) {
		this.typesEvenement = typesEvenement;
	}

	public Long getIdSession() {
		return idSession;
	}

	public void setIdSession(Long idSession) {
		this.idSession = idSession;
	}

	public List<Evenement> getEvenementsSession() {
		return evenementsSession;
	}

	public void setEvenementsSession(List<Evenement> evenementsSession) {
		this.evenementsSession = evenementsSession;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public String getEtatModule() {
		return etatModule;
	}

	public void setEtatModule(String etatModule) {
		this.etatModule = etatModule;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public String getMonthOfEvenement() {
		return monthOfEvenement;
	}

	public void setMonthOfEvenement(String monthOfEvenement) {
		this.monthOfEvenement = monthOfEvenement;
	}

	public String getDayOfEvenement() {
		return dayOfEvenement;
	}

	public void setDayOfEvenement(String dayOfEvenement) {
		this.dayOfEvenement = dayOfEvenement;
	}

	

}
