package com.adaming.myapp.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.adaming.myapp.entities.Etudiant;
import com.adaming.myapp.entities.Module;
import com.adaming.myapp.entities.Note;
import com.adaming.myapp.entities.Question;
import com.adaming.myapp.entities.SessionEtudiant;
import com.adaming.myapp.etudiant.service.IEtudiantService;
import com.adaming.myapp.module.service.IModuleService;
import com.adaming.myapp.notes.service.INotesService;
import com.adaming.myapp.question.service.IQuestionService;
import com.adaming.myapp.tools.MyComparator;

@SuppressWarnings("serial")
@Component("examenBean")
@Scope(value = "session")
public class ExamenBean implements Serializable {

	/**
	 * LOGGER LOG4j 
	 * @see org.apache.log4j.Logger
	 */
    private final Logger LOGGER  = Logger.getLogger("ExamenBean");
	
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
	private Long idEtudiant;
	private List<Question> questionsByModule;
	private List<SessionEtudiant> sessionEnCours;
	private List<Etudiant> etudiantsBySession;
	private List<Module> moduleBySessions;
	private List<Note> notes;
	private String addExamException;
	private Double note = 0.0;
	private String reponseSelectionnee;
	private Object scoreFinal;
	private List<Question> reponses;
	private Etudiant etudiant;
	

	/* @method redirection � la fin de l'examen et afficher la note final */
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
		LOGGER.info("trier le tableau"+reponses);
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
		LOGGER.info("trier le tableau"+reponses);

		FacesContext
				.getCurrentInstance()
				.getApplication()
				.getNavigationHandler()
				.handleNavigation(FacesContext.getCurrentInstance(), null,
						"examen_success");
	}

	/* @method add ExamenV3 */
	public void registerV3() {
        Question question=null;
		List<Question> questions = serviceQuestion.getAllQuestionsByModule(idModule);
        if(!reponseSelectionnee.equals(null)){
             for(Question q:questions){
				if (reponseSelectionnee.equals(q.getPremeiereReponse()) && 
						q.getPremeiereBonneReponse().equals("bonne")) {
						note++;
					    q.setPoint(1);
					    q.setStrReponse(reponseSelectionnee);
					    question=q;
					    break;
					} 
					else if (reponseSelectionnee.equals(q.getDouxiemeReponse()) && 
							q.getDouxiemeBonneReponse().equals("bonne")) {
							note++;
							 q.setPoint(1);
							 q.setStrReponse(reponseSelectionnee);
							 question=q;
							 break;
					} 
					else if (reponseSelectionnee.equals(q.getTroisiemeReponse()) && 
							q.getTroisiemeBonneReponse().equals("bonne")) {
							note++;
							 q.setPoint(1);
							 q.setStrReponse(reponseSelectionnee);
							question=q;
							break;
					} 
					else if (reponseSelectionnee.equals(q.getQuatriemeReponse()) && 
							q.getQuatriemeBonneReponse().equals("bonne")) {
							note++;
							 q.setPoint(1);
							 q.setStrReponse(reponseSelectionnee);
						    question=q;
						    break;
					}
					else if (reponseSelectionnee.equals(q.getPremeiereReponse()) && 
							!q.getPremeiereBonneReponse().equals("bonne")
							|| reponseSelectionnee.equals(q.getDouxiemeReponse()) && 
							!q.getDouxiemeBonneReponse().equals("bonne")
							|| reponseSelectionnee.equals(q.getTroisiemeReponse()) && 
							!q.getTroisiemeBonneReponse().equals("bonne")
							|| reponseSelectionnee.equals(q.getQuatriemeReponse()) && 
							!q.getQuatriemeBonneReponse().equals("bonne")
							){
						 q.setStrReponse(reponseSelectionnee);
						 q.setPoint(0);
						 question=q;
						 break;
					}
			}
            reponses.add(question);
            LOGGER.info("tab"+reponses);
            LOGGER.debug("note "+note);
            LOGGER.debug("tab size"+reponses.size());
		}
       
	}

	public void resetVarsExam() {
		// init reponsesFinal
		reponseSelectionnee = new String();
		scoreFinal = note;
		note = 0.0;
		reponseSelectionnee = null;
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

					   LOGGER.info("Boucle Note "
							+ note.getEtudiant().getNomEtudiant());

					if (note.getScore() != null) {
						// modules pass�s par l'�tudiant
						modulesNotDisplay.add(note.getModule());
						LOGGER.info(note.getModule().getNomModule());
					}
				}
			}

			for (Module mod : moduleBySessions) {

				if (mod.isActif()) {

					LOGGER.debug("Boucle Module " + mod.getNomModule());
					modulesBySessionsActif.add(mod);

					for (Module modN : modulesNotDisplay) {

						LOGGER.debug("Test Boucle For n2");
						LOGGER.debug("Id mod " + mod.getIdModule());
						LOGGER.debug("Id modN " + mod.getIdModule());
						modulesInter = modulesBySessionsActif;
						if (mod.getIdModule().equals(modN.getIdModule())) {
							LOGGER.debug("Test Add moduleList");
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
				setAddExamException("Vous n'avez pas de modules � valider !");
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

	

	public Long getIdEtudiant() {
		return idEtudiant;
	}

	public void setIdEtudiant(Long idEtudiant) {
		this.idEtudiant = idEtudiant;
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

	


}
