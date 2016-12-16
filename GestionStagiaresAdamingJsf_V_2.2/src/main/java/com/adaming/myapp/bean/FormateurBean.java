package com.adaming.myapp.bean;

import java.io.Serializable;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.adaming.myapp.entities.Formateur;
import com.adaming.myapp.entities.Role;
import com.adaming.myapp.entities.User;
import com.adaming.myapp.exception.VerificationInDataBaseException;
import com.adaming.myapp.formateur.service.IFormateurService;
import com.adaming.myapp.role.service.IRoleService;
import com.adaming.myapp.user.service.IUserService;
@SuppressWarnings("serial")
@Component("formateurBean")
@Scope(value="request")
public class FormateurBean implements Serializable{
    
	
	@Inject
	private IFormateurService serviceFormateur;

	@Inject
	private IUserService serviceUser;
	@Inject
	private IRoleService serviceRole;
	
	private String civilite;
	private String nom;
	private String prenom;
	private String adresse;
	private String codePostal;
	private String telMobile;
	private String mail;
	private String nationalite;
	private Date dateDeNaissance;
	private String lieuDeNaissance;
	private String specialite;
	
	
	/*methodes*/
	
	public void addFormateur(){
		/*generate random password*/
		String passwordRandom = serviceUser.generateSessionKey(8);
		
		Formateur formateur = new Formateur(civilite, nom, prenom, adresse, codePostal, telMobile, mail, nationalite, dateDeNaissance, lieuDeNaissance, specialite);
	     
		// new User
		User u     = new User(mail,passwordRandom, true);
		// new Role
		Role r = new Role("ROLE_FORMATEUR");
		
		try {
			serviceFormateur.addFormateur(formateur);
			serviceUser.saveUser(u);
			serviceRole.saveRole(r, u.getIdUser());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "le Formateur "+nom+", "+prenom+" à bien été ajoutée avec Success"+" Voici les informations du compte Formateur : "+"Pseudo : "+mail+", Password : "+passwordRandom));
			reset();
		} catch (VerificationInDataBaseException e1) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!",e1.getMessage()));
		}
	}
	/*vider les champs aprés l'inscription de chaque formateur*/
	public void reset(){
		civilite="";
		nom="";
		prenom="";
		adresse="";
		codePostal="";
		telMobile="";
		mail="";
		nationalite="";
		dateDeNaissance=null;
		lieuDeNaissance="";
		specialite="";
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getTelMobile() {
		return telMobile;
	}
	public void setTelMobile(String telMobile) {
		this.telMobile = telMobile;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getNationalite() {
		return nationalite;
	}
	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}
	public Date getDateDeNaissance() {
		return dateDeNaissance;
	}
	public void setDateDeNaissance(Date dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}
	public String getLieuDeNaissance() {
		return lieuDeNaissance;
	}
	public void setLieuDeNaissance(String lieuDeNaissance) {
		this.lieuDeNaissance = lieuDeNaissance;
	}
	public String getSpecialite() {
		return specialite;
	}
	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}
	


	public String getCivilite() {
		return civilite;
	}


	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}


}
