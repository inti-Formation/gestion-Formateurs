package com.adaming.myapp.persistence;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * @author adel
 * @date 10/10/2016
 * @version 1.0.0
 * */
public interface IGenericDao <T extends Serializable>{

	List<T> getAll();

	T getOne(final Long id);
}
