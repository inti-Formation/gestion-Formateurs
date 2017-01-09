package com.adaming.myapp.factory.impl;

import com.adaming.myapp.entities.Site;
import com.adaming.myapp.factory.manager.IFactory;

public class SiteFactoryImpl implements IFactory<Site> {

	@Override
	public Site create(String object) {
		if("Site".equalsIgnoreCase(object)){
			return new Site();
		}
		return null;
	}

}
