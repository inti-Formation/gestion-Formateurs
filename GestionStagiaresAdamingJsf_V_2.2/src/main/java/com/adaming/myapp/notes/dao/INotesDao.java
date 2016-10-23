package com.adaming.myapp.notes.dao;

import java.util.List;

import com.adaming.myapp.entities.Note;

public interface INotesDao {

	public Note addNoteFinal(Note note, Long idSession, Long idEtudiant,
			Long idModule);

	public List<Note> getAllNotes();

	public List<Note> getNotesBySessionAndModule(Long idSession, Long idMoule);

	public boolean testNoteByEtuAndByModule(Long idSession, Long idModule,
			Long idEtudiant);
	
	public List<Note> getAllNotesByStudent(Long idEtudiant);
	
	public List<Note> getAllNotesBySession(Long idSession);
	
	
}
