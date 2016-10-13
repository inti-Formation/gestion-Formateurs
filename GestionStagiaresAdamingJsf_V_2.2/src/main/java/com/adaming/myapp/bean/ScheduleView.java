package com.adaming.myapp.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.joda.time.DateTime;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.adaming.myapp.entities.Absence;
import com.adaming.myapp.entities.Entretien;
import com.adaming.myapp.entities.Etudiant;
import com.adaming.myapp.entities.Formateur;
import com.adaming.myapp.entities.Module;
import com.adaming.myapp.entities.Retard;
import com.adaming.myapp.entities.SessionEtudiant;
import com.adaming.myapp.entities.TopEtudiant;
import com.adaming.myapp.entities.WarningEtudiant;
import com.adaming.myapp.etudiant.service.IEtudiantService;
import com.adaming.myapp.evenement.service.IEvenementService;
import com.adaming.myapp.exception.VerificationInDataBaseException;
import com.adaming.myapp.formateur.service.IFormateurService;
import com.adaming.myapp.module.service.IModuleService;
import com.adaming.myapp.session.service.ISessionService;

@Component("scheduleView")
@Scope("session")
public class ScheduleView {

	@Inject
	private ISessionService serviceSession;

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
	private List<SessionEtudiant> sessionEnCours;
	private List<Etudiant> etudiantsBySession;
	private List<Module> modules;
	private Module module;

	private Date dateIn;
	private String[] dateString;
	private int annee;
	private int semaine;
	private SessionEtudiant sessionEtudiant;
	private boolean dispo;
	/* evenement */

	private Long idEtudiant;
	private Date dateStart;
	private Date dateEnd;
	private String typeEvenement;
	private String evenementFoundException;
	private String evenementSuccess;

	private Formateur formateur;
	private Long idFormateur;
	private List<SessionEtudiant> sessionsFormateur;
	private SessionEtudiant sessionFormateur;

	@PostConstruct
	public void init() {
		sessionEnCours = serviceSession.getAllSessionsInProgress();

	}

