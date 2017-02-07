package com.adaming.myapp.factory.producer;

import com.adaming.myapp.entities.Reponses;
import com.adaming.myapp.factory.impl.ReponsesFactoryImpl;
import com.adaming.myapp.factory.manager.IFactory;

public final class ReponsesFactoryProducer {

	public final static IFactory<Reponses> getFactoryImpl(String typeImpl){
		if("ReponsesFactoryImpl".equalsIgnoreCase(typeImpl)){
			return new ReponsesFactoryImpl();
		}
		return null;
	}
}
