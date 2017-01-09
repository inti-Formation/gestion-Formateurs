package com.adaming.myapp.factory.producer;

import com.adaming.myapp.entities.Etudiant;
import com.adaming.myapp.entities.Formateur;
import com.adaming.myapp.factory.impl.EtudiantFactoryImpl;
import com.adaming.myapp.factory.impl.FormateurFactoryImpl;
import com.adaming.myapp.factory.manager.IFactory;

public class FormateurFactoryProducer {

	public static IFactory<Formateur> getFactoryImpl(String typeImpl){
		if("FormateurFactoryImpl".equalsIgnoreCase(typeImpl)){
			return new FormateurFactoryImpl();
		}
		return null;
	}
}
