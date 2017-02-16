package com.adaming.myapp.site.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.adaming.myapp.entities.Salle;
import com.adaming.myapp.entities.Site;
import com.adaming.myapp.entities.User;
import com.adaming.myapp.persistence.AbstractJpaDao;
import com.adaming.myapp.tools.LoggerConfig;

public class SiteDaoImpl extends AbstractJpaDao<Site> implements ISiteDao{
    

	
	@Override
	public List<Site> getAll() {
		// TODO Auto-generated method stub
		return getAllAbstractJpa();
	}

	@Override
	public Site getOne(final Long id) {
		// TODO Auto-generated method stub
		return getOneAbstractJpa(id);
	}

	@Override
	public Site add(final Site site) {
		em.persist(site);
		LoggerConfig.logInfo("Site "+site+ "has benn added");
		return site;
	}

	@Override
	public Site update(final Site site) {
		em.merge(site);
		LoggerConfig.logInfo("Site "+site+ "has benn merged");
		return site;
	}

	@Override
	public Site remove(final Long idSite) {
		Site site = getOne(idSite);
		em.remove(site);
		LoggerConfig.logInfo("Site "+site+ "has benn removed");
		return site;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Site> getSiteByName(final String nom, final String adresse) {
		Query query = em.createQuery("select s.nomSite,s.adresse.adresse From Site s where s.nomSite =:x and s.adresse.adresse =:y");
		query.setParameter("x",nom);
		query.setParameter("y",adresse);
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> getSallesBySite(final Long idSite) {
		Query query = em.createQuery(
				  "Select sa.idSalle,sa.numeroSalle,sa.nbPlace FROM Salle sa JOIN"
				+ " sa.site o where o.idSite=:x");
		query.setParameter("x",idSite);
		LoggerConfig.logInfo("il existe "+query.getResultList()+" dans le site "+idSite);
		return query.getResultList();
	}
	
	


}
