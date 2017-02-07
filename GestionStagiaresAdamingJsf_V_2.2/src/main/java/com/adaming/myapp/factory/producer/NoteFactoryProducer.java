package com.adaming.myapp.factory.producer;
import com.adaming.myapp.entities.Note;
import com.adaming.myapp.factory.impl.NoteFactoryImpl;
import com.adaming.myapp.factory.manager.IFactory;

public final class NoteFactoryProducer {

	public final static IFactory<Note> getFactoryImpl(String typeImpl){
		if("NoteFactoryImpl".equalsIgnoreCase(typeImpl)){
			return new NoteFactoryImpl();
		}
		return null;
	}
}
