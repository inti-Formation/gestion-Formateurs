package com.adaming.myapp.factory.manager;

import java.io.Serializable;
/**
 * 
 * @author adel
 * @date 10/10/2016
 * @version 1.0.0
 * */
public interface IFactory <T extends Serializable>{

	T create (final String object);
	
	
}
