package com.adaming.myapp.factory.manager;

import java.io.Serializable;

public interface IFactory <T extends Serializable>{

	T create (String object);
	
	
}
