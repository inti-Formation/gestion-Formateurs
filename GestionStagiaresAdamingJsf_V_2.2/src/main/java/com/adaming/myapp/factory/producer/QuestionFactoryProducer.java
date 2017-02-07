package com.adaming.myapp.factory.producer;

import com.adaming.myapp.entities.Questions;
import com.adaming.myapp.factory.impl.QuestionFactoryImpl;
import com.adaming.myapp.factory.manager.IFactory;

public final class QuestionFactoryProducer {

	public final static IFactory<Questions> getFactoryImpl(String typeImpl){
		if("QuestionFactoryImpl".equalsIgnoreCase(typeImpl)){
			return new QuestionFactoryImpl();
		}
		return null;
	}
}
