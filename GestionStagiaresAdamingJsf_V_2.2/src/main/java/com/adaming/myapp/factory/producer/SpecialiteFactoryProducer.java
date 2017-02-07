package com.adaming.myapp.factory.producer;

import com.adaming.myapp.entities.Specialite;
import com.adaming.myapp.factory.impl.SpecialiteFactoryImpl;
import com.adaming.myapp.factory.manager.IFactory;

public final class SpecialiteFactoryProducer {

	public final static IFactory<Specialite> getFactoryImpl(String typeImpl){
		if("SpecialiteFactoryImpl".equalsIgnoreCase(typeImpl)){
			return new SpecialiteFactoryImpl();
		}
		return null;
	}
}
