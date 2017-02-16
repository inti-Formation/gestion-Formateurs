package com.adaming.myapp.module.dao;

import java.util.List;
import java.util.Set;

import com.adaming.myapp.entities.Module;
import com.adaming.myapp.exception.AddModuleException;
import com.adaming.myapp.exception.VerificationInDataBaseException;
import com.adaming.myapp.persistence.IGenericDao;

public interface IModuleDao extends IGenericDao<Module> {
	

	 Module addModule(final Module m,final Long idSpecialite);
	
	 Module updateModule(final Module m,final Long idSpecialite);
	
	 List<Module> getModulesBySpecialite(final Long idSpecialite) throws VerificationInDataBaseException;
	
	 List<Module> getModulesBySession(final Long idSession);
	 
	 List<Object[]> getModulesBySessionV2(final Long idSession);
	
	 List<Module> getModuleActivedBySession(final Long idSession);
	 
	 Set<Object[]> getModulesValideBySession(final Long idSession);
	 
	 Module verifyExistingModule(final String name);
}
