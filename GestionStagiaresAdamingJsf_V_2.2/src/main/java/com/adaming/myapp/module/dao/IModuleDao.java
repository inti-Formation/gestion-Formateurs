package com.adaming.myapp.module.dao;

import java.util.List;
import java.util.Set;

import com.adaming.myapp.entities.Module;
import com.adaming.myapp.exception.AddModuleException;
import com.adaming.myapp.exception.VerificationInDataBaseException;
import com.adaming.myapp.persistence.IGenericDao;

public interface IModuleDao extends IGenericDao<Module> {
	

	 Module addModule(Module m,Long idSpecialite);
	
	 Module updateModule(Module m,Long idSpecialite);
	
	 List<Module> getModulesBySpecialite(Long idSpecialite) throws VerificationInDataBaseException;
	
	 List<Module> getModulesBySession(Long idSession);
	 
	 List<Object[]> getModulesBySessionV2(Long idSession);
	
	 List<Module> getModuleActivedBySession(Long idSession);
	 
	 Set<Object[]> getModulesValideBySession(Long idSession);
	 
	 Module verifyExistingModule(String name);
}
