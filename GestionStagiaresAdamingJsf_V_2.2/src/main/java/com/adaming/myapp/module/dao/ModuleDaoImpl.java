package com.adaming.myapp.module.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.Query;

import com.adaming.myapp.entities.Module;
import com.adaming.myapp.entities.SessionEtudiant;
import com.adaming.myapp.entities.Specialite;
import com.adaming.myapp.exception.AddModuleException;
import com.adaming.myapp.persistence.AbstractJpaDao;

public class ModuleDaoImpl extends AbstractJpaDao<Module> implements IModuleDao{
    
	private final Logger LOGGER = Logger.getLogger("ModuleDaoImpl");
	
	@Override
	public List<Module> getAll() {
		List<Module> modules = getAllAbstractJpa();
		LOGGER.info("il existe "+modules.size()+"Modules");
		return modules;
	}

	@Override
	public Module getOne(Long id) {
		Module m = getOneAbstractJpa(id);
		LOGGER.info("le module"+m.getIdModule()+"a bien été recupérer");
		return m;
	}

	@Override
	public Module addModule(Module m, Long idSpecialite)
			throws AddModuleException {
		Specialite s  = em.find(Specialite.class, idSpecialite);
		m.setSpecialite(s);
		em.persist(m);
		LOGGER.info("le module"+m.getIdModule()+" a bien été enregistrer");
		return m;
	}

	@Override
	public Module updateModule(Module m, Long idSpecialite) {
		Specialite s = em.find(Specialite.class,idSpecialite);
		m.setSpecialite(s);
		em.merge(m);
		LOGGER.info("le module "+m.getIdModule()+" a bien été recupérer");
		return m;
	}

	@Override
	public List<Module> getModulesBySpecialite(Long idSpecialite) {
		Specialite s  = em.find(Specialite.class,idSpecialite);
		List<Module> modules = s.getModules();
		LOGGER.info("il existes"+modules.size()+" dans la specialite"+s.getDesignation());
		return modules;
	}

	@Override
	public List<Module> getModulesBySession(Long idSession) {
		SessionEtudiant session = em.find(SessionEtudiant.class,idSession);
		List<Module> modules = session.getSpecialite().getModules();
		LOGGER.info("il existe "+modules.size()+" modules dans la sessions Numéro "+idSession);
		return modules;
	}

}
