package com.adaming.myapp.etudiant.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.adaming.myapp.entities.Etudiant;
import com.adaming.myapp.entities.SessionEtudiant;
import com.adaming.myapp.exception.AddEtudiantException;

public class EtudiantDaoImpl implements IEtudiantDao {
    
	@PersistenceContext
	private EntityManager em;
	
	Logger log = Logger.getLogger("EtudiantDaoImpl");
	@Override
	public Etudiant addStudent(Etudiant e,Long idSession) throws AddEtudiantException {
		List<Etudiant> tabEtudiant=null;//verifications
		tabEtudiant=getEtudiantBySession(idSession);
		for(Etudiant etudiant:tabEtudiant){
			if(etudiant.getDateDeNaissance().compareTo(e.getDateDeNaissance()) ==0
			&& etudiant.getNomEtudiant().equals(e.getNomEtudiant())){
				throw new AddEtudiantException("L'étudiant "+e.getNomEtudiant()+" Existe déja dans la Session Numéro"+idSession);
			}
		}
		SessionEtudiant s = em.find(SessionEtudiant.class,idSession);
		e.setSessionEtudiant(s);
		s.getEtudiants().add(e);
		em.persist(e);
		log.info("l'etudiant "+e.getIdEtudiant()+" a bien été ajouter");
		return e;
	}

	@Override
	public Etudiant updateStudent(Etudiant e,Long idSession) {
		SessionEtudiant s = em.find(SessionEtudiant.class,idSession);
		e.setSessionEtudiant(s);
		em.merge(e);
		log.info("l'etudiant "+e.getIdEtudiant()+" a bien été modifie");
		return e;
	}

	@Override
	public Etudiant removeStudent(Long idStudent) {
		Etudiant e = em.find(Etudiant.class,idStudent);
		em.remove(e);
		log.info("l'etudiant "+e.getIdEtudiant()+" a bien été supprime");
		return e;
	}

	@Override
	public Etudiant getStudentById(Long idStudent) {
		Etudiant e = em.find(Etudiant.class,idStudent);
		log.info("l'etudiant "+e.getIdEtudiant()+" a bien été recuperer");
		return e;
	}

	@Override
	public List<Etudiant> getEtudiantBySession(Long idSession) {
		List<Etudiant> etudiants=null;
		SessionEtudiant s = em.find(SessionEtudiant.class, idSession);
		etudiants=s.getEtudiants();
		log.info("le nombre des etudiants dans la session N "+s.getIdSession()+"est "+etudiants.size());
		return etudiants;
	}

	

}
