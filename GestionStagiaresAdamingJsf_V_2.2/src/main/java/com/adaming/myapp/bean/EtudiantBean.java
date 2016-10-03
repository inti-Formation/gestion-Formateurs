package com.adaming.myapp.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.adaming.myapp.entities.Etudiant;
import com.adaming.myapp.entities.Role;
import com.adaming.myapp.entities.SessionEtudiant;
import com.adaming.myapp.entities.Specialite;
import com.adaming.myapp.entities.User;
import com.adaming.myapp.etudiant.service.IEtudiantService;
import com.adaming.myapp.exception.AddEtudiantException;
import com.adaming.myapp.role.service.IRoleService;
import com.adaming.myapp.session.service.ISessionService;
import com.adaming.myapp.user.service.IUserService;

@Component("etudiantBean")
@ViewScoped
public class EtudiantBean implements Serializable {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -6057529050023345627L;

	/**
	 * 
	 */


	@Inject
	private IEtudiantService serviceEtudiant;
	
	@Inject
	private ISessionService serviceSession;
	
	@Inject
	private IUserService serviceUser;
	
	@Inject
	private IRoleService serviceRole;
	
	
	private Long idSession;
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
	private String success;
	private String addEtudiantException;
	private List<SessionEtudiant> sessionsEncours;
	private List<SessionEtudiant> allSessions;
	private List<Etudiant> etudiants;
	private Specialite specialite;
	private Etudiant etudiant;
	
	/* add etudiant */
	public void addEtudiant(){
		/*generate random password*/
		String passwordRandom = serviceUser.generateSessionKey(8);
		//new Etudiant
		Etudiant e = new Etudiant(nomEtudiant, prenomEtudiant, dateDeNaissance, formationInitial, ecole, dateObtention, adressePostal, codePostal, numTel, mail);
		// new User
		User u     = new User(mail,passwordRandom, true);
		// new Role
		Role r = new Role("ROLE_ETUDIANT");
		try {
			serviceEtudiant.addStudent(e, idSession);
			serviceUser.saveUser(u);
			serviceRole.saveRole(r, u.getIdUser());
			setSuccess("l'Etudiant "+nomEtudiant+", "+prenomEtudiant+" à bien été ajoutée avec Success"+" Voici les informations du compte etudiant : "+"Pseudo : "+mail+", Password : "+passwordRandom);
			setAddEtudiantException("");
		} catch (AddEtudiantException e1) {
			setAddEtudiantException(e1.getMessage());
			setSuccess("");
		}
		
	}
	
	/*get all sessions in progress and getAllSessions
	 *  evry load page*/
	public void init(){
		sessionsEncours  = serviceSession.getAllSessionsInProgress();
        allSessions      = serviceSession.getAllSessions();
        // show statut of session finish or in progress
        Date curentDate= new Date();
		for(SessionEtudiant s:allSessions){
			if(s.getDateFin().getTime()<=curentDate.getTime()){
				s.setEtatSession("TERMINE");
			}else{
				s.setEtatSession("EN COURS");
			}
		}
	}
	/*@method get etudiant */
	public void getCurrentEtudiant(Long idEtudiant){
		etudiant=serviceEtudiant.getStudentById(idEtudiant);
	}
	/*@methode update etudiant*/
	public String edit(){
		serviceEtudiant.updateStudent(etudiant, idSession);
		return "etudiant_update_success?redirect=true";
	}
	
	public void getAllStudentsBySession(){
		etudiants = serviceEtudiant.getEtudiantBySession(idSession);
	}
	
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
	public Long getIdSession() {
		return idSession;
	}
	public void setIdSession(Long idSession) {
		this.idSession = idSession;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getAddEtudiantException() {
		return addEtudiantException;
	}
	public void setAddEtudiantException(String addEtudiantException) {
		this.addEtudiantException = addEtudiantException;
	}
	public List<SessionEtudiant> getSessionsEncours() {
		return sessionsEncours;
	}
	public void setSessionsEncours(List<SessionEtudiant> sessionsEncours) {
		this.sessionsEncours = sessionsEncours;
	}

	public List<Etudiant> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(List<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}

	public Specialite getSpecialite() {
		return specialite;
	}

	public void setSpecialite(Specialite specialite) {
		this.specialite = specialite;
	}

	public List<SessionEtudiant> getAllSessions() {
		return allSessions;
	}

	public void setAllSessions(List<SessionEtudiant> allSessions) {
		this.allSessions = allSessions;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}
	

}
