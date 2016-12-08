package com.adaming.myapp.persistence;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<T extends Serializable> {

	List<T> getAll();
	
	T getOne(final Long id);
}
