package com.adaming.myapp.contrat.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.contrat.dao.IContratDao;
import com.adaming.myapp.entities.Contrat;
import com.adaming.myapp.exception.VerificationInDataBaseException;
import com.adaming.myapp.tools.LoggerConfig;
@Transactional(readOnly=true)
public class ContratServiceImpl implements IContratService{
	
	/**
     * Logger @see java.util.logging.Logger
     **/
	
	
	
	private IContratDao dao;

	
	/**
	 * @param dao the dao to set
	 */
	public void setDao(IContratDao dao) {
		this.dao = dao;
		LoggerConfig.logInfo("<-------Dao Contrat Injected------>");
		
	}

	@Override
	@Transactional(readOnly=false)
	public Contrat addContrat(Contrat c,Long idEtudiant) throws VerificationInDataBaseException {
		List<Contrat> contrats = null;
		contrats=getAll();
		if(contrats.size()>0){
			for(Contrat contrat:contrats){
				if(contrat.getEtudiant().getIdEtudiant() == idEtudiant){
					throw new VerificationInDataBaseException("le contrat numéro "+contrat.getIdContrat()+" est déja lu et approuvé");
				}
			}
		}
		return dao.addContrat(c,idEtudiant);
	}

	@Override
	public List<Contrat> getAll() {
		// TODO Auto-generated method stub
		return dao.getAll();
	}

	@Override
	public Contrat getOne(Long id) {
		// TODO Auto-generated method stub
		return dao.getOne(id);
	}

	@Override
	public int nombreContrat() {
		// TODO Auto-generated method stub
		return dao.nombreContrat();
	}

	@Override
	@Transactional(readOnly=false)
	public void remove(Long idContrat) {
		// TODO Auto-generated method stub
		dao.remove(idContrat);
	}

}
