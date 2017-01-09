package com.adaming.myapp.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@SuppressWarnings("serial")
@Entity
public class Site implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idSite;
	private String nomSite;
	@Embedded
	private Adresse adresse;

	public Site() {
		// TODO Auto-generated constructor stub
	}

	public Site(String nomSite, Adresse adresse) {
		super();
		this.nomSite = nomSite;
		this.adresse = adresse;
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
	 * @return the nomSite
	 */
	public String getNomSite() {
		return nomSite;
	}

	/**
	 * @param nomSite the nomSite to set
	 */
	public void setNomSite(String nomSite) {
		this.nomSite = nomSite;
	}

	/**
	 * @return the adresse
	 */
	public Adresse getAdresse() {
		return adresse;
	}

	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Site [idSite=" + idSite + ", nomSite=" + nomSite + ", adresse="
				+ adresse + "]";
	}
	
	
	
}
