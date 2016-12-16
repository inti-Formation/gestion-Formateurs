package com.adaming.myapp.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class SessionEtudiant implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idSession;
    @Temporal(TemporalType.DATE)
	private Date dateDebute;
    @Temporal(TemporalType.DATE)
	private Date dateFin;
	private String lieu;
	@Transient
	private String etatSession;
	@Transient
	private String dateDebuteInDays;
	@Transient
	private String dateFinInDays;
	/*association*/
	@ManyToOne
	@JoinColumn(name="ID_SPEC_SESSION")
	private Specialite specialite;

	@OneToMany(mappedBy="sessionEtudiant")
	private List<Etudiant> etudiants;
	
	@ManyToMany(mappedBy="sessionsEtudiant")
	private List<Formateur> formateurs;
	@OneToMany(mappedBy="sessionEtudiant")
	private List<Note> notes;
	
	/*construct*/
    public SessionEtudiant() {
		// TODO Auto-generated constructor stub
	}

	public SessionEtudiant(Date dateDebute, Date dateFin, String lieu) {
		super();
		this.dateDebute = dateDebute;
		this.dateFin = dateFin;
		this.lieu = lieu;
	}

	public Long getIdSession() {
		return idSession;
	}

	public void setIdSession(Long idSession) {
		this.idSession = idSession;
	}

	public Date getDateDebute() {
		return dateDebute;
	}

	public void setDateDebute(Date dateDebute) {
		this.dateDebute = dateDebute;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public Specialite getSpecialite() {
		return specialite;
	}

	public void setSpecialite(Specialite specialite) {
		this.specialite = specialite;
	}

	

	public List<Etudiant> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(List<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}

	public String getEtatSession() {
		return etatSession;
	}

	public void setEtatSession(String etatSession) {
		this.etatSession = etatSession;
	}

	public String getDateDebuteInDays() {
		return dateDebuteInDays;
	}

	public void setDateDebuteInDays(String dateDebuteInDays) {
		this.dateDebuteInDays = dateDebuteInDays;
	}

	public String getDateFinInDays() {
		return dateFinInDays;
	}

	public void setDateFinInDays(String dateFinInDays) {
		this.dateFinInDays = dateFinInDays;
	}

	public List<Formateur> getFormateurs() {
		return formateurs;
	}

	public void setFormateurs(List<Formateur> formateurs) {
		this.formateurs = formateurs;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "SessionEtudiant [idSession=" + idSession + ", dateDebute="
				+ dateDebute + ", dateFin=" + dateFin + ", lieu=" + lieu
				+ ", etatSession=" + etatSession + ", dateDebuteInDays="
				+ dateDebuteInDays + ", dateFinInDays=" + dateFinInDays + "]";
	}

	
	
}
