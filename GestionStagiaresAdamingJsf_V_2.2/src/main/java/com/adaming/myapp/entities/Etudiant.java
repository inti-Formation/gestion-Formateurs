package com.adaming.myapp.entities;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.mysql.fabric.xmlrpc.base.Array;
@Entity
public class Etudiant {
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
	private String [] presence = {"P","P","P","P","P","P","P","P","P","P"};
	
	@Transient
	private String [] comportement = {"Veuillez Choisir l'un des Commentaires..........","Niveau technique moyen. Tres bonne progression depuis le debut. Beaucoup de volonté et de serieux malgré quelques difficultés,bonne communication.","Niveau technique satisfaisant.  Sérieux et appliqué.bonne communication.","Niveau technique moyen.  Sérieux et motivé mais doit travailler d'avantage.","Niveau technique satisfaisant.  Personne sérieuse, autonome, motivée  et appliquée,bonne communication,  Parmis  les meilleurs elements.","Niveau technique moyen, motivée, ralleur .","Niveau technique satisfaisant. autonome et motivé mais distrait quelques fois, bonne capacité à comprendre, Parmi les meilleurs de la session.","Niveau technique satisfaisant. Tres bonne progression. Sérieux et bonne communication.","Niveau technique satisfaisant.  Autonome, Parmis  les meilleurs elements de la session,Communique peut.","Niveau technique moyen,  Assez discret,  Sérieux et motivé mais doit travailler d'avantage.","Niveau technique moyen. Tres bonne progression depuis le debut. Beaucoup de volonté et de serieux malgré quelques difficultés,bonne communication.","Niveau technique moyen,Communique peut.","Niveau technique satisfaisant, autonome,  Parmis  les meilleurs elements,bonne communication.","Niveau technique moyen.  Sérieux et motivé mais doit travailler d'avantage."};
	
	@Transient
	private String [] risque = {"Moyen","Faible","Élévé"};
	
	@Transient
	private String [] evaluation = {"Excellent","Très Bien","Moyen","Passable","Trop Juste"};
	
	/*assoc*/
	@ManyToOne
	@JoinColumn(name="ID_SESS_ETUDIANT")
    private SessionEtudiant sessionEtudiant;
	@OneToMany(mappedBy="etudiant",fetch=FetchType.EAGER)
    private List<Examen> examens;

	/*construct*/
	public Etudiant() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	/*construct*/

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

	public List<Examen> getExamens() {
		return examens;
	}

	public void setExamens(List<Examen> examens) {
		this.examens = examens;
	}

	public String[] getPresence() {
		return presence;
	}

	public void setPresence(String[] presence) {
		this.presence = presence;
	}



	public String[] getComportement() {
		return comportement;
	}



	public void setComportement(String[] comportement) {
		this.comportement = comportement;
	}



	public String[] getRisque() {
		return risque;
	}



	public void setRisque(String[] risque) {
		this.risque = risque;
	}



	public String[] getEvaluation() {
		return evaluation;
	}



	public void setEvaluation(String[] evaluation) {
		this.evaluation = evaluation;
	}


	
}
