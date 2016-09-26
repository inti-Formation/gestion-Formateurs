package com.adaming.myapp.bean;


import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adaming.myapp.entities.Specialite;
import com.adaming.myapp.exception.AddSpecialiteException;
import com.adaming.myapp.specialite.service.ISpecialiteService;

@Component("specialiteBean")
@ApplicationScoped
public class SpecialiteBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    Logger log = Logger.getLogger("SpecialiteBean");
    
	@Autowired
    private ISpecialiteService serviceSpec;
	
	private Specialite specialite;
	
	private String addSpecialiteException;
	
	private List<Specialite> specialites;
	
	private String succes ;
	
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
			setAddSpecialiteException("");
			setSucces("la spécialitée "+designation+" à bien été ajoutée avec Success");
			//FacesContext.getCurrentInstance().getExternalContext().dispatch("specialite.xhtml");
		} catch (AddSpecialiteException e) {
			setAddSpecialiteException(e.getMessage());
			setSucces("");
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

	public String getAddSpecialiteException() {
		return addSpecialiteException;
	}

	public void setAddSpecialiteException(String addSpecialiteException) {
		this.addSpecialiteException = addSpecialiteException;
	}

	public String getSucces() {
		return succes;
	}

	public void setSucces(String succes) {
		this.succes = succes;
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
