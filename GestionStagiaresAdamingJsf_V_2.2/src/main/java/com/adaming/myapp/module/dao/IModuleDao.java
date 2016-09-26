package com.adaming.myapp.module.dao;

import java.util.List;

import com.adaming.myapp.entities.Module;
import com.adaming.myapp.exception.AddModuleException;

public interface IModuleDao {
	
	public List<Module> getAllModules();

	public Module addModule(Module m,Long idSpecialite) throws AddModuleException;
	
	public Module getModuleById(Long idModule);
	
	public Module updateModule(Module m,Long idSpecialite);
	
	public List<Module> getModulesBySpecialite(Long idSpecialite);
	
	public List<Module> getModulesBySession(Long idSession);
	
	
}
