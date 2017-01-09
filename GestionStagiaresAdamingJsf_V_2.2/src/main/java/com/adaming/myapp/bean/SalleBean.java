package com.adaming.myapp.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.binding.message.Severity;
import org.springframework.stereotype.Component;

import com.adaming.myapp.entities.Salle;
import com.adaming.myapp.entities.Site;
import com.adaming.myapp.exception.VerificationInDataBaseException;
import com.adaming.myapp.salle.service.ISalleService;
import com.adaming.myapp.site.service.ISiteService;
@SuppressWarnings("serial")
@Component("salleBean")
@ViewScoped
public class SalleBean implements Serializable {
    @Inject
	private ISalleService serviceSalle;
    @Inject
    private ISiteService serviceSite;
    
    private Salle salle;
    private List<Site> sites;
    private List<Salle> salles;
    
    @NotEmpty
	private String numeroSalle;
    @NotNull
	private Integer nbPlace;
    @NotNull
	private Long idSite;
	
	public void addSalle(){
		salle = createSalle();
		try {
			serviceSalle.add(salle, idSite);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"la Salle " + numeroSalle
									+ " à bien été ajouter avec success"));
			reset();
			getAllSites();
		} catch (VerificationInDataBaseException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning...!",
							e.getMessage()));
		}
	}


	/**
	 *  @create New Salle
	 ** @return Object Salle
	 ** @factory.create.method
	 */
	private Salle createSalle() {
		salle = FactoryBean.getSalleFactory().create("Salle");
		salle.setNbPlace(nbPlace);
		salle.setNumeroSalle(numeroSalle);
		return salle;
	}

	public void getAllSites(){
		sites = serviceSite.getAll();
	}
	
	public void reset(){
		nbPlace = 0;
		numeroSalle  = "";
		idSite = null;
		
	}
	
	public String resetAndRedirect(){
		getAllSites();
		reset();
		return "salle?faces-redirect=true";
	}
	
	public String getAllSalle(){
		salles = serviceSalle.getAll();
		return "liste_salles?faces-redirect=true";
	}
	
	
	/**
	 * @return the numeroSalle
	 */
	public String getNumeroSalle() {
		return numeroSalle;
	}
	/**
	 * @param numeroSalle the numeroSalle to set
	 */
	public void setNumeroSalle(String numeroSalle) {
		this.numeroSalle = numeroSalle;
	}
	/**
	 * @return the nbPlace
	 */
	public Integer getNbPlace() {
		return nbPlace;
	}
	/**
	 * @param nbPlace the nbPlace to set
	 */
	public void setNbPlace(Integer nbPlace) {
		this.nbPlace = nbPlace;
	}
	/**
	 * @return the sites
	 */
	public List<Site> getSites() {
		return sites;
	}
	/**
	 * @param sites the sites to set
	 */
	public void setSites(List<Site> sites) {
		this.sites = sites;
	}
	/**
	 * @return the idSite
	 */
	public Long getIdSite() {
		return idSite;
	}
	/**
	 * @param idSite the idSite to set
	 */
	public void setIdSite(Long idSite) {
		this.idSite = idSite;
	}


	/**
	 * @return the salle
	 */
	public Salle getSalle() {
		return salle;
	}


	/**
	 * @param salle the salle to set
	 */
	public void setSalle(Salle salle) {
		this.salle = salle;
	}


	/**
	 * @return the salles
	 */
	public List<Salle> getSalles() {
		return salles;
	}


	/**
	 * @param salles the salles to set
	 */
	public void setSalles(List<Salle> salles) {
		this.salles = salles;
	}
	
	
	
	
}
