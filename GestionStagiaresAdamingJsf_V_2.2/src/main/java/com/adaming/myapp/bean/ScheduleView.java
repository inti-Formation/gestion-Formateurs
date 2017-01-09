package com.adaming.myapp.bean;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.binding.message.Severity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.adaming.myapp.entities.Etudiant;
import com.adaming.myapp.entities.Evenement;
import com.adaming.myapp.entities.Formateur;
import com.adaming.myapp.entities.Module;
import com.adaming.myapp.entities.SessionEtudiant;
import com.adaming.myapp.etudiant.service.IEtudiantService;
import com.adaming.myapp.evenement.service.IEvenementService;
import com.adaming.myapp.exception.EvenementNotFoundException;
import com.adaming.myapp.exception.VerificationInDataBaseException;
import com.adaming.myapp.formateur.service.IFormateurService;
import com.adaming.myapp.module.service.IModuleService;

@SuppressWarnings("serial")
@Component("scheduleView")
@Scope("session")
public class ScheduleView  implements Serializable {

	/**
	 * LOGGER LOG4j 
	 * @see org.apache.log4j.Logger
	 */
     private final Logger LOGGER  = Logger.getLogger("ScheduleView");
    
     
    
	@Inject
	private IEtudiantService serviceEtudiant;
	@Inject
	private IModuleService serviceModule;

	@Inject
	private IFormateurService serviceFormateur;

	@Inject
	private IEvenementService serviceEvenement;
	/* get the name of user (formateur) for evenement */
	@Inject
	private UserAuthentificationBean userAuthentificationBean;


	private Long idSession;
	private Long idModule;
	private Long idSpecialite;
	private List<Etudiant> etudiantsBySession;
	private List<Module> modules;
	private Module module;

	private Date dateIn;
	private String[] dateString;
	private DateTime[] joursSemaine;
	private int annee;
	private int semaine;
	private SessionEtudiant sessionEtudiant;
	private boolean dispo;
	/**@evenement */

	private Long idEtudiant;
	private Date dateStart;
	private Date dateEnd;
	private String typeEvenement;
	private String evenementFoundException;
	private String evenementSuccess;
    private boolean active = false;
	private Formateur formateur;
	private Long idFormateur;
	private List<SessionEtudiant> sessionsFormateur;
	private SessionEtudiant sessionFormateur;
	private List<Evenement> events;

	public void initReporting() throws VerificationInDataBaseException{
		evenementFoundException = "";
		formateur = FactoryBean.getFormateurFactory().create("Formateur");
		formateur = serviceFormateur.getFormateur(userAuthentificationBean
				.getName());
		LOGGER.debug(":::::::idFormateur" + formateur.getIdFormateur());
		sessionsFormateur = formateur.getSessionsEtudiant();

		if (sessionsFormateur.size() > 0) {
			DateTime dateFinS = null;
			for (SessionEtudiant session : sessionsFormateur) {

				dateFinS = new DateTime(session.getDateFin());
				if (dateFinS.isAfterNow()) {
					LOGGER.debug("::::::::    on rentre dans if compare date");
					idSession = session.getIdSession();
					LOGGER.debug(":::::::: idSession: " + idSession);
					sessionFormateur = session;
				}

			}
			evenementFoundException = "";
		    genererSchedule();

		} else {
			evenementFoundException = "Pas de sessions en cours !";
		}
		idFormateur = formateur.getIdFormateur();
	}

	public String initEvenement() throws VerificationInDataBaseException {
		initReporting();
		return "evenement?redirect=true";
	}

	public String initWarning() throws VerificationInDataBaseException {
		initReporting();
		return "warning?redirect=true";
	}

	public String initAbsences() throws VerificationInDataBaseException {
		initReporting();
		dateIn = new Date();
		active = false;
		//genererSchedule();
		return "absence?redirect=true";
	}
	
