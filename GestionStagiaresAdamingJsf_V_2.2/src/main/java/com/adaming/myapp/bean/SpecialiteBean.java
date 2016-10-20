package com.adaming.myapp.bean;


import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.adaming.myapp.entities.Specialite;
import com.adaming.myapp.exception.AddSpecialiteException;
import com.adaming.myapp.specialite.service.ISpecialiteService;

@Component("specialiteBean")
@Scope(value="request")
public class SpecialiteBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    Logger log = Logger.getLogger("SpecialiteBean");
    
	@Autowired
    private ISpecialiteService serviceSpec;
	
	private Specialite specialite;
	
	private List<Specialite> specialites;
	
	
	private Long idSpe;
	
	private Long idSpecialite;
	
	private String designation;

	public Long getIdSpecialite() {
		return idSpecialite;
	}
	/*get Specialite by id*/
	public Specialite getSpecialiteById(){
		idSpe = Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idSpe"));
		System.out.println("idSpe"+idSpe);
		specialite=serviceSpec.getSpecialiteById(idSpe);
		System.out.println("object : "+specialite);
		return specialite;
		
	}
	/*add Specialite*/
	public void addSpecialite(){
		Specialite specialite = new Specialite(designation);
		try {
			serviceSpec.addSpecialite(specialite);
			getAllSpec();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info","la spécialitée " + designation + " à bien été ajoutée"));
			designation = "";
		} catch (AddSpecialiteException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!",e.getMessage()));
		}
		
	}
	@PostConstruct
	public void getAllSpec(){
		specialites=serviceSpec.getAllSpec();
	}

	public void setIdSpecialite(Long idSpecialite) {
		this.idSpecialite = idSpecialite;
	}
	
	/*togel*/

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

	public Long getIdSpe() {
		return idSpe;
	}

	public void setIdSpe(Long idSpe) {
		this.idSpe = idSpe;
	}

	
}
