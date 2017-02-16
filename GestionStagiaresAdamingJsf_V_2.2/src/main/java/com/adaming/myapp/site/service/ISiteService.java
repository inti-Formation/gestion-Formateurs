package com.adaming.myapp.site.service;

import java.util.List;

import com.adaming.myapp.entities.Salle;
import com.adaming.myapp.entities.Site;
import com.adaming.myapp.exception.VerificationInDataBaseException;

public interface ISiteService {

	Site add(final Site site) throws VerificationInDataBaseException;

	Site update(final Site site);

	Site remove(final Long idSite);
	
	Site getOne(final Long idSite);
	
	List<Site> getAll();
	
	List<Site> getSiteByName(final String nom,final String adresse);
	
	List<Object[]> getSallesBySite(final Long idSite) throws VerificationInDataBaseException;
}
