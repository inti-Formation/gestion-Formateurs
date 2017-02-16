package com.adaming.myapp.etudiant.service;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.entities.Etudiant;
import com.adaming.myapp.etudiant.dao.IEtudiantDao;
import com.adaming.myapp.exception.AddEtudiantException;
import com.adaming.myapp.exception.VerificationInDataBaseException;
import com.adaming.myapp.tools.LoggerConfig;
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
	
    
	
   /**
    * @Interface IEtudiantDao @see com.adaming.myapp.etudiant.dao.IEtudiantDao
    **/
	private IEtudiantDao dao;

	public void setDao(IEtudiantDao dao) {
		this.dao = dao;
		LoggerConfig.logInfo("<-----------Dao Student Injected---------->");
	}
    
	
   /**
	* {@inheritDoc} 
    * @throws VerificationInDataBaseException 
	* @see com.adaming.myapp.etudiant.service.IEtudiantService.addStudent
	**/
	@Override
	@Transactional(readOnly=false)
	public Etudiant addStudent(final Etudiant e, final Long idSession)
			throws VerificationInDataBaseException {
	            Etudiant etudiant = verifyExistingEtudiant(e.getNomEtudiant(),e.getDateDeNaissance());
	            if (etudiant != null)
				{
					throw new VerificationInDataBaseException("L'étudiant "
							+ e.getNomEtudiant()
							+ " Existe déja dans la Session N°" + idSession);
				}
				else 
					if(getEtudiant(e.getMail()) != null)
				{
					throw new VerificationInDataBaseException("l'adresse mail "+e.getMail()+" existe déjà dans la session N° "+idSession+" Veuillez renseigner une autre adresse mail");
				}

		        return dao.addStudent(e, idSession);
	}
   
	
	/**
	 * {@inheritDoc} 
	 * @see com.adaming.myapp.etudiant.service.IEtudiantService.updateStudent
	 **/
	@Override
	@Transactional(readOnly=false)
	public Etudiant updateStudent(final Etudiant e, final Long idSession) {
		// TODO Auto-generated method stub
		return dao.updateStudent(e, idSession);
	}
   
	
	
	/**
	 * {@inheritDoc} 
	 * @see com.adaming.myapp.etudiant.service.IEtudiantService.removeStudent
	 **/
	@Override
	@Transactional(readOnly=false)
	public Etudiant removeStudent(final Long idStudent) {
		// TODO Auto-generated method stub
		return dao.removeStudent(idStudent);
	}
    
	
	
	/**
	 * {@inheritDoc} 
	 * @see com.adaming.myapp.etudiant.service.IEtudiantService.getStudentById
	 **/
	@Override
	public Etudiant getStudentById(final Long idStudent) {
		// TODO Auto-generated method stub
		return dao.getStudentById(idStudent);
	}
    
	
	
	/**
	 * {@inheritDoc} 
	 * @throws VerificationInDataBaseException 
	 * @see com.adaming.myapp.etudiant.service.IEtudiantService.getEtudiantBySession
	 **/
	@Override
	public List<Object[]> getEtudiantBySession(final Long idSession) throws VerificationInDataBaseException {
		 List<Object[]> objects = dao.getEtudiantBySession(idSession);
		 if(objects != null){
        	if(objects.size() == 0){
        		throw new VerificationInDataBaseException("Il n'existe aucun étudiant dans la session Numéro "+idSession);
        	}
        	LoggerConfig.logInfo("le nombre des etudiants dans la session N "
					+ idSession + "est " + objects.size());
        }
		return objects;
	}
    
	
	
	/**
	 * {@inheritDoc} 
	 * @see com.adaming.myapp.etudiant.service.IEtudiantService.getEtudiant
	 **/
	@Override
	public Etudiant getEtudiant(final String mail) {
		// TODO Auto-generated method stub
		return dao.getEtudiant(mail);
	}


	@Override
	public List<Etudiant> getStudentsBySession(final Long idSession) throws VerificationInDataBaseException {
		List<Etudiant> etudiants = dao.getStudentsBySession(idSession);
        if(etudiants == null || etudiants.isEmpty()){
        		throw new VerificationInDataBaseException("Il n'existe aucun étudiant dans la session Numéro "+idSession);
        }
		return dao.getStudentsBySession(idSession);
	}


	@Override
	public Etudiant verifyExistingEtudiant(final String name, final Date dateDeNaissance) {
		// TODO Auto-generated method stub
		return dao.verifyExistingEtudiant(name, dateDeNaissance);
	}

}
