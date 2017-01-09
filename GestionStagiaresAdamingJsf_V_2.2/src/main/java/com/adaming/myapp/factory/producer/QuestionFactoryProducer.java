package com.adaming.myapp.factory.producer;

import com.adaming.myapp.entities.Question;
import com.adaming.myapp.factory.impl.QuestionFactoryImpl;
import com.adaming.myapp.factory.manager.IFactory;

public class QuestionFactoryProducer {

	public static IFactory<Question> getFactoryImpl(String typeImpl){
		if("QuestionFactoryImpl".equalsIgnoreCase(typeImpl)){
			return new QuestionFactoryImpl();
		}
		return null;
	}
}
