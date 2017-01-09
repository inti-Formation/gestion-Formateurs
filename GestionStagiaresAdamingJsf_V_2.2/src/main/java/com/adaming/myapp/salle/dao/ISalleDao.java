package com.adaming.myapp.salle.dao;

import java.util.List;

import com.adaming.myapp.entities.Salle;
import com.adaming.myapp.entities.Site;
import com.adaming.myapp.persistence.IGenericDao;

public interface ISalleDao extends IGenericDao<Salle>{

    Salle add(final Salle salle,Long idSite);
	
	Salle update(final Salle salle);
	
	Salle remove(final Long idSalle);
	
	List<Salle> getSalleByName(final String numero,final Long idSite);
}
