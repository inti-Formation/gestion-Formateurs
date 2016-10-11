package com.adaming.myapp.notes.service;

import java.util.List;

import com.adaming.myapp.entities.Note;

public interface INotesService {

	public List<Note> getAllNotes();

	public Note addNoteFinal(Note note, Long idSession, Long idEtudiant,
			Long idModule);

	public List<Note> getNotesBySessionAndModule(Long idSession, Long idMoule);

	public boolean testNoteByEtuAndByModule(Long idSession, Long idModule,
			Long idEtudiant);
}
