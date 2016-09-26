package com.adaming.myapp.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.adaming.myapp.entities.Module;
import com.adaming.myapp.exception.AddModuleException;
import com.adaming.myapp.module.service.IModuleService;
@Component("moduleBean")
@ViewScoped
public class ModuleBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
	@Autowired
	private IModuleService serviceModule;
	
	private Long idModule;
	private String nomModule;
	private Long idSpecialite;
	private String addModuleException;
	private String success;
	private List<Module> modulesBySpecialites;
	private Module m;
	
	/*add new Module*/
	public void addModule(){
		m = new Module(nomModule);
		try {
			serviceModule.addModule(m, idSpecialite);
			setSuccess("Le Module "+m.getNomModule()+" à bien été ajouter avec success");
			setAddModuleException("");
		} catch (AddModuleException e) {
			setAddModuleException(e.getMessage());
			setSuccess("");
		}
	}
	/*@method update*/
	public String edit(){
			Module module=serviceModule.updateModule(m, idSpecialite);
			return "module_update_success?redirect=true";
	}
    /*get Module By Specialities*/
	public void getModulesBySpecialite(){
		modulesBySpecialites=serviceModule.getModulesBySpecialite(idSpecialite);
	}
	
	public void getCurrentModule(Long idModule){
		m=serviceModule.getModuleById(idModule);
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
	public String getAddModuleException() {
		return addModuleException;
	}
	public void setAddModuleException(String addModuleException) {
		this.addModuleException = addModuleException;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
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
