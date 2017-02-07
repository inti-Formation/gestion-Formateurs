package com.adaming.myapp.notes.dao;

import java.util.List;

import com.adaming.myapp.entities.Note;

public interface INotesDao {

	 Note addNoteFinal(Note note, Long idSession, Long idEtudiant,
			Long idModule);

	 List<Note> getAllNotes();

	 List<Object[]> getNotesBySessionAndModule(Long idSession, Long idMoule);

	 boolean testNoteByEtuAndByModule(Long idSession, Long idModule,
			Long idEtudiant);
	
	 List<Note> getAllNotesByStudent(Long idEtudiant);
	
	 List<Object[]> getAllNotesBySession(Long idSession);
	 
	 Double getMoyenne(Long idSession,Long idModule);
	
	
}
