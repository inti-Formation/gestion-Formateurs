package com.adaming.myapp.etudiant.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.entities.Etudiant;
import com.adaming.myapp.etudiant.dao.IEtudiantDao;
import com.adaming.myapp.exception.AddEtudiantException;

@Transactional
public class EtudiantServiceImpl implements IEtudiantService {

	Logger log = Logger.getLogger("EtudiantServiceImpl");

	private IEtudiantDao dao;

	public void setDao(IEtudiantDao dao) {
		this.dao = dao;
		log.info("<-----------Dao Student Injected---------->");
	}

	@Override
	public Etudiant addStudent(Etudiant e, Long idSession)
			throws AddEtudiantException {
		List<Etudiant> tabEtudiant = null;// verifications
		tabEtudiant = getEtudiantBySession(idSession);
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

	@Override
	public Etudiant updateStudent(Etudiant e, Long idSession) {
		// TODO Auto-generated method stub
		return dao.updateStudent(e, idSession);
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

	@Override
	public Etudiant getEtudiant(String mail) {
		// TODO Auto-generated method stub
		return dao.getEtudiant(mail);
	}

}
