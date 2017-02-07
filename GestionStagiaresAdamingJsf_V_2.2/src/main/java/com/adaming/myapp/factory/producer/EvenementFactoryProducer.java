package com.adaming.myapp.factory.producer;
import com.adaming.myapp.entities.Evenement;
import com.adaming.myapp.factory.impl.EvenementFactoryImpl;
import com.adaming.myapp.factory.manager.IFactory;

public final class EvenementFactoryProducer {

	public final static IFactory<Evenement> getFactoryImpl(String typeImpl){
		if("EvenementFactoryImpl".equalsIgnoreCase(typeImpl)){
			return new EvenementFactoryImpl();
		}
		return null;
	}
	
}
