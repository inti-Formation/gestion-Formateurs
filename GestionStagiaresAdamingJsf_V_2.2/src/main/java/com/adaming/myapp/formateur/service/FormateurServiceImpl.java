package com.adaming.myapp.formateur.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.entities.Formateur;
import com.adaming.myapp.exception.VerificationInDataBaseException;
import com.adaming.myapp.formateur.dao.IFormateurDao;

@Transactional
public class FormateurServiceImpl implements IFormateurService {

	private IFormateurDao dao;

	private Logger logger = Logger.getLogger("FormateurServiceImpl");

	public void setDao(IFormateurDao dao) {
		this.dao = dao;
		logger.info("<----------Dao Formateur Injected-------->");
	}

	@Override
	public Formateur addFormateur(Formateur f)
			throws VerificationInDataBaseException {
		// TODO Auto-generated method stub
		return dao.addFormateur(f);
	}

	@Override
	public void addFormateurToSession(Long idSession, Long idFormateur) {
		dao.addFormateurToSession(idSession, idFormateur);
	}

	@Override
	public List<Formateur> getAllFormateurs() {
		// TODO Auto-generated method stub
		return dao.getAllFormateurs();
	}

	@Override
	public Formateur getFormateur(String mail) {
		// TODO Auto-generated method stub
		return dao.getFormateur(mail);
	}

}
