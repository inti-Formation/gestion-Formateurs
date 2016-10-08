package com.adaming.myapp.etudiant.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.entities.Etudiant;
import com.adaming.myapp.entities.Evenement;
import com.adaming.myapp.etudiant.dao.IEtudiantDao;
import com.adaming.myapp.exception.AddEtudiantException;
@Transactional
public class EtudiantServiceImpl implements IEtudiantService{
   
	Logger log = Logger.getLogger("EtudiantServiceImpl");
	
	private IEtudiantDao dao;
	
	
	public void setDao(IEtudiantDao dao) {
		this.dao = dao;
		log.info("<-----------Dao Student Injected---------->");
	}

	@Override
	public Etudiant addStudent(Etudiant e,Long idSession) throws AddEtudiantException {
		// TODO Auto-generated method stub
		return dao.addStudent(e,idSession);
	}

	@Override
	public Etudiant updateStudent(Etudiant e,Long idSession) {
		// TODO Auto-generated method stub
		return dao.updateStudent(e,idSession);
	}

	@Override
	public Etudiant removeStudent(Long idStudent) {
		// TODO Auto-generated method stub
		return dao.removeStudent(idStudent);
	}

	@Override
	public Etudiant getStudentById(Long idStudent) {
		// TODO Auto-generated method stub
		return dao.getStudentById(idStudent);
	}

	@Override
	public List<Etudiant> getEtudiantBySession(Long idSession) {
		// TODO Auto-generated method stub
		return dao.getEtudiantBySession(idSession);
	}

	

}
