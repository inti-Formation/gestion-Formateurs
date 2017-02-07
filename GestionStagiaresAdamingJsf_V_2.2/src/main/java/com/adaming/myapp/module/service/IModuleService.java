package com.adaming.myapp.module.service;

import java.util.List;
import java.util.Set;

import com.adaming.myapp.entities.Module;
import com.adaming.myapp.exception.AddModuleException;
import com.adaming.myapp.exception.VerificationInDataBaseException;

public interface IModuleService {
	
	 List<Module> getAllModules();

	 Module addModule(Module m, Long idSpecialite) throws VerificationInDataBaseException;

	 Module getModuleById(Long idModule);

	 Module updateModule(Module m, Long idSpecialite);

	 List<Module> getModulesBySpecialite(Long idSpecialite) throws VerificationInDataBaseException;
	
	 List<Module> getModulesBySession(Long idSession);
	 
	 List<Object[]> getModulesBySessionV2(Long idSession);
	 
	 List<Module> getModuleActivedBySession(Long idSession) throws VerificationInDataBaseException;
	 
	 Set<Object[]> getModulesValideBySession(Long idSession) throws VerificationInDataBaseException;
	 
	 Module verifyExistingModule(String name);
}
