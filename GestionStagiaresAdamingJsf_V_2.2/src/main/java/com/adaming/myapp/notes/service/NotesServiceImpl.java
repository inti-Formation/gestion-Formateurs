package com.adaming.myapp.notes.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.entities.Module;
import com.adaming.myapp.entities.Note;
import com.adaming.myapp.notes.dao.INotesDao;
@Transactional
public class NotesServiceImpl implements INotesService {
    
	private INotesDao dao;
	
	Logger log = Logger.getLogger("NotesServiceImpl");
	
	
	public void setDao(INotesDao dao) {
		this.dao = dao;
		log.info("<----------- Dao Notes Injected----------->");
	}



	@Override
	public List<Note> getAllNotes() {
		// TODO Auto-generated method stub
		return dao.getAllNotes();
	}

	@Override
	public Note addNoteFinal(Note note, Long idSession, Long idEtudiant,
			Long idModule) {
		// TODO Auto-generated method stub
		return dao.addNoteFinal(note, idSession, idEtudiant, idModule);
	}



	@Override
	public List<Note> getNotesBySessionAndModule(Long idSession, Long idMoule) {
		// TODO Auto-generated method stub
		return dao.getNotesBySessionAndModule(idSession, idMoule);
	}


	

}
