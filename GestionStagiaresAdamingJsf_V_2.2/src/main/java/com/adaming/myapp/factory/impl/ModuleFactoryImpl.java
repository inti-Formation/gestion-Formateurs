package com.adaming.myapp.factory.impl;

import com.adaming.myapp.entities.Module;
import com.adaming.myapp.factory.manager.IFactory;

public final class ModuleFactoryImpl implements IFactory<Module> {

	@Override
	public final Module create(final String object) {
		if("Module".equalsIgnoreCase(object)){
			return new Module();
		}
		return null;
	}

}
