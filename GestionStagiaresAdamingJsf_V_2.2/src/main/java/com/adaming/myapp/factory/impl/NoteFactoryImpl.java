package com.adaming.myapp.factory.impl;

import com.adaming.myapp.entities.Note;
import com.adaming.myapp.factory.manager.IFactory;

public final class NoteFactoryImpl implements IFactory<Note>{

	@Override
	public final Note create(final String object) {
		if("Note".equalsIgnoreCase(object)){
			return new Note();
		}
		return null;
	}

}
