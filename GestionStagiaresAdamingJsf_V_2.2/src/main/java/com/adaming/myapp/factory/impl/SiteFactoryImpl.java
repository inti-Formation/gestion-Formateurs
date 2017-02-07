package com.adaming.myapp.factory.impl;

import com.adaming.myapp.entities.Site;
import com.adaming.myapp.factory.manager.IFactory;

public final class SiteFactoryImpl implements IFactory<Site> {

	@Override
	public final Site create(final String object) {
		if("Site".equalsIgnoreCase(object)){
			return new Site();
		}
		return null;
	}

}
