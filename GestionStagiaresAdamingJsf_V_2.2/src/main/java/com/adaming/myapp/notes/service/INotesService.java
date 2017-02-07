package com.adaming.myapp.notes.service;

import java.util.List;

import com.adaming.myapp.entities.Note;
import com.adaming.myapp.exception.VerificationInDataBaseException;

public interface INotesService {

	 List<Note> getAllNotes();

	 Note addNoteFinal(Note note, Long idSession, Long idEtudiant,
			Long idModule) throws VerificationInDataBaseException;

	 List<Object[]> getNotesBySessionAndModule(Long idSession, Long idMoule);

	 boolean testNoteByEtuAndByModule(Long idSession, Long idModule,
			Long idEtudiant);
	
	 List<Note> getAllNotesByStudent(Long idEtudiant);
	
	 List<Object[]> getAllNotesBySession(Long idSession);
	 
	 Double getMoyenne(Long idSession,Long idModule);
}
