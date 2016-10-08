package com.adaming.myapp.entities;

import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.time.DateUtils;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_EVENEMENT",discriminatorType=DiscriminatorType.STRING)
public class Evenement {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idEvenement;
	private Date startDate;
	private Date endDate;
	@Temporal(TemporalType.DATE)
	private Date curentDate = new Date();

	/*assoc*/
	@ManyToOne
	@JoinColumn(name="ID_EVE_ETUDIANT")
	private Etudiant etudiant;
	@ManyToOne
	@JoinColumn(name="ID_EVE_SESSION")
	private SessionEtudiant sessionEtudiant;
	
	public Evenement() {
		// TODO Auto-generated constructor stub
	}

	public Evenement(Date startDate, Date endDate,Date currentDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.curentDate=currentDate;
	}


	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Long getIdEvenement() {
		return idEvenement;
	}

	public void setIdEvenement(Long idEvenement) {
		this.idEvenement = idEvenement;
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

	public Date getCurentDate() {
		return curentDate;
	}

	public void setCurentDate(Date curentDate) {
		this.curentDate = curentDate;
	}

	
	
}
