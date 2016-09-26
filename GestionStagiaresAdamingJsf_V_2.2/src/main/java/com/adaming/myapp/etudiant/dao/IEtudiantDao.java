package com.adaming.myapp.etudiant.dao;

import java.util.List;

import com.adaming.myapp.entities.Etudiant;
import com.adaming.myapp.exception.AddEtudiantException;

public interface IEtudiantDao {

	public Etudiant addStudent(Etudiant e,Long idSession) throws AddEtudiantException;
	
	public Etudiant updateStudent(Etudiant e,Long idSession);
	
	public Etudiant removeStudent(Long idStudent);
	
	public Etudiant getStudentById(Long idStudent);
	
	public List<Etudiant> getEtudiantBySession(Long idSession);

	
}
