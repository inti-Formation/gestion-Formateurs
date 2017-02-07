package com.adaming.myapp.factory.producer;

import com.adaming.myapp.entities.Salle;
import com.adaming.myapp.factory.impl.SalleFactoryImpl;
import com.adaming.myapp.factory.manager.IFactory;

public final class SalleFactoryProducer {

	public final static IFactory<Salle> getFactoryImpl(String typeImpl){
		if("SalleFactoryImpl".equalsIgnoreCase(typeImpl)){
			return new SalleFactoryImpl();
		}
		return null;
	}
}
