package com.adaming.myapp.bean;


import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.adaming.myapp.entities.Specialite;
import com.adaming.myapp.exception.AddSpecialiteException;
import com.adaming.myapp.specialite.service.ISpecialiteService;

@SuppressWarnings("serial")
@Component("specialiteBean")
@ViewScoped
public class SpecialiteBean implements Serializable{
	
	/**
	 * LOGGER log4J
	 * @see org.apache.log4j.Logger
	 * */
    private final Logger LOGGER = Logger.getLogger("SpecialiteBean");
    
	@Inject
    private ISpecialiteService serviceSpec;
	
	private Specialite specialite;
	
	private List<Specialite> specialites;

	private Long idSpecialite;
	
	private String designation;
    
	
	/*@method getSpecialite By Id*/
	public void getSpecialiteById(Long idSpecialite){
		specialite=serviceSpec.getSpecialiteById(idSpecialite);
	}
	/*method modifier*/
	public String edit(){
		serviceSpec.updateSpecialite(specialite);
		getAllSpecialite();
		return "specialite?faces-redirect=true";
	}
	
	/**@method add Specialite*/
	public void addSpecialite(){
		specialite = createSpecialite();
		try {
			serviceSpec.addSpecialite(specialite);
			getAllSpecialite();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info","la spécialitée " + designation + " à bien été ajoutée"));
			designation = "";
		} catch (AddSpecialiteException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!",e.getMessage()));
		}
		
	}
	
	/**
	 * @create New Specialite
	 **@return Object Specialite 
	 **@factory.create.method
	 */
	private Specialite createSpecialite() {
		specialite = FactoryBean.getSepcialiteFactory().create("Specialite");
		specialite.setDesignation(designation);
		return specialite;
	}
	@PostConstruct
	public void getAllSpec(){
		getAllSpecialite();
		LOGGER.info("Specialitées"+specialites);
	}
	/**@method getAllSpecialite*/
	public void getAllSpecialite(){
		specialites=serviceSpec.getAllSpecV2();
	}

	public void setIdSpecialite(Long idSpecialite) {
		this.idSpecialite = idSpecialite;
	}


	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	

	public List<Specialite> getSpecialites() {
		return specialites;
	}

	public void setSpecialites(List<Specialite> specialites) {
		this.specialites = specialites;
	}

	public Specialite getSpecialite() {
		return specialite;
	}

	public void setSpecialite(Specialite specialite) {
		this.specialite = specialite;
	}

	public Long getIdSpecialite() {
		return idSpecialite;
	}

	
}
