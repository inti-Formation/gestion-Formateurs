package com.adaming.myapp.module.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.entities.Module;
import com.adaming.myapp.exception.AddModuleException;
import com.adaming.myapp.module.dao.IModuleDao;
@Transactional
public class ModuleServiceImpl implements IModuleService{
    
	private IModuleDao dao;
    
	Logger log = Logger.getLogger("ModuleServiceImpl");
    
	public void setDao(IModuleDao dao) {
		this.dao = dao;
		log.info("<------Dao Module a bien été injecté------>");
	}

	@Override
	public Module addModule(Module m, Long idSpecialite) throws AddModuleException {
		// TODO Auto-generated method stub
		return dao.addModule(m, idSpecialite);
	}

	@Override
	public Module getModuleById(Long idModule) {
		// TODO Auto-generated method stub
		return dao.getModuleById(idModule);
	}

	@Override
	public Module updateModule(Module m, Long idSpecialite) {
		// TODO Auto-generated method stub
		return dao.updateModule(m, idSpecialite);
	}

	@Override
	public List<Module> getModulesBySpecialite(Long idSpecialite) {
		// TODO Auto-generated method stub
		return dao.getModulesBySpecialite(idSpecialite);
	}

	@Override
	public List<Module> getAllModules() {
		// TODO Auto-generated method stub
		return dao.getAllModules();
	}

	@Override
	public List<Module> getModulesBySession(Long idSession) {
		// TODO Auto-generated method stub
		return dao.getModulesBySession(idSession);
	}

}
