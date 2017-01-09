package com.adaming.myapp.salle.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import com.adaming.myapp.entities.Salle;
import com.adaming.myapp.exception.VerificationInDataBaseException;
import com.adaming.myapp.salle.dao.ISalleDao;
@Transactional(readOnly=true)
public class SalleServiceImpl implements ISalleService {
    
	private ISalleDao dao;
	
	private final Logger LOGGER = Logger.getLogger(SalleServiceImpl.class);
	
	
	/**
	 * @param dao the dao to set
	 */
	public void setDao(ISalleDao dao) {
		this.dao = dao;
		LOGGER.info("<---------DAO Salle Injected----->");
	}

	@Override
	public Salle getOne(Long id) {
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
	public Salle add(Salle salle, Long idSite) throws VerificationInDataBaseException {
		List<Salle> salles = getSalleByName(salle.getNumeroSalle(), idSite);
		if(salles.size()>0){
			throw new VerificationInDataBaseException("La salle Numéro :"+salle.getNumeroSalle()+"existe Déja dans le site numéro "+idSite);
		}
		return dao.add(salle, idSite);
	}

	@Override
	@Transactional(readOnly=false)
	public Salle update(Salle salle) {
		// TODO Auto-generated method stub
		return dao.update(salle);
	}

	@Override
	@Transactional(readOnly=false)
	public Salle remove(Long idSalle) {
		// TODO Auto-generated method stub
		return dao.remove(idSalle);
	}

	@Override
	public List<Salle> getSalleByName(String numero, Long idSite) {
		// TODO Auto-generated method stub
		return dao.getSalleByName(numero, idSite);
	}

}
