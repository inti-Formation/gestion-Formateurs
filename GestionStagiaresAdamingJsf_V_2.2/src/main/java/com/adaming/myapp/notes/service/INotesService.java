package com.adaming.myapp.notes.service;

import java.util.List;

import com.adaming.myapp.entities.Note;

public interface INotesService {

	 List<Note> getAllNotes();

	 Note addNoteFinal(Note note, Long idSession, Long idEtudiant,
			Long idModule);

	 List<Note> getNotesBySessionAndModule(Long idSession, Long idMoule);

	 boolean testNoteByEtuAndByModule(Long idSession, Long idModule,
			Long idEtudiant);
	
	 List<Note> getAllNotesByStudent(Long idEtudiant);
	
	 List<Note> getAllNotesBySession(Long idSession);
}
