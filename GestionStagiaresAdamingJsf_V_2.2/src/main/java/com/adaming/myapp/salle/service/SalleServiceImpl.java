package com.adaming.myapp.salle.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.entities.Salle;
import com.adaming.myapp.exception.VerificationInDataBaseException;
import com.adaming.myapp.salle.dao.ISalleDao;
import com.adaming.myapp.tools.LoggerConfig;
@Transactional(readOnly=true)
public class SalleServiceImpl implements ISalleService {
    
	private ISalleDao dao;
	

	/**
	 * @param dao the dao to set
	 */
	public void setDao(ISalleDao dao) {
		this.dao = dao;
		LoggerConfig.logInfo("<---------DAO Salle Injected----->");
	}

	@Override
	public Salle getOne(final Long id) {
		// TODO Auto-generated method stub
		return dao.getOne(id);
	}

	@Override
	public List<Salle> getAll() {
		// TODO Auto-generated method stub
		return dao.getAll();
	}

	@Override
	@Transactional(readOnly=false)
	public Salle add(final Salle salle, final Long idSite) throws VerificationInDataBaseException {
		List<Salle> salles = getSalleByName(salle.getNumeroSalle(), idSite);
		if(salles.size()>0){
			throw new VerificationInDataBaseException("La salle Numéro :"+salle.getNumeroSalle()+" existe Déja dans le site numéro "+idSite);
		}
		return dao.add(salle, idSite);
	}

	@Override
	@Transactional(readOnly=false)
	public Salle update(final Salle salle) {
		// TODO Auto-generated method stub
		return dao.update(salle);
	}

	@Override
	@Transactional(readOnly=false)
	public Salle remove(final Long idSalle) {
		// TODO Auto-generated method stub
		return dao.remove(idSalle);
	}

	@Override
	public List<Salle> getSalleByName(final String numero, final Long idSite) {
		// TODO Auto-generated method stub
		return dao.getSalleByName(numero, idSite);
	}

}
