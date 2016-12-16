package com.adaming.myapp.module.service;

import java.util.List;

import com.adaming.myapp.entities.Module;
import com.adaming.myapp.exception.AddModuleException;
import com.adaming.myapp.exception.VerificationInDataBaseException;

public interface IModuleService {
	
	 List<Module> getAllModules();

	 Module addModule(Module m, Long idSpecialite) throws AddModuleException;

	 Module getModuleById(Long idModule);

	 Module updateModule(Module m, Long idSpecialite);

	 List<Module> getModulesBySpecialite(Long idSpecialite) throws VerificationInDataBaseException;
	
	 List<Module> getModulesBySession(Long idSession);
}
