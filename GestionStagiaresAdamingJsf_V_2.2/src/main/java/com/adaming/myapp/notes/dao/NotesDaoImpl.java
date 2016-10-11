package com.adaming.myapp.notes.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.adaming.myapp.entities.Etudiant;
import com.adaming.myapp.entities.Module;
import com.adaming.myapp.entities.Note;
import com.adaming.myapp.entities.SessionEtudiant;

public class NotesDaoImpl implements INotesDao {

	@PersistenceContext
	private EntityManager em;

	Logger log = Logger.getLogger("NotesDaoImpl");

	@Override
	@SuppressWarnings("unchecked")
	public List<Note> getAllNotes() {
		Query query = em.createQuery("from Note n");
		log.info("Liste des Notes : " + query.getResultList().size());
		return query.getResultList();
	}

	@Override
	public Note addNoteFinal(Note note, Long idSession, Long idEtudiant,
			Long idModule) {
		SessionEtudiant s = em.find(SessionEtudiant.class, idSession);
		note.setSessionEtudiant(s);
		Etudiant e = em.find(Etudiant.class, idEtudiant);
		note.setEtudiant(e);
		Module m = em.find(Module.class, idModule);
		note.setModule(m);

		if (testNoteByEtuAndByModule(idSession, idModule, idEtudiant)) {

			throw new RuntimeException("Examen déjà Réalisé !");
		}

		em.persist(note);
		log.info("la note final de l'etudiant " + idEtudiant
				+ " est ajoutée avec success");

		return note;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Note> getNotesBySessionAndModule(Long idSession, Long idMoule) {
		Query query = em
				.createQuery("from Note n where n.sessionEtudiant.idSession=:x and n.module.idModule=:y");
		query.setParameter("x", idSession);
		query.setParameter("y", idMoule);
		log.info("la liste des notes de la session Numero  " + idSession
				+ " est : " + query.getResultList().size());
		return query.getResultList();
	}

	public boolean testNoteByEtuAndByModule(Long idSession, Long idModule,
			Long idEtudiant) {
		Query query = em
				.createQuery("SELECT count(*) from Note n where n.sessionEtudiant.idSession=:x and n.module.idModule=:y and n.etudiant.idEtudiant=:z");
		query.setParameter("x", idSession);
		query.setParameter("y", idModule);
		query.setParameter("z", idEtudiant);
		Long count = (Long) query.getSingleResult();
		System.out.println("::::::::: Resultat count: " + count);
		if (count == 1) {
			return true;
		}

		return false;

	}

}
