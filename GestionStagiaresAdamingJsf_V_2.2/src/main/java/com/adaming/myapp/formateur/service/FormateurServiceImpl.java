package com.adaming.myapp.formateur.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.entities.Formateur;
import com.adaming.myapp.exception.VerificationInDataBaseException;
import com.adaming.myapp.formateur.dao.IFormateurDao;

@Transactional(readOnly=true)
public class FormateurServiceImpl implements IFormateurService {

	private IFormateurDao dao;

	private final Logger LOGGER = Logger.getLogger("FormateurServiceImpl");

	public void setDao(IFormateurDao dao) {
		this.dao = dao;
		LOGGER.info("<----------Dao Formateur Injected-------->");
	}

	@Override
	@Transactional(readOnly=false)
	public Formateur addFormateur(Formateur f)
			throws VerificationInDataBaseException {
		List<Object[]> formateurs = getFormateuByName(f.getNom(), f.getDateDeNaissance(),f.getMail());
		
		/*verification*/
		if(!formateurs.isEmpty()){
			for(Object[] formateur:formateurs){
				String nom = (String) formateur[0];
				Date date = (Date) formateur[1];
				String mail = (String) formateur[2];
				if(date.compareTo(f.getDateDeNaissance()) == 0
						&& nom.equals(f.getNom()))
				{
					throw new VerificationInDataBaseException("Le Formateur "
							+ f.getNom()
							+ " Existe déja dans la base de données");
				}
				else if(mail.equals(f.getMail()))
				{
					throw new VerificationInDataBaseException("l'adresse mail "+mail+" existe déjà dans la base de donnée, Veuillez renseigner une autre adresse mail");
				}
			}
		}
		return dao.addFormateur(f);
	}

	@Override
	@Transactional(readOnly=false)
	public void addFormateurToSession(Long idSession, Long idFormateur) {
		dao.addFormateurToSession(idSession, idFormateur);
	}

	@Override
	public List<Formateur> getAllFormateurs() {
		// TODO Auto-generated method stub
		return dao.getAll();
	}

	@Override
	public Formateur getFormateur(String mail) {
		// TODO Auto-generated method stub
		return dao.getFormateur(mail);
	}

	@Override
	public Formateur getFormateurById(Long idFormateur) {
		// TODO Auto-generated method stub
		return dao.getOne(idFormateur);
	}

	@Override
	public List<Object[]> getFormateuByName(String nom, Date dateDeNaissance,
			String mail) {
		// TODO Auto-generated method stub
		return dao.getFormateuByName(nom, dateDeNaissance, mail);
	}

}