	/*public String colorMoy(String m) {
		if (m != null && !m.equals("--")) {

			if (Float.parseFloat(m) >= (Float.parseFloat("10"))) {
				return "color: blue;";
			} else {
				return "color: red;";
			}
		}
		return null;

	}

	public String colorTd(String s) {
		System.out.println(s);
		if (s != null) {
			if(s.equalsIgnoreCase("R")){
				System.out.println("color");
				return "background-color:red";
			}
			else if(s.equalsIgnoreCase("A")){
				return "background-color:green;";
			}
			else if(s.equalsIgnoreCase("E")){
				return "background-color: rgba(251, 13, 13, 0.32);";
			}
			return "background-color: rgba(251, 13, 13, 0.32);";
		} else {
			return null;
		}

	}*/


	public String initProspection() throws VerificationInDataBaseException {
		initReporting();
		return "prospection?redirect=true";
	}

	public String initEvaluation() throws VerificationInDataBaseException {
		initReporting();
		getAllModulesBySession();
		active = false;
		setIdModule(null);
		return "evaluation?redirect=true";
	}

	public String initActivationModule() throws VerificationInDataBaseException {
		initReporting();
		getAllModulesBySession();
		return "activation_module?redirect=true";
	}

	/** @method get All Students By Session */
	public void getAllStudentsBySession(){
		etudiantsBySession = new ArrayList<Etudiant>();
		if (idSession != null) {
			try {
				etudiantsBySession = serviceEtudiant
						.getEtudiantBySession(idSession);
			} catch (VerificationInDataBaseException e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning !","Aucun étudiant dans cette session !"));
			}

			if (etudiantsBySession.size() > 0) {
				sessionEtudiant =FactoryBean.getSessionFactory().create("SessionEtudiant");
				for (Etudiant se : etudiantsBySession) {
					sessionEtudiant = se.getSessionEtudiant();
				}
				evenementFoundException = "";
			} else {
				evenementFoundException = "Pas d'étudiants dans cette session !";
			}
		} else {
			evenementFoundException = "Pas de sessions en cours !";
		}

	}

	/* @method generate Absences */
	public void genererSchedule(){
		genererDates();
		getAllStudentsBySession();
	}

	public void getAllModulesBySession() {
		if (idSession != null) {
			modules = serviceModule.getModulesBySession(idSession);
			evenementFoundException = "";
		} else {
			evenementFoundException = "Pas de sessions en cours !";
		}
	}

	public void getModuleById() {
		module = FactoryBean.getModuleFactory().create("Module");
		module = serviceModule.getModuleById(idModule);
	}

	/* @method get module by id for activation Formateur */
	public void getCurrentModule(Long idModule) {
		module = FactoryBean.getModuleFactory().create("Module");
		module = serviceModule.getModuleById(idModule);
	}

	/* @method update */
	public String edit(Long idSpecialite) {
		serviceModule.updateModule(module, idSpecialite);
		return "module_update_success?redirect=true";
	}

	/* @methode generate Evaluations */
	public void genererScheduleEvaluations() throws VerificationInDataBaseException {
		getAllStudentsBySession();
		getModuleById();
		active=true;
	}
	
	/*@methode generate date and events*/
	public void generateEvents() throws VerificationInDataBaseException{
		try {
			events = serviceEvenement
					.getAllEvenementsBetweenTwoDate(idSession, dateIn);
			genererSchedule();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning","Les évènements trouvés à partir du "+dateIn+" Veuillez modifié le tableau en desous"));
			active=true;
		} catch (EvenementNotFoundException e) {
			genererSchedule();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",e.getMessage()));
			events=null;
			active=true;
		}
		

	}

	/** @method generate date */
	public void genererDates() {
		DateTime date = new DateTime(dateIn);
		final int dayOfWeek = date.getDayOfWeek();
		annee = date.getYear();
		semaine = date.getWeekOfWeekyear();

		// si jour select n'est pas un lundi
		if (dayOfWeek != 1) {
			date = date.withDayOfWeek(1);
		}

		joursSemaine = new DateTime[5];
		dateString = new String[5];
		// joursSemaine[0] = date.toDate();

		// on boucle jusqu'a vendredi
		for (int i = 0; i < 5; i++) {
			joursSemaine[i] = date.plusDays(i);
			dateString[i] = joursSemaine[i].getDayOfMonth() + "/"
					+ joursSemaine[i].getMonthOfYear();
		}
	}

