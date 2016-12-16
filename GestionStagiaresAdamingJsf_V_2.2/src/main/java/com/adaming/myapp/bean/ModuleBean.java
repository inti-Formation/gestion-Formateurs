package com.adaming.myapp.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adaming.myapp.entities.Module;
import com.adaming.myapp.exception.AddModuleException;
import com.adaming.myapp.exception.VerificationInDataBaseException;
import com.adaming.myapp.module.service.IModuleService;
@SuppressWarnings("serial")
@Component("moduleBean")
@ViewScoped
public class ModuleBean implements Serializable {

	/**
	 * LOGGER LOG4j 
	 * @see org.apache.log4j.Logger
	 */
    private final Logger LOGGER  = Logger.getLogger("ExamenBean");
    
	@Inject
	private IModuleService serviceModule;
	
	private Long idModule;
	private String nomModule;
	private Long idSpecialite;
	private List<Module> modulesBySpecialites;
	private Module m;
	
	/*add new Module*/
	public void addModule(){
		m = new Module(nomModule);
		try {
			serviceModule.addModule(m, idSpecialite);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info","Le Module "+m.getNomModule()+" à bien été ajouter avec success"));
			reset();
		} catch (AddModuleException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!",e.getMessage()));
			
		}
	}
	
	public void reset(){
		nomModule = "";
		idSpecialite=null;
	}
	/*vider les champs on clickant sur l'aside ajouter un module*/
	public String resetAndRedirect(){
		reset();
		setModulesBySpecialites(null);
		return "module";
	}
	/*@method update*/
	public String edit(){
			serviceModule.updateModule(m, idSpecialite);
			return "module_update_success?redirect=true";
	}
    /*get Module By Specialities*/
	public void getModulesBySpecialite(){
			try {
				modulesBySpecialites=serviceModule.getModulesBySpecialite(idSpecialite);
				LOGGER.info("Modules By Specialites : "+modulesBySpecialites);
			} catch (VerificationInDataBaseException e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!",e.getMessage()));
				setModulesBySpecialites(null);
			}
	}
	/* get current module*/
	public void getCurrentModule(Long idModule){
		m=serviceModule.getModuleById(idModule);
		LOGGER.info("Module : "+m);
	}
	
	public Long getIdModule() {
		return idModule;
	}
	public void setIdModule(Long idModule) {
		this.idModule = idModule;
	}
	public String getNomModule() {
		return nomModule;
	}
	public void setNomModule(String nomModule) {
		this.nomModule = nomModule;
	}
	public Long getIdSpecialite() {
		return idSpecialite;
	}
	public void setIdSpecialite(Long idSpecialite) {
		this.idSpecialite = idSpecialite;
	}
	
	public List<Module> getModulesBySpecialites() {
		return modulesBySpecialites;
	}
	public void setModulesBySpecialites(List<Module> modulesBySpecialites) {
		this.modulesBySpecialites = modulesBySpecialites;
	}
	public Module getM() {
		return m;
	}
	public void setM(Module m) {
		this.m = m;
	}
	
	
	
	
}
