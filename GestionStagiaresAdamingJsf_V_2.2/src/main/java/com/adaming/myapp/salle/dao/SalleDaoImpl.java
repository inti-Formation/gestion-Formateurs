package com.adaming.myapp.salle.dao;

import java.util.List;

import javax.persistence.Query;

import com.adaming.myapp.entities.Salle;
import com.adaming.myapp.entities.Site;
import com.adaming.myapp.persistence.AbstractJpaDao;

public class SalleDaoImpl extends AbstractJpaDao<Salle> implements ISalleDao {

	@Override
	public List<Salle> getAll() {
		// TODO Auto-generated method stub
		return getAllAbstractJpa();
	}

	@Override
	public Salle getOne(Long id) {
		// TODO Auto-generated method stub
		return getOneAbstractJpa(id);
	}

	@Override
	public Salle add(Salle salle,Long idSite) {
		Site site = em.find(Site.class,idSite);
		salle.setSite(site);
		em.persist(salle);
		return salle;
	}

	@Override
	public Salle update(Salle salle) {
		em.merge(salle);
		return salle;
	}

	@Override
	public Salle remove(Long idSalle) {
		Salle salle = getOne(idSalle);
		em.remove(salle);
		return salle;
	}

	@Override
	public List<Salle> getSalleByName(String numero,Long idSite) {
		Query query = em.createQuery("select sa.numeroSalle,sa.site.idSite From Salle sa where sa.numeroSalle=:x and sa.site.idSite =:y");
		query.setParameter("x",numero);
		query.setParameter("y",idSite);
		return query.getResultList();
	}

}
