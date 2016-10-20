package com.adaming.myapp.abstractfactory;

public class EvenementFactoryProducer {

	public static IEvenementFactory getEvenementFactory(String typeFactory){
		
		if("EvenementFactoryImpl".equalsIgnoreCase(typeFactory)){
			return new EvenementFactoryImpl();
		}
		return null;
	}
}
