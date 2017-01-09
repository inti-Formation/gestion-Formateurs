package com.adaming.myapp.module.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.entities.Module;
import com.adaming.myapp.exception.AddModuleException;
import com.adaming.myapp.exception.VerificationInDataBaseException;
import com.adaming.myapp.module.dao.IModuleDao;
@Transactional(readOnly=true)
public class ModuleServiceImpl implements IModuleService{
    
	private IModuleDao dao;
    
	Logger log = Logger.getLogger("ModuleServiceImpl");
    
	public void setDao(IModuleDao dao) {
		this.dao = dao;
		log.info("<------Dao Module a bien été injecté------>");
	}

	@Override
	@Transactional(readOnly=false)
	public Module addModule(Module m, Long idSpecialite) throws AddModuleException {
		List<Module> modules = null;
		modules = getAllModules();
		for(Module module:modules){
			if(m.getNomModule().equals(module.getNomModule())){
				throw new AddModuleException("le Module "+m.getNomModule()+" existe Déja dans la spécialitée "+module.getSpecialite().getDesignation()+" Merçi de bien vouloir changer la description du Module,  Ex:"+m.getNomModule()+" 2");
			}
		}
		return dao.addModule(m, idSpecialite);
	}

	@Override
	public Module getModuleById(Long idModule) {
		// TODO Auto-generated method stub
		return dao.getOne(idModule);
	}

	@Override
	@Transactional(readOnly=false)
	public Module updateModule(Module m, Long idSpecialite) {
		// TODO Auto-generated method stub
		return dao.updateModule(m, idSpecialite);
	}

	@Override
	public List<Module> getModulesBySpecialite(Long idSpecialite) throws VerificationInDataBaseException {
		// TODO Auto-generated method stub
		return dao.getModulesBySpecialite(idSpecialite);
	}

	@Override
	public List<Module> getAllModules() {
		// TODO Auto-generated method stub
		return dao.getAll();
	}

	@Override
	public List<Module> getModulesBySession(Long idSession) {
		// TODO Auto-generated method stub
		return dao.getModulesBySession(idSession);
	}

}
