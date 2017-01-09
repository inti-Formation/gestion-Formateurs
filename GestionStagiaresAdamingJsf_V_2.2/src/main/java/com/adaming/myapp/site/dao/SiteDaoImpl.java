package com.adaming.myapp.site.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.adaming.myapp.entities.Salle;
import com.adaming.myapp.entities.Site;
import com.adaming.myapp.entities.User;
import com.adaming.myapp.persistence.AbstractJpaDao;

public class SiteDaoImpl extends AbstractJpaDao<Site> implements ISiteDao{
    
	private Logger LOGGER = Logger.getLogger(SiteDaoImpl.class);
	
	@Override
	public List<Site> getAll() {
		// TODO Auto-generated method stub
		return getAllAbstractJpa();
	}

	@Override
	public Site getOne(Long id) {
		// TODO Auto-generated method stub
		return getOneAbstractJpa(id);
	}

	@Override
	public Site add(Site site) {
		em.persist(site);
		LOGGER.info("Site "+site+ "has benn added");
		return site;
	}

	@Override
	public Site update(Site site) {
		em.merge(site);
		LOGGER.info("Site "+site+ "has benn merged");
		return site;
	}

	@Override
	public Site remove(Long idSite) {
		Site site = getOne(idSite);
		em.remove(site);
		LOGGER.info("Site "+site+ "has benn removed");
		return site;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Site> getSiteByName(String nom, String adresse) {
		Query query = em.createQuery("select s.nomSite,s.adresse.adresse From Site s where s.nomSite =:x and s.adresse.adresse =:y");
		query.setParameter("x",nom);
		query.setParameter("y",adresse);
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> getSallesBySite(Long idSite) {
		Query query = em.createQuery(
				  "Select sa.idSalle,sa.numeroSalle,sa.nbPlace FROM Salle sa JOIN"
				+ " sa.site o where o.idSite=:x");
		query.setParameter("x",idSite);
		LOGGER.info("il existe "+query.getResultList()+" dans le site "+idSite);
		return query.getResultList();
	}


}
