package com.adaming.myapp.factory.impl;

import com.adaming.myapp.entities.Note;
import com.adaming.myapp.factory.manager.IFactory;

public class NoteFactoryImpl implements IFactory<Note>{

	@Override
	public Note create(String object) {
		if("Note".equalsIgnoreCase(object)){
			return new Note();
		}
		return null;
	}

}
