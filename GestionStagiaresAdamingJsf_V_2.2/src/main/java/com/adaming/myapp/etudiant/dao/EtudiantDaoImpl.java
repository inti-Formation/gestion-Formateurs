package com.adaming.myapp.etudiant.dao;
import java.util.List;
import com.adaming.myapp.entities.Etudiant;
import com.adaming.myapp.exception.AddEtudiantException;
/*
 *  @author Adel 
 *  @version 1.0.0
 *  @date 11/10/2016
 *  @param e l'entit� etudiant 
 *  @param idSession le type de la relation � associ�e
 *  @param idStudent l'identifiant de l'entit� etudiant
 *  @param mail le mail de l'etudiant
 *  @throws AddEtudiantException v�rification dans la base de donn�e
 *  <p>cette class h�rite de la class EtudiantAbstractJpa
	 *  @see com.adaming.myapp.etudiant.dao.EtudiantAbstractJpa 
	 *  et impl�mente l'interface IEtudiantDao
 *  </p>
 * */

public class EtudiantDaoImpl extends EtudiantAbstractJpa implements IEtudiantDao {

   /*
	* {@inheritDoc} 
	* @see com.adaming.myapp.etudiant.dao.IEtudiantDao.addStudent
	* @see com.adaming.myapp.etudiant.dao.EtudiantAbstractJpa.addStudentAbstractJpa
    **/
	@Override
	public Etudiant addStudent(Etudiant e, Long idSession)
			throws AddEtudiantException {
		return addStudentAbstractJpa(e, idSession);
	}
	
	
	
	
	/*
	* {@inheritDoc} 
	* @see com.adaming.myapp.etudiant.dao.IEtudiantDao.updateStudent
	* @see com.adaming.myapp.etudiant.dao.EtudiantAbstractJpa.updateStudentAbstractJpa
    **/
	@Override
	public Etudiant updateStudent(Etudiant e, Long idSession) {
		return updateStudentAbstractJpa(e, idSession);
	}
	
	
	
	
	
	/*
	* {@inheritDoc} 
	* @see com.adaming.myapp.etudiant.dao.IEtudiantDao.removeStudent
	* @see com.adaming.myapp.etudiant.dao.EtudiantAbstractJpa.removeStudentAbstractJpa
    **/
	@Override
	public Etudiant removeStudent(Long idStudent) {
		return removeStudentAbstractJpa(idStudent);
	}
    
	
	
	
	/*
	* {@inheritDoc} 
	* @see com.adaming.myapp.etudiant.dao.IEtudiantDao.getStudentById
	* @see com.adaming.myapp.etudiant.dao.EtudiantAbstractJpa.getStudentByIdAbstractJpa
    **/
	@Override
	public Etudiant getStudentById(Long idStudent) {
		return getStudentByIdAbstractJpa(idStudent);
	}
    
	
	
	
	
	/*
	* {@inheritDoc} 
	* @see com.adaming.myapp.etudiant.dao.IEtudiantDao.getEtudiantBySession
	* @see com.adaming.myapp.etudiant.dao.EtudiantAbstractJpa.getEtudiantBySessionAbstractJpa
    **/
	@Override
	public List<Etudiant> getEtudiantBySession(Long idSession) {
		return getEtudiantBySessionAbstractJpa(idSession);
	}
    
	
	
	
	
	/*
	* {@inheritDoc} 
	* @see com.adaming.myapp.etudiant.dao.IEtudiantDao.getEtudiant
	* @see com.adaming.myapp.etudiant.dao.EtudiantAbstractJpa.getEtudiantAbstractJpa
    **/
	@Override
	public Etudiant getEtudiant(String mail) {
		return getEtudiantAbstractJpa(mail);
	}

}
