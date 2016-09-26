package com.adaming.myapp.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Examen {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idExamen;
	private Date dateExamen;
	private Double note;
	@Transient
	private String reponseSelectionnee;


	/*assoc*/
	@ManyToOne
	@JoinColumn(name="ID_ET_EXAMEN")
	private Etudiant etudiant;
	
	@ManyToOne
	@JoinColumn(name="ID_SES_EXAMEN")
	private SessionEtudiant sessionEtudaint;
	
	@OneToOne
	@JoinColumn(name="ID_MOD_EXAMEN")
	private Module module;
   
	/*construct*/
	public Examen() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Examen(Date dateExamen, Double note, String reponseSelectionnee) {
		super();
		this.dateExamen = dateExamen;
		this.note = note;
		this.reponseSelectionnee = reponseSelectionnee;
	}

	public Long getIdExamen() {
		return idExamen;
	}

	public void setIdExamen(Long idExamen) {
		this.idExamen = idExamen;
	}

	public Date getDateExamen() {
		return dateExamen;
	}

	public void setDateExamen(Date dateExamen) {
		this.dateExamen = dateExamen;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public SessionEtudiant getSessionEtudaint() {
		return sessionEtudaint;
	}

	public void setSessionEtudaint(SessionEtudiant sessionEtudaint) {
		this.sessionEtudaint = sessionEtudaint;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public Double getNote() {
		return note;
	}

	public void setNote(Double note) {
		this.note = note;
	}

	public String getReponseSelectionnee() {
		return reponseSelectionnee;
	}

	public void setReponseSelectionnee(String reponseSelectionnee) {
		this.reponseSelectionnee = reponseSelectionnee;
	}


}
