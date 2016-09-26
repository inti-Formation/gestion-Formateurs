package com.adaming.myapp.module.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.adaming.myapp.entities.Module;
import com.adaming.myapp.entities.SessionEtudiant;
import com.adaming.myapp.entities.Specialite;
import com.adaming.myapp.exception.AddModuleException;

public class ModuleDaoImpl implements IModuleDao{
	
	@PersistenceContext
    private EntityManager em;
	
	Logger log = Logger.getLogger("ModuleDaoImpl");
	
	
	@Override
	public Module addModule(Module m, Long idSpecialite) throws AddModuleException {
		Specialite s  = em.find(Specialite.class, idSpecialite);
		m.setSpecialite(s);
		List<Module> modules = getAllModules();
		for(Module module:modules){
			if(m.getNomModule().equals(module.getNomModule())){
				throw new AddModuleException("le Module "+m.getNomModule()+" existe Déja dans la Spécialitée "+module.getSpecialite().getDesignation()+" Merçi de Bien Vouloir Changer la Description du Module,  Ex:"+m.getNomModule()+" 2");
			}
		}
		em.persist(m);
		log.info("le module"+m.getIdModule()+" a bien été enregistrer");
		return m;
	}

	@Override
	public Module getModuleById(Long idModule) {
		Module m = em.find(Module.class,idModule);
		log.info("le module"+m.getIdModule()+"a bien été recupérer");
		return m;
	}

	@Override
	public Module updateModule(Module m, Long idSpecialite) {
		Specialite s = em.find(Specialite.class,idSpecialite);
		m.setSpecialite(s);
		em.merge(m);
		log.info("le module "+m.getIdModule()+" a bien été recupérer");
		return m;
	}

	@Override
	public List<Module> getModulesBySpecialite(Long idSpecialite) {
		Specialite s  = em.find(Specialite.class,idSpecialite);
		List<Module> modules = s.getModules();
		log.info("il existes"+modules.size()+" dans la specialite"+s.getDesignation());
		return modules;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Module> getAllModules() {
		Query query = em.createQuery("from Module m");
		log.info("il existe "+query.getResultList().size()+"Modules");
		return query.getResultList();
	}

	@Override
	public List<Module> getModulesBySession(Long idSession) {
		SessionEtudiant session = em.find(SessionEtudiant.class,idSession);
		List<Module> modules = session.getSpecialite().getModules();
		log.info("il existe "+modules.size()+" modules dans la sessions Numéro "+idSession);
		return modules;
	}

}
