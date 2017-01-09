package com.adaming.myapp.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.adaming.myapp.entities.Formateur;
import com.adaming.myapp.entities.Role;
import com.adaming.myapp.entities.User;
import com.adaming.myapp.exception.VerificationInDataBaseException;
import com.adaming.myapp.formateur.service.IFormateurService;
import com.adaming.myapp.role.service.IRoleService;
import com.adaming.myapp.tools.DataUtil;
import com.adaming.myapp.tools.Filter;
import com.adaming.myapp.tools.GenerateSessionKey;
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
	
	@NotEmpty(message="Veuillez sélectionnez une civilitée")
	private String civilite;
	@NotEmpty
	private String nom;
	@NotEmpty
	private String prenom;
	@NotEmpty
	private String adresse;
	@NotEmpty
	private String codePostal;
	@NotEmpty
	private String telMobile;
	private String mail;
	@NotEmpty
	private String nationalite;
	@NotNull
	private Date dateDeNaissance;
	@NotEmpty
	private String lieuDeNaissance;
	@NotEmpty
	private String specialite;
	
	private Formateur formateur;
	private User user;
	private Role role;
	private String passwordRandom;
	/*methodes*/
	public void addFormateur(){
		/**generate random password*/
		passwordRandom = generateRandomPassword();
		/**@create New Formateur */
		formateur = createFormateur();
		/**@create New User */
		user = createUser(passwordRandom);
		/**@create New Role */
		role = createRole();
		try {
			serviceFormateur.addFormateur(formateur);
			serviceUser.saveUser(user);
			serviceRole.saveRole(role, user.getIdUser());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "le Formateur "+nom+", "+prenom+" à bien été ajoutée avec Success"+" Voici les informations du compte Formateur : "+"Pseudo : "+mail+", Password : "+passwordRandom));
			reset();
		} catch (VerificationInDataBaseException e1) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!",e1.getMessage()));
		}
	}
	/**
	 * @create New Role
	 **@return Object Role 
	 **@factory.create.method
	 */
	private Role createRole() {
		role = FactoryBean.getRoleFactory().create("Role");
		role.setRoleName("ROLE_FORMATEUR");
		return role;
	}
	/**
	 * @create New User
	 * @param passwordRandom
	 * @return Object User 
	 * @factory.create.method
	 */
	private User createUser(String passwordRandom) {
		user=FactoryBean.getUserFactory().create("User");
		user.setName(formateur.getMail());
		user.setPassword(passwordRandom);
		user.setActived(true);
		return user;
	}
	
	
	/**
	 * @create New Formateur
	 * @return Object Formateur 
	 * @factory.create.method
	 */
	private Formateur createFormateur() {
		formateur = FactoryBean.getFormateurFactory().create("Formateur");
		formateur.setCivilite(civilite);
		formateur.setNom(nom);
		formateur.setPrenom(prenom);
		formateur.setAdresse(adresse);
		formateur.setCodePostal(codePostal);
		formateur.setTelMobile(telMobile);
		formateur.setMail(mail);
		formateur.setNationalite(nationalite);
		formateur.setDateDeNaissance(dateDeNaissance);
		formateur.setLieuDeNaissance(lieuDeNaissance);
		formateur.setSpecialite(specialite);
		return formateur;
	}
	
	/**
	 ** @method generateRandomKey, generate random password width length 8
	 ** 
	 */
	private String generateRandomPassword() {
		passwordRandom = GenerateSessionKey.generateRandomKey(8);
		return passwordRandom;
	}
	
	
	/**
	 * @vider les champs aprés l'inscription de chaque formateur
	 * 
	 **/
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
	/**
	 * @Autocomplete pour faire l'autocomplétion
	 * remplir le tableau des spécialité affecter à chauque formateur
	 * 
	 **/
	public List<String> specialitesInfo(String query){
		List<String> specilites = DataUtil.fillingSpecialites(query);
		List<String> filtred = Filter.filterObject(query, specilites);
		return filtred;
	}
	
	/**
	 * @Autocomplete pour faire l'autocomplétion
	 * remplir le tableau des nations affecter à chauque formateur
	 * 
	 **/
	public List<String> getNations(String query){
		List<String> nations = Arrays.asList(DataUtil.fillingNation(query));
		List<String> filtred = Filter.filterObject(query, nations);
		return filtred;
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
