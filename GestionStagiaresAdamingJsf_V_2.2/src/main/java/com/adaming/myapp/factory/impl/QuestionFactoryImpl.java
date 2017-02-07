package com.adaming.myapp.factory.impl;

import com.adaming.myapp.entities.Questions;
import com.adaming.myapp.factory.manager.IFactory;

public final class QuestionFactoryImpl implements IFactory<Questions> {

	@Override
	public final Questions create(final String object) {
		if("Questions".equalsIgnoreCase(object)){
			return new Questions();
		}
		return null;
	}

}
