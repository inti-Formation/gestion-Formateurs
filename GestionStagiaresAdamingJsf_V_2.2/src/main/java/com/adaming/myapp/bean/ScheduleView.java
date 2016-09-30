package com.adaming.myapp.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import com.adaming.myapp.entities.Etudiant;
import com.adaming.myapp.entities.SessionEtudiant;
import com.adaming.myapp.etudiant.service.IEtudiantService;
import com.adaming.myapp.session.service.ISessionService;

@Component("scheduleView")
@ViewScoped
public class ScheduleView {

	private Long idSession;
	private List<SessionEtudiant> sessionEnCours;
	private List<Etudiant> etudiantsBySession;

	private Date dateIn;
	private String[] dateString;
	private int annee;
	private int semaine;

	private boolean dispo;

	@Inject
	private ISessionService serviceSession;

	@Inject
	private IEtudiantService serviceEtudiant;

	public void setServiceSession(ISessionService serviceSession) {
		this.serviceSession = serviceSession;
	}

	public void setServiceEtudiant(IEtudiantService serviceEtudiant) {
		this.serviceEtudiant = serviceEtudiant;
	}

	@PostConstruct
	public void init() {
		sessionEnCours = serviceSession.getAllSessionsInProgress();

	}

	/* @method get All Students By Session */
	public void getAllStudentsBySession() {
		etudiantsBySession = new ArrayList<Etudiant>();
		etudiantsBySession = serviceEtudiant.getEtudiantBySession(idSession);

	}

	public void genererSchedule() {

		getAllStudentsBySession();
		genererDates();

	}

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

}