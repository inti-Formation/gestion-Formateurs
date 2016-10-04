package com.adaming.myapp.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.adaming.myapp.entities.Etudiant;
import com.adaming.myapp.entities.Module;
import com.adaming.myapp.entities.Note;
import com.adaming.myapp.entities.Question;
import com.adaming.myapp.entities.SessionEtudiant;
import com.adaming.myapp.etudiant.service.IEtudiantService;
import com.adaming.myapp.examen.service.IExamenService;
import com.adaming.myapp.module.service.IModuleService;
import com.adaming.myapp.notes.service.INotesService;
import com.adaming.myapp.question.service.IQuestionService;
import com.adaming.myapp.session.service.ISessionService;

@Component("examenBean")
@Scope(value = "session")
public class ExamenBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1907479773940246301L;
	/**
	 * 
	 */

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
	private List<Note> notes;
	private String addExamException;
	private String confirm = null;
	private Double note = 0.0;
	private String reponseSelectionnee;
	private Object scoreFinal;

	/* @method load Pagee */
	public void init() {
		sessionEnCours = serviceSession.getAllSessionsInProgress();
		confirm = new String();// ok on click in button we show message

	}

	/* @method redirection à la fin de l'examen et afficher la note final */
	public String redirect() {

		// on cree la note finale
		Note noteF = new Note(note);
		serviceNotes.addNoteFinal(noteF, idSession, idEtudiant, idModule);

		// init reponsesFinal
		reponseSelectionnee = new String();
		scoreFinal = note;
		note = 0.0;

		return "examen_success?redirect=true";
	}

	/* @method copie de redirect() avec redirection a fin de Timeout */
	public void onTimeout() {

		Note noteF = new Note(note);
		serviceNotes.addNoteFinal(noteF, idSession, idEtudiant, idModule);

		// init reponsesFinal
		reponseSelectionnee = new String();
		scoreFinal = note;
		note = 0.0;

		FacesContext
				.getCurrentInstance()
				.getApplication()
				.getNavigationHandler()
				.handleNavigation(FacesContext.getCurrentInstance(), null,
						"examen_success");
	}

	/* @method add ExamenV3 */
	public void registerV3() {
		confirm = new String();
		setConfirm("Clicked");
		System.out
				.println("reponse selectionnee init : " + reponseSelectionnee);
		System.out.println("init Note Obtenu:" + note);

		// on incremente si reponse bonne
		if (reponseSelectionnee.equals("bonne")) {
			note++;
		}
		System.out.println("init apres test:" + note);

	}

	/* @method for to passe un examens */
	public String goStartExamen() {
		setEtudiantsBySession(null);
		setModuleBySessions(null);
		scoreFinal = 0.0;
		return "start_examen?redirect=true";
	}

	/* @method get All Students By Session */
	public void getAllStudentsBySession() {
		etudiantsBySession = new ArrayList<Etudiant>();
		etudiantsBySession = serviceEtudiant.getEtudiantBySession(idSession);
	}

	/* @@method get All Modules By Session */
	public void getAllModulesBySession() {
		moduleBySessions = new ArrayList<Module>();

		moduleBySessions = serviceModule.getModulesBySession(idSession);
		notes = serviceNotes.getAllNotes();
		boolean hasNotes = false;

		List<Module> modulesBySessionsActif = new ArrayList<Module>();
		List<Module> modulesInter = new ArrayList<Module>();
		List<Module> modulesNotDisplay = new ArrayList<Module>();
		if (!moduleBySessions.isEmpty()) {

			// on boucle les notes
			for (Note note : notes) {

				if (note.getEtudiant().getIdEtudiant() == idEtudiant) {

					System.out.println("Boucle Note "
							+ note.getEtudiant().getNomEtudiant());

					if (note.getScore() != null) {
						// modules passés par l'étudiant
						modulesNotDisplay.add(note.getModule());
						System.out.println(note.getModule().getNomModule());
					}
				}
			}

			for (Module mod : moduleBySessions) {

				if (mod.isActif()) {

					System.out.println("Boucle Module " + mod.getNomModule());
					modulesBySessionsActif.add(mod);

					for (Module modN : modulesNotDisplay) {

						System.out.println("Test Boucle For n2");
						System.out.println("Id mod " + mod.getIdModule());
						System.out.println("Id modN " + mod.getIdModule());
						modulesInter = modulesBySessionsActif;
						if (mod.getIdModule().equals(modN.getIdModule())) {
							System.out.println("Test Add moduleList");
							modulesInter.remove(mod);
							hasNotes = true;

						}
					}
				}
			}

			if (hasNotes) {
				moduleBySessions = modulesInter;
			} else {
				moduleBySessions = modulesBySessionsActif;

			}

			if (moduleBySessions.isEmpty()) {
				setAddExamException("Vous n'avez pas de modules à valider !");
			} else {
				setAddExamException("");
			}

		} else {

			setAddExamException("Pas de modules pour cette session !");
		}

	}

	/* @@method getAllQuestions By Module */
	public void getAllQuestionByModule() {
		questionsByModule = new ArrayList<Question>();
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

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

}
