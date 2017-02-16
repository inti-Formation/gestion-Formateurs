package com.adaming.myapp.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.adaming.myapp.module.service.IModuleService;
import com.adaming.myapp.notes.service.INotesService;
import com.adaming.myapp.session.service.ISessionService;
import com.adaming.myapp.tools.LoggerConfig;
import com.adaming.myapp.tools.Utilitaire;
/**
 * 
 * @author adel
 * @date 10/10/2016
 * @version 1.0.0
 * */
@SuppressWarnings("serial")
@Component("suivieExamenBean")
@ViewScoped
public class SuivieExamenBean implements Serializable{
    
	@Inject
	private ISessionService serviceSession;
	@Inject
	private IModuleService serviceModule;
	@Inject
	private INotesService serviceNote;

	private Long idSession;
	private List<Object[]> sessionEnCours;
	private List<Object[]> modules;
	private List<Object[]> notes;
	private List<Object[]> results;
	private Double moyenne;
	
	public String init(){
		idSession = null;
		setModules(null);
		sessionEnCours = serviceSession.getAllSessionsInProgressV2();
		return "suivie_examen?faces-redirect=true";
	}
	
	/** cette method permet de vérifier est ce que le module à été passée ou non **/
	public void getModulesBySession(){
		modules=serviceModule.getModulesBySessionV2(idSession);
		if(modules.size()>0){
			for(Object[] m:modules){
				Long idModule = (Long) m[0];
				moyenne = serviceNote.getMoyenne(idSession,idModule);
				if(moyenne != null){
					m[2] = true;
					LoggerConfig.logDebug("module "+m[0]+"--"+moyenne);
				}else{
					m[2] = false;
					LoggerConfig.logDebug("module "+m[0]+"--"+moyenne);
				}
				
			}
		}else{
			Utilitaire.displayMessageWarning("Aucun Module Trouvé dans la session N° "+idSession);
		}
		
		
	}
	
	/** get all notes by session end modules**/
	public String getNotesByModulesAndSession(Long idSession,Long idModule){
		results = serviceNote.getNotesBySessionAndModule(idSession, idModule);
		return "notes?faces-redirect=true";
	}

	public Long getIdSession() {
		return idSession;
	}
	public void setIdSession(Long idSession) {
		this.idSession = idSession;
	}
	public List<Object[]> getModules() {
		return modules;
	}
	public void setModules(List<Object[]> modules) {
		this.modules = modules;
	}
	public List<Object[]> getSessionEnCours() {
		return sessionEnCours;
	}
	public void setSessionEnCours(List<Object[]> sessionEnCours) {
		this.sessionEnCours = sessionEnCours;
	}

	public List<Object[]> getResults() {
		return results;
	}

	public void setResults(List<Object[]> results) {
		this.results = results;
	}

	public Double getMoyenne() {
		return moyenne;
	}

	public void setMoyenne(Double moyenne) {
		this.moyenne = moyenne;
	}

}
