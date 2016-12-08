package com.adaming.myapp.persistence;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractJpaDao<T extends Serializable> {
    
	@PersistenceContext
	protected EntityManager em;
	
	private Class <T> clazz;
	
	@SuppressWarnings("unchecked")
	public AbstractJpaDao() {
		this.clazz = (Class<T>)((ParameterizedType) (getClass().getGenericSuperclass())).getActualTypeArguments()[0];
	}
	
	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}
    
	public T getOneAbstractJpa(final Long id){
		return em.find(clazz, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> getAllAbstractJpa(){
		return em.createQuery("from "+clazz.getName()).getResultList();
	}
}