	/* reset evenement */
	public void resetEvenement() {
		dateStart = null;
		dateEnd = null;
		idEtudiant = null;
		typeEvenement = "";
	}

	/** @methode signaler un retard (refactoring)*/
	private void signalerUnRetad() {
		Evenement retard = null;
		retard = FactoryBean.getEvenementFactory().create("retard");
		retard.setStartDate(dateStart);
		retard.setEndDate(dateEnd);
		retard.setCurentDate(new Date());
		retard.setSignaleur(userAuthentificationBean.getName());
		try {
			serviceEvenement.addEvenement(retard, idSession, idEtudiant);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"le Retard de " + dateStart + " A " + dateEnd
									+ " à bien été signalé"));
			resetEvenement();
		} catch (VerificationInDataBaseException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", e
							.getMessage()));
			resetEvenement();
		}
	}

	/** @methode signaler une absence (refactoring) */
	private void signalerUneAbsence() {
		Evenement absence = null;
		absence = FactoryBean.getEvenementFactory().create("Absence");
		absence.setStartDate(dateStart);
		absence.setEndDate(dateEnd);
		absence.setCurentDate(new Date());
		absence.setSignaleur(userAuthentificationBean.getName());
		try {
			serviceEvenement.addEvenement(absence, idSession, idEtudiant);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"l'absence de " + dateStart + " A " + dateEnd
									+ " à bien été signalé"));
			resetEvenement();
		} catch (VerificationInDataBaseException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", e
							.getMessage()));
			resetEvenement();
		}
	}

	/** @methode signaler un entretien (refactoring) */
	private void signalerUnEntretien() {
		Evenement entretien = null;
		entretien = FactoryBean.getEvenementFactory().create("Entretien");
		entretien.setStartDate(dateStart);
		entretien.setEndDate(dateEnd);
		entretien.setCurentDate(new Date());
		entretien.setSignaleur(userAuthentificationBean.getName());
		try {
			serviceEvenement.addEvenement(entretien, idSession, idEtudiant);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"l'entretien de " + dateStart + " A " + dateEnd
									+ " à bien été signalé"));
			resetEvenement();
		} catch (VerificationInDataBaseException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", e
							.getMessage()));
			resetEvenement();
		}
	}

	/** @methode signaler un etudiantTop (refactoring)*/
	private void signalerUnEtudiantTop() {
		Evenement topEtudiant = null;
		topEtudiant = FactoryBean.getEvenementFactory().create("TopEtudiant");
		topEtudiant.setStartDate(new Date());
		topEtudiant.setEndDate(new Date());
		topEtudiant.setCurentDate(new Date());
		topEtudiant.setSignaleur(userAuthentificationBean.getName());
		try {
			serviceEvenement.AddWarningAndTop(topEtudiant, idSession,
					idEtudiant);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"l'evènement à bien été signalée"));
			resetEvenement();
		} catch (VerificationInDataBaseException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", e
							.getMessage()));
			resetEvenement();
		}
	}

	/** @methode signaler un etudiantTop (refactoring) */
	private void signalerUnEtudiantWarning() {
		Evenement warningEtudiant = null;
		warningEtudiant = FactoryBean.getEvenementFactory().create("WarningEtudiant");
		warningEtudiant.setStartDate(new Date());
		warningEtudiant.setEndDate(new Date());
		warningEtudiant.setCurentDate(new Date());
		warningEtudiant.setSignaleur(userAuthentificationBean.getName());
		try {
			serviceEvenement.AddWarningAndTop(warningEtudiant, idSession,
					idEtudiant);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"l'evènement à bien été signalée"));
			resetEvenement();
		} catch (VerificationInDataBaseException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", e
							.getMessage()));
			resetEvenement();
		}
	}

	/* @method signaler un evenement */
	public void signalerEvenement() {
		if (!typeEvenement.equals(null)) {
			if (typeEvenement.equals("retard")) {
				signalerUnRetad();
			} else if (typeEvenement.equals("absence")) {
				signalerUneAbsence();
			} else if (typeEvenement.equals("entretient")) {
				signalerUnEntretien();
			} else if (typeEvenement.equals("top")) {
				signalerUnEtudiantTop();
			} else if (typeEvenement.equals("warning")) {
				signalerUnEtudiantWarning();
			}
		}

	}

	/* :::::::::::::::::::::: */

	public Long getIdSession() {
		return idSession;
	}

	public void setIdSession(Long idSession) {
		this.idSession = idSession;
	}

	public List<Etudiant> getEtudiantsBySession() {
		return etudiantsBySession;
	}

	public void setEtudiantsBySession(List<Etudiant> etudiantsBySession) {
		this.etudiantsBySession = etudiantsBySession;
	}

	public Date getDateIn() {
		return dateIn;
	}

	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}

	public String[] getDateString() {
		return dateString;
	}

	public void setDateString(String[] dateString) {
		this.dateString = dateString;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public int getSemaine() {
		return semaine;
	}

	public void setSemaine(int semaine) {
		this.semaine = semaine;
	}

	public boolean isDispo() {
		return dispo;
	}

	public void setDispo(boolean dispo) {
		this.dispo = dispo;
	}

	public SessionEtudiant getSessionEtudiant() {
		return sessionEtudiant;
	}

	public void setSessionEtudiant(SessionEtudiant sessionEtudiant) {
		this.sessionEtudiant = sessionEtudiant;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public Long getIdModule() {
		return idModule;
	}

	public void setIdModule(Long idModule) {
		this.idModule = idModule;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public Long getIdEtudiant() {
		return idEtudiant;
	}

	public void setIdEtudiant(Long idEtudiant) {
		this.idEtudiant = idEtudiant;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public String getTypeEvenement() {
		return typeEvenement;
	}

	public void setTypeEvenement(String typeEvenement) {
		this.typeEvenement = typeEvenement;
	}

	public void setServiceEtudiant(IEtudiantService serviceEtudiant) {
		this.serviceEtudiant = serviceEtudiant;
	}

	public void setUserAuthentificationBean(
			UserAuthentificationBean userAuthentificationBean) {
		this.userAuthentificationBean = userAuthentificationBean;
	}

	public String getEvenementFoundException() {
		return evenementFoundException;
	}

	public void setEvenementFoundException(String evenementFoundException) {
		this.evenementFoundException = evenementFoundException;
	}

	public String getEvenementSuccess() {
		return evenementSuccess;
	}

	public void setEvenementSuccess(String evenementSuccess) {
		this.evenementSuccess = evenementSuccess;
	}

	public Formateur getFormateur() {
		return formateur;
	}

	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}

	public void setServiceFormateur(IFormateurService serviceFormateur) {
		this.serviceFormateur = serviceFormateur;
	}

	public List<SessionEtudiant> getSessionsFormateur() {
		return sessionsFormateur;
	}

	public void setSessionsFormateur(List<SessionEtudiant> sessionsFormateur) {
		this.sessionsFormateur = sessionsFormateur;
	}

	public Long getIdFormateur() {
		return idFormateur;
	}

	public void setIdFormateur(Long idFormateur) {
		this.idFormateur = idFormateur;
	}

	public SessionEtudiant getSessionFormateur() {
		return sessionFormateur;
	}

	public void setSessionFormateur(SessionEtudiant sessionFormateur) {
		this.sessionFormateur = sessionFormateur;
	}

	public Long getIdSpecialite() {
		return idSpecialite;
	}

	public void setIdSpecialite(Long idSpecialite) {
		this.idSpecialite = idSpecialite;
	}

	public List<Evenement> getEvents() {
		return events;
	}

	public void setEvents(List<Evenement> events) {
		this.events = events;
	}

	public DateTime[] getJoursSemaine() {
		return joursSemaine;
	}

	public void setJoursSemaine(DateTime[] joursSemaine) {
		this.joursSemaine = joursSemaine;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	

}