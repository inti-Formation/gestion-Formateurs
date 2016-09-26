package com.adaming.myapp.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.adaming.myapp.entities.Etudiant;
import com.adaming.myapp.entities.Examen;
import com.adaming.myapp.entities.Module;
import com.adaming.myapp.entities.Note;
import com.adaming.myapp.entities.Question;
import com.adaming.myapp.entities.SessionEtudiant;
import com.adaming.myapp.etudiant.service.IEtudiantService;
import com.adaming.myapp.examen.service.IExamenService;
import com.adaming.myapp.exception.AddExamenException;
import com.adaming.myapp.module.service.IModuleService;
import com.adaming.myapp.notes.service.INotesService;
import com.adaming.myapp.question.service.IQuestionService;
import com.adaming.myapp.session.service.ISessionService;
import com.lowagie.text.pdf.AcroFields.Item;

@Component("examenBean")
@Scope(value="session")
public class ExamenBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private IExamenService serviceExamen;
	@Inject
	private ISessionService serviceSession;
	@Inject
	private IEtudiantService serviceEtudiant;
	@Inject
	private IModuleService serviceModule;
	@Inject
	private IQuestionService serviceQuestion;
	@Inject
	private INotesService serviceNotes;

	private Long idSession;
	private Long idModule;
	private Long idExamen;
	private Long idEtudiant;
	private Date dateExamen = new Date();
	private List<Question> questionsByModule;
	private List<SessionEtudiant> sessionEnCours;
	private List<Etudiant> etudiantsBySession;
	private List<Module> moduleBySessions;
	private String addExamException;
	private String confirm=null;
	private Double note=0.0;
	private String reponseSelectionnee;
	private Object scoreFinal;

	

	

	/* @method load Page */
	public void init() {
		sessionEnCours = serviceSession.getAllSessionsInProgress();
		confirm=new String();//ok on click in button we show message
	   
	}
	
	
	/*@method redirection à la fin de l'examen et afficher la note final*/
	public String redirect(){
		scoreFinal=serviceExamen.getScoreExamen(idSession, idEtudiant, idModule);
		Note note = new Note(scoreFinal);
		serviceNotes.addNoteFinal(note, idSession, idEtudiant, idModule);
		return "examen_success?redirect=true";
	}

	/* @method for to passe un examens */
	public String goStartExamen() {
		setEtudiantsBySession(null);
		setModuleBySessions(null);
		return "start_examen?redirect=true";
	}

	
	/* @method add ExamenV3 */
	public void registerV3(){
			Examen ex = new Examen(dateExamen,note,reponseSelectionnee);
			serviceExamen.addExamenV2(ex, idEtudiant, idSession, idModule);
			setConfirm("OK");
			//init reponsesFinal
			reponseSelectionnee=new String();
	}

	/* @method get All Students By Session */
	public void getAllStudentsBySession() {
		etudiantsBySession = new ArrayList<Etudiant>();
		etudiantsBySession = serviceEtudiant.getEtudiantBySession(idSession);
	}

	/* @@method get All Modules By Session */
	public void getAllModulesBySession() {
		moduleBySessions= new ArrayList<Module>();
		moduleBySessions = serviceModule.getModulesBySession(idSession);
	}

	/* @@method getAllQuestions By Module */
	public void getAllQuestionByModule() {
		questionsByModule= new ArrayList<Question>();
		questionsByModule = serviceQuestion.getAllQuestionsByModule(idModule);
	}


	public Long getIdSession() {
		return idSession;
	}

	public void setIdSession(Long idSession) {
		this.idSession = idSession;
	}

	public Long getIdModule() {
		return idModule;
	}

	public void setIdModule(Long idModule) {
		this.idModule = idModule;
	}

	public Long getIdExamen() {
		return idExamen;
	}

	public void setIdExamen(Long idExamen) {
		this.idExamen = idExamen;
	}

	public Long getIdEtudiant() {
		return idEtudiant;
	}

	public void setIdEtudiant(Long idEtudiant) {
		this.idEtudiant = idEtudiant;
	}

	public Date getDateExamen() {
		return dateExamen;
	}

	public void setDateExamen(Date dateExamen) {
		this.dateExamen = dateExamen;
	}

	public List<Question> getQuestionsByModule() {
		return questionsByModule;
	}

	public void setQuestionsByModule(List<Question> questionsByModule) {
		this.questionsByModule = questionsByModule;
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

	public List<Module> getModuleBySessions() {
		return moduleBySessions;
	}

	public void setModuleBySessions(List<Module> moduleBySessions) {
		this.moduleBySessions = moduleBySessions;
	}

	public String getAddExamException() {
		return addExamException;
	}

	public void setAddExamException(String addExamException) {
		this.addExamException = addExamException;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public Double getNote() {
		return note;
	}

	public void setNote(Double note) {
		this.note = note;
	}

	public String getReponseSelectionnee() {
		return reponseSelectionnee;
	}

	public void setReponseSelectionnee(String reponseSelectionnee) {
		this.reponseSelectionnee = reponseSelectionnee;
	}


	public Object getScoreFinal() {
		return scoreFinal;
	}


	public void setScoreFinal(Object scoreFinal) {
		this.scoreFinal = scoreFinal;
	}

	

}
