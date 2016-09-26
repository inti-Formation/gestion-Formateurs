package com.adaming.myapp.examen.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.adaming.myapp.entities.Etudiant;
import com.adaming.myapp.entities.Examen;
import com.adaming.myapp.entities.Module;
import com.adaming.myapp.entities.SessionEtudiant;

public class ExamenDaoImpl implements IExamenDao {

	@PersistenceContext
	private EntityManager em;

	Logger log = Logger.getLogger("ExamenDaoImpl");

	

	@Override
	public Object getScoreExamen(Long idSession, Long idEtudiant, Long idModule) {
		Query query = em.createQuery("select sum(e.note)from Examen e where e.sessionEtudaint.idSession=:x and e.etudiant.idEtudiant=:y and e.module.idModule=:z ");
		query.setParameter("x",idSession);
		query.setParameter("y",idEtudiant);
		query.setParameter("z",idModule);
		log.info("la note de letudiant: "+idEtudiant+" pour le module "+idModule+" est : "+query.getSingleResult());
		return query.getSingleResult();
	}

	@Override
	public List<Examen> getAllExamensBySession(Long idSession) {
		List<Examen> examens = new ArrayList<Examen>();
		SessionEtudiant session = em.find(SessionEtudiant.class, idSession);
		examens = session.getExamens();
		log.info("le size de la liste des examens par session "
				+ examens.size());
		return examens;
	}

	@Override
	public List<Examen> getAllExamenByEtudiant(Long idEtudiant) {
		List<Examen> examens = new ArrayList<Examen>();
		Etudiant e = em.find(Etudiant.class, idEtudiant);
		examens = e.getExamens();
		log.info("la liste de l'examen de letudiant" + e.getIdEtudiant());
		return examens;
	}
	
	

	@Override
	public Examen addExamenV2(Examen ex, Long idEtudiant, Long idSession,
			Long idModule) {
		Etudiant e = em.find(Etudiant.class, idEtudiant);
		ex.setEtudiant(e);
		SessionEtudiant s = em.find(SessionEtudiant.class, idSession);
		ex.setSessionEtudaint(s);
		Module m = em.find(Module.class, idModule);
		ex.setModule(m);
		String reponseSelectionne = ex.getReponseSelectionnee();
		System.out.println("reponse selectionnee init : "+reponseSelectionne);
		Double noteObtenu = new Double(0.0);
		System.out.println("init Note Obtenu:" + noteObtenu);
			if(reponseSelectionne.equals("bonne")){
						noteObtenu = noteObtenu + 1;
			}
		ex.setNote(noteObtenu);
		System.out.println("note Final dans l'examens :" + ex.getNote());
		em.persist(ex);
		log.info("lexamens  bien été ajouter avec infos suivants" + ""
				+ "idEtudiant: " + idEtudiant + "idSession:" + idSession
				+ "IdModule : " + idModule + "response envoyer:"
				+ ex.getReponseSelectionnee());
		return ex;
	}
}