	public void initReporting() {
		evenementFoundException = "";
		formateur = new Formateur();
		formateur = serviceFormateur.getFormateur(userAuthentificationBean
				.getName());
		System.out.println(":::::::idFormateur" + formateur.getIdFormateur());
		sessionsFormateur = formateur.getSessionsEtudiant();

		if (sessionsFormateur.size() > 0) {

			for (SessionEtudiant session : sessionsFormateur) {

				DateTime dateFinS = new DateTime(session.getDateFin());
				if (dateFinS.isAfterNow()) {
					System.out
							.println("::::::::    on rentre dans if compare date");
					idSession = session.getIdSession();
					System.out.println(":::::::: idSession: " + idSession);
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

	public String initEvenement() {
		initReporting();
		return "evenement?redirect=true";

	}

	public String initWarning() {
		initReporting();
		return "warning?redirect=true";

	}

	public String initAbsences() {
		initReporting();
		dateIn = new Date();
		genererSchedule();
		return "absence?redirect=true";

	}

	public String initProspection() {
		initReporting();
		return "prospection?redirect=true";

	}

	public String initEvaluation() {
		initReporting();
		getAllModulesBySession();
		return "evaluation?redirect=true";

	}

	/* @method get All Students By Session */
	public void getAllStudentsBySession() {
		etudiantsBySession = new ArrayList<Etudiant>();

		if (idSession != null) {
			etudiantsBySession = serviceEtudiant
					.getEtudiantBySession(idSession);

			if (etudiantsBySession.size() > 0) {
				for (Etudiant se : etudiantsBySession) {
					sessionEtudiant = new SessionEtudiant();
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
	public void genererSchedule() {

		genererDates();
		getAllStudentsBySession();

	}

	/* @method get all modules by sessions */
	public void getAllModulesBySession() {
		if (idSession != null) {
			modules = serviceModule.getModulesBySession(idSession);
			evenementFoundException = "";
		} else {
			evenementFoundException = "Pas de sessions en cours !";
		}

	}

	/* @method get module by id */
	public void getModuleById() {
		module = new Module();
		module = serviceModule.getModuleById(idModule);
	}

	/* @methode generate Evaluations */
	public void genererScheduleEvaluations() {

		getAllStudentsBySession();
		getModuleById();

	}

	/* @method generate date */
	public void genererDates() {

		DateTime date = new DateTime(dateIn);

		int dayOfWeek = date.getDayOfWeek();
		annee = date.getYear();
		semaine = date.getWeekOfWeekyear();

		// si jour select n'est pas un lundi
		if (dayOfWeek != 1) {
			date = date.withDayOfWeek(1);
		}

		DateTime[] joursSemaine = new DateTime[5];
		dateString = new String[5];
		// joursSemaine[0] = date.toDate();

		// on boucle jusqu'a vendredi
		for (int i = 0; i < 5; i++) {
			joursSemaine[i] = date.plusDays(i);
			dateString[i] = joursSemaine[i].getDayOfMonth() + "/"
					+ joursSemaine[i].getMonthOfYear();
		}

	}

	/* @method signaler un evenement */
	public void signalerEvenement() {
		Retard retard = null;
		Absence absence = null;
		Entretien entretien = null;
		TopEtudiant topEtudiant = null;
		WarningEtudiant warningEtudiant = null;

		if (!typeEvenement.equals(null)) {
			if (typeEvenement.equals("retard")) {
				retard = new Retard(dateStart, dateEnd,
						userAuthentificationBean.getName(), new Date());
				try {
					serviceEvenement.addRetard(retard, idSession, idEtudiant);
					setEvenementSuccess("le Retard de " + dateStart + " A "
							+ dateEnd + " à bien été signalé");
					setEvenementFoundException("");
				} catch (VerificationInDataBaseException e) {
					setEvenementFoundException(e.getMessage());
					setEvenementSuccess("");
				}
			} else if (typeEvenement.equals("absence")) {
				absence = new Absence(dateStart, dateEnd,
						userAuthentificationBean.getName(), new Date());
				try {
					serviceEvenement.addAbsence(absence, idSession, idEtudiant);
					setEvenementSuccess("l'absence" + " de " + dateStart
							+ " A " + dateEnd + " à bien été signalée");
					setEvenementFoundException("");
				} catch (VerificationInDataBaseException e) {
					setEvenementFoundException(e.getMessage());
					setEvenementSuccess("");
				}
			} else if (typeEvenement.equals("entretient")) {
				entretien = new Entretien(dateStart, dateEnd,
						userAuthentificationBean.getName(), new Date());
				try {
					serviceEvenement.addEntretien(entretien, idSession,
							idEtudiant);
					setEvenementSuccess("l'entretien de " + dateStart + " A "
							+ dateEnd + " à bien été signalée");
					setEvenementFoundException("");
				} catch (VerificationInDataBaseException e) {
					setEvenementFoundException(e.getMessage());
					setEvenementSuccess("");
				}
			} else if (typeEvenement.equals("top")) {
				topEtudiant = new TopEtudiant(new Date(), new Date(),
						userAuthentificationBean.getName(), new Date());
				try {
					serviceEvenement.addTop(topEtudiant, idSession, idEtudiant);
					setEvenementSuccess("l'evènement à bien été signalée");
					setEvenementFoundException("");
				} catch (VerificationInDataBaseException e) {
					setEvenementFoundException(e.getMessage());
					setEvenementSuccess("");
				}
			} else if (typeEvenement.equals("warning")) {
				warningEtudiant = new WarningEtudiant(new Date(), new Date(),
						userAuthentificationBean.getName(), new Date());
				try {
					serviceEvenement.addWarning(warningEtudiant, idSession,
							idEtudiant);
					setEvenementSuccess("l'evènement à bien été signalée");
					setEvenementFoundException("");
				} catch (VerificationInDataBaseException e) {
					setEvenementFoundException(e.getMessage());
					setEvenementSuccess("");
				}
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

	public List<SessionEtudiant> getSessionEnCours() {
		return sessionEnCours;
	}

	public void setSessionEnCours(List<SessionEtudiant> sessionEnCours) {
		this.sessionEnCours = sessionEnCours;
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

	public void setServiceSession(ISessionService serviceSession) {
		this.serviceSession = serviceSession;
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

}