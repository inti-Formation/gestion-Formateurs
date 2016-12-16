package com.adaming.myapp.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
/*@Author Adel
 *@version 1.0.0
 *@date 09/10/2016
 *
 * */
@SuppressWarnings("serial")
@Entity
public class Etudiant implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idEtudiant;
	private String nomEtudiant;
	private String prenomEtudiant;
	private Date dateDeNaissance;
	private String formationInitial;
	private String ecole;
	private Date dateObtention;
	private String adressePostal;
	private String codePostal;
	private String numTel;
	private String mail;
	
	@Transient
	private  String [] presence = {"OK","OK","OK","OK","OK","OK","OK","OK","OK","OK"};
	
	@Transient
	private final String [] comportement = {"Veuillez Choisir l'un des Commentaires..........","Niveau technique moyen. Tres bonne progression depuis le debut. Beaucoup de volonté et de serieux malgré quelques difficultés,bonne communication.","Niveau technique satisfaisant.  Sérieux et appliqué.bonne communication.","Niveau technique moyen.  Sérieux et motivé mais doit travailler d'avantage.","Niveau technique satisfaisant.  Personne sérieuse, autonome, motivée  et appliquée,bonne communication,  Parmis  les meilleurs elements.","Niveau technique moyen, motivée, ralleur .","Niveau technique satisfaisant. autonome et motivé mais distrait quelques fois, bonne capacité à comprendre, Parmi les meilleurs de la session.","Niveau technique satisfaisant. Tres bonne progression. Sérieux et bonne communication.","Niveau technique satisfaisant.  Autonome, Parmis  les meilleurs elements de la session,Communique peut.","Niveau technique moyen,  Assez discret,  Sérieux et motivé mais doit travailler d'avantage.","Niveau technique moyen. Tres bonne progression depuis le debut. Beaucoup de volonté et de serieux malgré quelques difficultés,bonne communication.","Niveau technique moyen,Communique peut.","Niveau technique satisfaisant, autonome,  Parmis  les meilleurs elements,bonne communication.","Niveau technique moyen.  Sérieux et motivé mais doit travailler d'avantage."};
	
	@Transient
	private final String [] risque = {"Veuillez Choisir un Niveau","Moyen","Faible","Élévé"};
	
	@Transient
	private final String [][] evaluation ={{"Veuillez Choisir un Niveau","Excellent","Très Bien","Moyen","Passable","Trop Juste"},{"Veuillez Choisir un Niveau","Excellent","Très Bien","Moyen","Passable","Trop Juste"},{"Veuillez Choisir un Niveau","Excellent","Très Bien","Moyen","Passable","Trop Juste"}} ;
	
	/*assoc*/
	@ManyToOne
	@JoinColumn(name="ID_SESS_ETUDIANT")
    private SessionEtudiant sessionEtudiant;
	@OneToMany(mappedBy="etudiant",fetch=FetchType.EAGER)
	private List<Note> notes;
	@OneToMany(mappedBy="etudiant",fetch=FetchType.EAGER)
	private List<Evenement> evenements;

	/*construct*/
	public Etudiant() {
		// TODO Auto-generated constructor stub
	}

	
	/*construct avec param */

	public Etudiant(String nomEtudiant, String prenomEtudiant,
			Date dateDeNaissance, String formationInitial, String ecole,
			Date dateObtention, String adressePostal, String codePostal,
			String numTel, String mail) {
		super();
		this.nomEtudiant = nomEtudiant;
		this.prenomEtudiant = prenomEtudiant;
		this.dateDeNaissance = dateDeNaissance;
		this.formationInitial = formationInitial;
		this.ecole = ecole;
		this.dateObtention = dateObtention;
		this.adressePostal = adressePostal;
		this.codePostal = codePostal;
		this.numTel = numTel;
		this.mail = mail;
	}
	
	/*get and set */

	public Long getIdEtudiant() {
		return idEtudiant;
	}



	public void setIdEtudiant(Long idEtudiant) {
		this.idEtudiant = idEtudiant;
	}



	public String getNomEtudiant() {
		return nomEtudiant;
	}



	public void setNomEtudiant(String nomEtudiant) {
		this.nomEtudiant = nomEtudiant;
	}



	public String getPrenomEtudiant() {
		return prenomEtudiant;
	}



	public void setPrenomEtudiant(String prenomEtudiant) {
		this.prenomEtudiant = prenomEtudiant;
	}



	public Date getDateDeNaissance() {
		return dateDeNaissance;
	}



	public void setDateDeNaissance(Date dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}



	public String getFormationInitial() {
		return formationInitial;
	}



	public void setFormationInitial(String formationInitial) {
		this.formationInitial = formationInitial;
	}



	public String getEcole() {
		return ecole;
	}



	public void setEcole(String ecole) {
		this.ecole = ecole;
	}



	public Date getDateObtention() {
		return dateObtention;
	}



	public void setDateObtention(Date dateObtention) {
		this.dateObtention = dateObtention;
	}



	public String getAdressePostal() {
		return adressePostal;
	}



	public void setAdressePostal(String adressePostal) {
		this.adressePostal = adressePostal;
	}



	public String getCodePostal() {
		return codePostal;
	}



	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}



	public String getNumTel() {
		return numTel;
	}



	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}



	public String getMail() {
		return mail;
	}



	public void setMail(String mail) {
		this.mail = mail;
	}



	public SessionEtudiant getSessionEtudiant() {
		return sessionEtudiant;
	}



	public void setSessionEtudiant(SessionEtudiant sessionEtudiant) {
		this.sessionEtudiant = sessionEtudiant;
	}



	public List<Note> getNotes() {
		return notes;
	}



	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}



	public String[] getPresence() {
		return presence;
	}



	public String[] getComportement() {
		return comportement;
	}



	public String[] getRisque() {
		return risque;
	}



	public String[][] getEvaluation() {
		return evaluation;
	}


	public List<Evenement> getEvenements() {
		return evenements;
	}


	public void setEvenements(List<Evenement> evenements) {
		this.evenements = evenements;
	}


	public void setPresence(String[] presence) {
		this.presence = presence;
	}


	
}
