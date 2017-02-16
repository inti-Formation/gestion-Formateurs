package com.adaming.myapp.notes.service;

import java.util.List;

import com.adaming.myapp.entities.Note;
import com.adaming.myapp.exception.VerificationInDataBaseException;

public interface INotesService {

	 List<Note> getAllNotes();

	 Note addNoteFinal(final Note note, final Long idSession, final Long idEtudiant,
			Long idModule) throws VerificationInDataBaseException;

	 List<Object[]> getNotesBySessionAndModule(final Long idSession, final Long idMoule);

	 boolean testNoteByEtuAndByModule(final Long idSession, final Long idModule,
			Long idEtudiant);
	
	 List<Note> getAllNotesByStudent(final Long idEtudiant);
	
	 List<Object[]> getAllNotesBySession(final Long idSession);
	 
	 Double getMoyenne(final Long idSession,final Long idModule);
}
