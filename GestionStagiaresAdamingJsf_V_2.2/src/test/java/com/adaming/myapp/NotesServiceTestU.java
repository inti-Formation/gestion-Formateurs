package com.adaming.myapp;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adaming.myapp.entities.Note;
import com.adaming.myapp.notes.service.INotesService;

public class NotesServiceTestU {
    
	private static INotesService serviceNotes;
	private static ClassPathXmlApplicationContext applicationContext;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	   applicationContext = new ClassPathXmlApplicationContext("app.xml");
	   serviceNotes=(INotesService)applicationContext.getBean("NotesService");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		applicationContext.close();
	}
	
	@Test
	@Ignore
	public void getAllNoteByStudent(){
		List<Note> notes =  serviceNotes.getAllNotesByStudent(1L);
		System.out.println(notes);
		
	}
	
	@Test
	public void getMoyenne(){
		Double d = serviceNotes.getMoyenne(1L,2L);
		System.out.println(d);
	}

	

}
