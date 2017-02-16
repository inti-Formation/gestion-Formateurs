package com.adaming.myapp.specialite.service;
import java.util.List;
import java.util.logging.Logger;

import javax.faces.bean.ApplicationScoped;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.entities.Specialite;
import com.adaming.myapp.exception.AddSpecialiteException;
import com.adaming.myapp.exception.VerificationInDataBaseException;
import com.adaming.myapp.specialite.dao.ISpecialiteDao;
import com.adaming.myapp.tools.LoggerConfig;
/**
 * 
 * @author adel
 * @date 10/10/2016
 * @version 1.0.0
 * */
@Transactional(readOnly=true)

public class SpecialiteServiceImpl implements ISpecialiteService{
    
	
	
	private ISpecialiteDao dao;
	
	public void setDao(ISpecialiteDao dao) {
		this.dao = dao;
		LoggerConfig.logInfo("<----------dao specialite injected-------->");
	}

	@Override
	@Transactional(readOnly=false)
	public Specialite addSpecialite(Specialite sp) throws VerificationInDataBaseException {
		Specialite specialite = verifyExistingSpecialite(sp.getDesignation());
		if (specialite != null) 
		{
			throw new VerificationInDataBaseException(
					"La Spécialitée "+sp.getDesignation()+" Existe Déja !!");
		}
		return dao.addSpecialite(sp);
	}

	@Override
	@Transactional(readOnly=false)
	public Specialite updateSpecialite(Specialite sp) {
		// TODO Auto-generated method stub
		return dao.updateSpecialite(sp);
	}

	@Override
	public Specialite getSpecialiteById(Long idSpecialite) {
		// TODO Auto-generated method stub
		return dao.getSpecialiteById(idSpecialite);
	}

	

	@Override
	public List<Specialite> getAllSpecV2() {
		// TODO Auto-generated method stub
		return dao.getAllSpecV2();
	}

	@Override
	public Specialite verifyExistingSpecialite(String name) {
		// TODO Auto-generated method stub
		return dao.verifyExistingSpecialite(name);
	}
	
	

}
