package com.adaming.myapp.factory.impl;

import com.adaming.myapp.entities.Question;
import com.adaming.myapp.factory.manager.IFactory;

public class QuestionFactoryImpl implements IFactory<Question> {

	@Override
	public Question create(String object) {
		if("Question".equalsIgnoreCase(object)){
			return new Question();
		}
		return null;
	}

}
