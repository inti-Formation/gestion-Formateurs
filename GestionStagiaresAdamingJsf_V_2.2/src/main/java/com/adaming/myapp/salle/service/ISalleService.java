package com.adaming.myapp.salle.service;

import java.util.List;

import com.adaming.myapp.entities.Salle;
import com.adaming.myapp.exception.VerificationInDataBaseException;

public interface ISalleService {
        
	    Salle getOne(final Long id);
	    
	    List<Salle> getAll();
	    
	    Salle add(final Salle salle,Long idSite) throws VerificationInDataBaseException;
		
		Salle update(final Salle salle);
		
		Salle remove(final Long idSalle);
		
		List<Salle> getSalleByName(final String numero,final Long idSite);
}
