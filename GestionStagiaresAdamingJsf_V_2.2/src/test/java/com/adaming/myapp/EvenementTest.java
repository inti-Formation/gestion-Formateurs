package com.adaming.myapp;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adaming.myapp.entities.Absence;
import com.adaming.myapp.entities.Entretien;
import com.adaming.myapp.entities.Evenement;
import com.adaming.myapp.entities.Retard;
import com.adaming.myapp.evenement.service.IEvenementService;
import com.adaming.myapp.module.service.IModuleService;

public class EvenementTest {

	private static IEvenementService serviceEvenement;
    private static ClassPathXmlApplicationContext context;
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		serviceEvenement=(IEvenementService)context.getBean("EvenementService");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}
	@Test
	public void addEntretien(){
		Entretien e =  new Entretien(null, null);
		serviceEvenement.addEntretien(e, 1L,1L);
	}
	
	@Test
	public void addAbsence(){
		Absence a =  new Absence(null, null);
		serviceEvenement.addAbsence(a,1L, 1L);
	}
	
	@Test
	public void addRetard(){
		Retard r =  new Retard(null, null);
		serviceEvenement.addRetard(r, 1L, 1L);
	}

}
