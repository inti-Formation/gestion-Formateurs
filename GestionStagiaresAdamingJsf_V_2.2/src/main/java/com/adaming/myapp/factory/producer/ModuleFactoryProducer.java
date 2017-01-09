package com.adaming.myapp.factory.producer;

import com.adaming.myapp.entities.Module;
import com.adaming.myapp.factory.impl.ModuleFactoryImpl;
import com.adaming.myapp.factory.manager.IFactory;

public class ModuleFactoryProducer {

	public static IFactory<Module> getFactoryImpl(String typeImpl){
		if("ModuleFactoryImpl".equalsIgnoreCase(typeImpl)){
			return new ModuleFactoryImpl();
		}
		return null;
	}
}
