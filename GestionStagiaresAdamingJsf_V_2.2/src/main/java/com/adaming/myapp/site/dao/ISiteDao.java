package com.adaming.myapp.site.dao;

import java.util.List;

import com.adaming.myapp.entities.Salle;
import com.adaming.myapp.entities.Site;
import com.adaming.myapp.persistence.IGenericDao;

public interface ISiteDao extends IGenericDao<Site> {

	Site add(final Site site);
	
	Site update(final Site site);
	
	Site remove(final Long idSite);
	
	List<Site> getSiteByName(final String nom,final String adresse);
	
	List<Object[]> getSallesBySite(final Long idSite);
}
