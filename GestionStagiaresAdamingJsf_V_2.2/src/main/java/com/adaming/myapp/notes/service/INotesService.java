package com.adaming.myapp.notes.service;

import java.util.List;

import com.adaming.myapp.entities.Examen;
import com.adaming.myapp.entities.Module;
import com.adaming.myapp.entities.Note;
import com.adaming.myapp.exception.AddNoteException;


public interface INotesService {

	public List<Note> getAllNotes();
	
	public Note addNoteFinal(Note note, Long idSession,Long idEtudiant,Long idModule);
	
	public List<Note> getNotesBySessionAndModule (Long idSession,Long idMoule);
}
