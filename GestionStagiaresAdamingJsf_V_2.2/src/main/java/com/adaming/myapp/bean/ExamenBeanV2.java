package com.adaming.myapp.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.adaming.myapp.entities.Etudiant;
import com.adaming.myapp.entities.Module;
import com.adaming.myapp.entities.Note;
import com.adaming.myapp.entities.Question;
import com.adaming.myapp.entities.SessionEtudiant;
import com.adaming.myapp.etudiant.service.IEtudiantService;
import com.adaming.myapp.module.service.IModuleService;
import com.adaming.myapp.notes.service.INotesService;
import com.adaming.myapp.question.service.IQuestionService;
import com.adaming.myapp.session.service.ISessionService;
import com.adaming.myapp.tools.MyComparator;

public class ExamenBeanV2 {

	

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
	@Inject
	private UserAuthentificationBean userAuthentification;

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
	private List<Question> reponses;
	private Etudiant etudiant;
	private String[] str;

	/* @method redirection à la fin de l'examen et afficher la note final */
	public String redirect() {

		// on cree la note finale
		Note noteF = new Note(note);

		try {
			serviceNotes.addNoteFinal(noteF, etudiant.getSessionEtudiant()
					.getIdSession(), etudiant.getIdEtudiant(), idModule);
		} catch (Exception e) {
			resetVarsExam();
			return "index?redirect=true";
		}

		// init reponsesFinal
		resetVarsExam();
		// on ordonne la listes des reponses par num
		Collections.sort(reponses, new MyComparator());

		return "examen_success?redirect=true";
	}

	/* @method copie de redirect() avec redirection a la fin de Timeout */
	public void onTimeout() {

		Note noteF = new Note(note);
		serviceNotes.addNoteFinal(noteF, etudiant.getSessionEtudiant()
				.getIdSession(), etudiant.getIdEtudiant(), idModule);

		// init reponsesFinal
		resetVarsExam();
		// on ordonne la listes des reponses par num
		Collections.sort(reponses, new MyComparator());

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

		// on decoupe les différentes vars separees par ','
		str = reponseSelectionnee.split(",");

		// on cree notre objet Reponse basee que Question
		Question reponse = new Question(Integer.parseInt(str[0]), str[1],
				Integer.parseInt(str[2]), str[7], str[8], str[9], str[10],
				str[3], str[4], str[5], str[6]);
        System.out.println("reponse selectionner "+reponseSelectionnee);
		// on set le string de la reponse choisie
		System.out.println("avant le set (CHoix de reponse)"+reponse.getStrReponse());
		reponse.setStrReponse(str[reponse.getChoixReponse() + 6]);
		System.out.println("apres le set (CHoix de reponse)"+reponse.getStrReponse());

		// on incremente la note si reponse bonne
		if (str[2 + reponse.getChoixReponse()].equals("bonne")) {
			note++;
			reponse.setPoint(1);
		} else {
			reponse.setPoint(0);
		}
		// on ajoute l'objet Reponse à une liste
		reponses.add(reponse);

	}

	public void resetVarsExam() {
		// init reponsesFinal
		reponseSelectionnee = new String();
		scoreFinal = note;
		note = 0.0;
		reponseSelectionnee = null;
		setConfirm("");
		questionsByModule = new ArrayList<Question>();

	}

	/* @method for to passe un examens */
	public String goStartExamen() {
		setModuleBySessions(null);
		scoreFinal = 0.0;
		reponseSelectionnee = null;
		reponses = new ArrayList<Question>();
		return "start_examen?redirect=true";
	}
	
    /*init examen*/
	public String initExamen() {
		
		etudiant = new Etudiant();
		etudiant = serviceEtudiant.getEtudiant(userAuthentification.getName());
		idSession = etudiant.getSessionEtudiant().getIdSession();
		idEtudiant = etudiant.getIdEtudiant();
		getAllModulesBySession();
		return "examen?redirect=true";

	}
	/*get all notes by student*/
	public String getAllNotesByStudent(){
		etudiant = new Etudiant();
		etudiant = serviceEtudiant.getEtudiant(userAuthentification.getName());
		idEtudiant = etudiant.getIdEtudiant();
		notes=new ArrayList<Note>();
		notes=serviceNotes.getAllNotesByStudent(idEtudiant);
		return "resultats?redirect=true";
	}

	/* @@method get All Modules By Session */
	public void getAllModulesBySession() {
		moduleBySessions = new ArrayList<Module>();
        notes=new ArrayList<Note>();
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

	public List<Question> getReponses() {
		return reponses;
	}

	public void setReponses(List<Question> reponses) {
		this.reponses = reponses;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public void setUserAuthentification(
			UserAuthentificationBean userAuthentification) {
		this.userAuthentification = userAuthentification;
	}

	public String[] getStr() {
		return str;
	}

	public void setStr(String[] str) {
		this.str = str;
	}
}
