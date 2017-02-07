package com.adaming.myapp.factory.producer;

import com.adaming.myapp.entities.Formateur;
import com.adaming.myapp.factory.impl.FormateurFactoryImpl;
import com.adaming.myapp.factory.manager.IFactory;

public final class FormateurFactoryProducer {

	public final static IFactory<Formateur> getFactoryImpl(String typeImpl){
		if("FormateurFactoryImpl".equalsIgnoreCase(typeImpl)){
			return new FormateurFactoryImpl();
		}
		return null;
	}
}
