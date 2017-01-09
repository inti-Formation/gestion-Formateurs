package com.adaming.myapp.entities;

import javax.persistence.Embeddable;

@Embeddable
public class Adresse {

	private String adresse;
	private String ville;
	private String codePostal;
	private String pays;
	
	public Adresse() {
		// TODO Auto-generated constructor stub
	}

	public Adresse(String adresse, String ville, String codePostal, String pays) {
		super();
		this.adresse = adresse;
		this.ville = ville;
		this.codePostal = codePostal;
		this.pays = pays;
	}

	/**
	 * @return the adresse
	 */
	public String getAdresse() {
		return adresse;
	}

	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	/**
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}

	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}

	/**
	 * @return the codePostale
	 */
	public String getCodePostal() {
		return codePostal;
	}

	/**
	 * @param codePostale the codePostale to set
	 */
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	/**
	 * @return the pays
	 */
	public String getPays() {
		return pays;
	}

	/**
	 * @param pays the pays to set
	 */
	public void setPays(String pays) {
		this.pays = pays;
	}
	
	
}
