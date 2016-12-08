package com.adaming.myapp.etudiant.dao;

import java.util.List;

import com.adaming.myapp.entities.Etudiant;
import com.adaming.myapp.exception.AddEtudiantException;
/*
 *  @author Adel 
 *  @version 1.0.0
 *  @date 11/10/2016
 *  @param e l'entité etudiant 
 *  @param idSession le type de la relation à associée
 *  @param idStudent l'identifiant de l'entité etudiant
 *  @param mail le mail de l'etudiant
 *  @throws AddEtudiantException vérification dans la base de donnée
 * */
public interface IEtudiantDao {
	
	
	
	/* Cette méthode permet d'enregister un objet de type etudiant 
	 * donne le type " identifiant de session " afin de l'associé à une session 
	 * @return le type sous forme d'un objet etudiant
	 * @throws AddEtudiantException se lève si le nom et le mail existe déja dans la base de donnée **/ 
	 Etudiant addStudent(Etudiant e, Long idSession)
			throws AddEtudiantException;

	
	 
	 
	/* Cette méthode permet de modifié un objet de type étudiant 
	 * donne le type " identifiant de l'objet session " afin de le garder à sa session initial 
	 * @return le type sous forme d'un objet  etudiant**/
	 Etudiant updateStudent(Etudiant e, Long idSession);

	 
	 
	 
	/* Cette méthode permet de supprimer un objet de type étudiant 
     * donne le type " identifiant de l'objet etudiant "
     * @return le type sous forme d'un objet etudiant**/
	 Etudiant removeStudent(Long idStudent);
	   
	  
	  
	 
	 /* Cette méthode permet de récupérer un objet de type étudiant 
	  * donne le type " identifiant de l'objet etudiant "
	  * @return le type sous forme d'un objet etudiant**/
	  Etudiant getStudentById(Long idStudent);
			 
	  
	  
	  
	 /* Cette méthode permet de récupérer une liste d'objets de type étudiant 
	  * donne le type " identifiant de l'objet etudiant "
	  * @return une liste sous forme d'un objet etudiant**/
	  List<Etudiant> getEtudiantBySession(Long idSession);
			 
	
	  
	  
	  
	 /* Cette méthode permet de récupérer un objet de type étudiant 
	  * donne le type " mail de l'objet etudiant "
	  * @return le type sous forme d'un objet etudiant**/
	  Etudiant getEtudiant(String mail);

}
