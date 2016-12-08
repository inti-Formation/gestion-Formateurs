package com.adaming.myapp.module.dao;

import java.util.List;

import com.adaming.myapp.entities.Module;
import com.adaming.myapp.exception.AddModuleException;
import com.adaming.myapp.persistence.IGenericDao;

public interface IModuleDao extends IGenericDao<Module> {
	

	 Module addModule(Module m,Long idSpecialite) throws AddModuleException;
	
	 Module updateModule(Module m,Long idSpecialite);
	
	 List<Module> getModulesBySpecialite(Long idSpecialite);
	
	 List<Module> getModulesBySession(Long idSession);
	
	
}
