package com.adaming.myapp.salle.dao;

import java.util.List;

import javax.persistence.Query;

import com.adaming.myapp.entities.Salle;
import com.adaming.myapp.entities.Site;
import com.adaming.myapp.persistence.AbstractJpaDao;
/**
 * 
 * @author adel
 * @date 10/10/2016
 * @version 1.0.0
 * */
public class SalleDaoImpl extends AbstractJpaDao<Salle> implements ISalleDao {

	@Override
	public List<Salle> getAll() {
		// TODO Auto-generated method stub
		return getAllAbstractJpa();
	}

	@Override
	public Salle getOne(final Long id) {
		// TODO Auto-generated method stub
		return getOneAbstractJpa(id);
	}

	@Override
	public Salle add(final Salle salle,final Long idSite) {
		Site site = em.find(Site.class,idSite);
		salle.setSite(site);
		em.persist(salle);
		return salle;
	}

	@Override
	public Salle update(final Salle salle) {
		em.merge(salle);
		return salle;
	}

	@Override
	public Salle remove(final Long idSalle) {
		Salle salle = getOne(idSalle);
		em.remove(salle);
		return salle;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Salle> getSalleByName(final String numero,final Long idSite) {
		final String SQL = "select sa.numeroSalle,sa.site.idSite From Salle sa where sa.numeroSalle=:x and sa.site.idSite =:y";
		Query query = em.createQuery(SQL).setParameter("x",numero).setParameter("y",idSite);
		return query.getResultList();
	}

}
