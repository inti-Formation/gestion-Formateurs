package com.adaming.myapp.factory.producer;

import com.adaming.myapp.entities.Site;
import com.adaming.myapp.factory.impl.SiteFactoryImpl;
import com.adaming.myapp.factory.manager.IFactory;

public final class SiteFactoryProducer {

	public final static IFactory<Site> getFactoryImpl(String typeImpl){
		if("SiteFactoryImpl".equalsIgnoreCase(typeImpl)){
			return new SiteFactoryImpl();
		}
		return null;
	}
}
