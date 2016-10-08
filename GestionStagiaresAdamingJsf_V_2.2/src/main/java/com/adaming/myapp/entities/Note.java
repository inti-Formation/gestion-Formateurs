package com.adaming.myapp.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.adaming.myapp.entities.Etudiant;
import com.adaming.myapp.entities.Module;
import com.adaming.myapp.entities.SessionEtudiant;

@Entity
public class Note implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idNote;
	private Double score;
	@OneToOne
	@JoinColumn(name="ID_ETU_NOTE")
	private Etudiant etudiant;
	@OneToOne
	@JoinColumn(name="ID_SES_NOTE")
	private SessionEtudiant sessionEtudiant;
	@OneToOne
	@JoinColumn(name="ID_MOD_NOTE")
	private Module module;
	
	
	public Note() {
		// TODO Auto-generated constructor stub
	}

	public Note(Object score) {
		super();
		this.score = (Double) score;
	}



	public Long getIdNote() {
		return idNote;
	}


	public void setIdNote(Long idNote) {
		this.idNote = idNote;
	}


	public Double getScore() {
		return score;
	}


	public void setScore(Double score) {
		this.score = score;
	}


	public Etudiant getEtudiant() {
		return etudiant;
	}


	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}


	public SessionEtudiant getSessionEtudiant() {
		return sessionEtudiant;
	}


	public void setSessionEtudiant(SessionEtudiant sessionEtudiant) {
		this.sessionEtudiant = sessionEtudiant;
	}


	public Module getModule() {
		return module;
	}


	public void setModule(Module module) {
		this.module = module;
	}

}
