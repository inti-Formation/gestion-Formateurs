package com.adaming.myapp.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.joda.time.DateTime;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.adaming.myapp.entities.Absence;
import com.adaming.myapp.entities.Entretien;
import com.adaming.myapp.entities.Etudiant;
import com.adaming.myapp.entities.Module;
import com.adaming.myapp.entities.Retard;
import com.adaming.myapp.entities.SessionEtudiant;
import com.adaming.myapp.entities.Specialite;
import com.adaming.myapp.etudiant.service.IEtudiantService;
import com.adaming.myapp.evenement.service.IEvenementService;
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
	private IEvenementService serviceEvenement;
	
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
	/*evenement*/
	
	private Long idEtudiant;
	private Date dateStart;
	private Date dateEnd;
	private String typeEvenement;
	
	@PostConstruct
	public void init() {
		sessionEnCours = serviceSession.getAllSessionsInProgress();
       
	}

	/* @method get All Students By Session */
	public void getAllStudentsBySession() {
		etudiantsBySession = new ArrayList<Etudiant>();
		etudiantsBySession = serviceEtudiant.getEtudiantBySession(idSession);
		
		for(Etudiant se : etudiantsBySession){
	        	sessionEtudiant= new SessionEtudiant();
	        	sessionEtudiant=se.getSessionEtudiant();
	    }
	}
	
	
    /* @method generate Absences*/
	public void genererSchedule() {

		getAllStudentsBySession();
		genererDates();

	}
	/* @method get all modules by sessions*/
	public void getAllModulesBySession(){
		modules=serviceModule.getModulesBySession(idSession);
	}
	/* @method get module by id*/
	public void getModuleById(){
		module= new Module();
		module=serviceModule.getModuleById(idModule);
	}
	/* @methode generate Evaluations*/
	public void genererScheduleEvaluations() {

		getAllStudentsBySession();
		getModuleById();

	}
	
   /* @method generate date*/
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
	
	/*@method signaler un evenement */
	public void signalerEvenement(){
		Retard  retard       = null;
		Absence absence      = null;
		Entretien entretient = null;
		
		if(!typeEvenement.equals(null)){
			if(typeEvenement.equals("retard")){
				retard=new Retard(dateStart, dateEnd);
				serviceEvenement.addRetard(retard, idSession, idEtudiant);
			}
			else if(typeEvenement.equals("absence")){
				absence=new Absence(dateStart, dateEnd);
				serviceEvenement.addAbsence(absence, idSession, idEtudiant);
			}
			else if(typeEvenement.equals("entretient")){
				entretient = new Entretien(dateStart,dateEnd);
				serviceEvenement.addEntretien(entretient, idSession, idEtudiant);
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

	
	

}