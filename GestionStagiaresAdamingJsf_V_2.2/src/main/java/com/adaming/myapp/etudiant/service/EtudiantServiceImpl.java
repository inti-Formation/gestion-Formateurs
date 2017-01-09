package com.adaming.myapp.etudiant.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.entities.Etudiant;
import com.adaming.myapp.etudiant.dao.IEtudiantDao;
import com.adaming.myapp.exception.AddEtudiantException;
import com.adaming.myapp.exception.VerificationInDataBaseException;
/**
 *  @author Adel 
 *  @version 1.0.0
 *  @date 11/10/2016
 *  @Interface dao injection de dépendance dans l'interface IEtudiantService @see app.xml
 *  @param e l'entité etudiant 
 *  @param idSession le type de la relation à associée
 *  @param idStudent l'identifiant de l'entité etudiant
 *  @param mail le mail de l'etudiant
 *  @throws AddEtudiantException vérification dans la base de donnée
 * */
@Transactional(readOnly=true)
public class EtudiantServiceImpl implements IEtudiantService {
    
	
	/**
     * Logger @see java.util.logging.Logger
     */
	final Logger LOGGER = Logger.getLogger("EtudiantServiceImpl");
    
	
   /**
    * @Interface IEtudiantDao @see com.adaming.myapp.etudiant.dao.IEtudiantDao
    **/
	private IEtudiantDao dao;

	public void setDao(IEtudiantDao dao) {
		this.dao = dao;
		LOGGER.info("<-----------Dao Student Injected---------->");
	}
    
	
   /**
	* {@inheritDoc} 
    * @throws VerificationInDataBaseException 
	* @see com.adaming.myapp.etudiant.service.IEtudiantService.addStudent
	**/
	@Override
	@Transactional(readOnly=false)
	public Etudiant addStudent(Etudiant e, Long idSession)
			throws AddEtudiantException {
		List<Etudiant> tabEtudiant = null;// verifications
		tabEtudiant = getStudentsBySession(idSession);
		if(tabEtudiant.size()>0){
			for (Etudiant etudiant : tabEtudiant) {
				if (etudiant.getDateDeNaissance().compareTo(e.getDateDeNaissance()) == 0
						&& etudiant.getNomEtudiant().equals(e.getNomEtudiant())) {
					throw new AddEtudiantException("L'étudiant "
							+ e.getNomEtudiant()
							+ " Existe déja dans la Session N°" + idSession);
				}else if(etudiant.getMail().equals(e.getMail())){
					throw new AddEtudiantException("l'adresse mail "+etudiant.getMail()+" existe déjà dans la sesion N° "+idSession+" Veuillez renseigner une autre adresse mail");
				}
			}
		}
		return dao.addStudent(e, idSession);
	}
   
	
	/**
	 * {@inheritDoc} 
	 * @see com.adaming.myapp.etudiant.service.IEtudiantService.updateStudent
	 **/
	@Override
	@Transactional(readOnly=false)
	public Etudiant updateStudent(Etudiant e, Long idSession) {
		// TODO Auto-generated method stub
		return dao.updateStudent(e, idSession);
	}
   
	
	
	/**
	 * {@inheritDoc} 
	 * @see com.adaming.myapp.etudiant.service.IEtudiantService.removeStudent
	 **/
	@Override
	@Transactional(readOnly=false)
	public Etudiant removeStudent(Long idStudent) {
		// TODO Auto-generated method stub
		return dao.removeStudent(idStudent);
	}
    
	
	
	/**
	 * {@inheritDoc} 
	 * @see com.adaming.myapp.etudiant.service.IEtudiantService.getStudentById
	 **/
	@Override
	public Etudiant getStudentById(Long idStudent) {
		// TODO Auto-generated method stub
		return dao.getStudentById(idStudent);
	}
    
	
	
	/**
	 * {@inheritDoc} 
	 * @throws VerificationInDataBaseException 
	 * @see com.adaming.myapp.etudiant.service.IEtudiantService.getEtudiantBySession
	 **/
	@Override
	public List<Etudiant> getEtudiantBySession(Long idSession) throws VerificationInDataBaseException {
		// TODO Auto-generated method stub
		return dao.getEtudiantBySession(idSession);
	}
    
	
	
	/**
	 * {@inheritDoc} 
	 * @see com.adaming.myapp.etudiant.service.IEtudiantService.getEtudiant
	 **/
	@Override
	public Etudiant getEtudiant(String mail) {
		// TODO Auto-generated method stub
		return dao.getEtudiant(mail);
	}


	@Override
	public List<Etudiant> getStudentsBySession(Long idSession) {
		// TODO Auto-generated method stub
		return dao.getStudentsBySession(idSession);
	}

}
