package com.adaming.myapp;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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

	

}
