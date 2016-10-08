package com.adaming.myapp;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adaming.myapp.entities.Absence;
import com.adaming.myapp.entities.Entretien;
import com.adaming.myapp.entities.Evenement;
import com.adaming.myapp.entities.Retard;
import com.adaming.myapp.evenement.service.IEvenementService;
import com.adaming.myapp.exception.EvenementNotFoundException;
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
	@Ignore
	@Test
	public void addEntretien(){
		Entretien e =  new Entretien(new Date(),new Date(),new Date());
		serviceEvenement.addEntretien(e, 1L,1L);
	}
	@Ignore
	@Test
	public void addAbsence(){
		Absence a =  new Absence(new Date(),new Date(),new Date());
		serviceEvenement.addAbsence(a,1L, 1L);
	}
	@Ignore
	@Test
	public void addRetard(){
		Retard r =  new Retard(new Date(),new Date(),new Date());
		serviceEvenement.addRetard(r, 1L, 1L);
	}
	@Test
	public void getEntretien(){
		List<Evenement> evenements;
		try {
			evenements = serviceEvenement.getEvenementsEntretien();
			assertTrue(evenements.size()>0);
		} catch (EvenementNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
	}
	@Test
	public void getRetard(){
		List<Evenement> evenements;
		try {
			evenements = serviceEvenement.getEvenementsRetards();
			assertTrue(evenements.size()>0);
		} catch (EvenementNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}
	@Test
	public void getAbsence(){
		List<Evenement> evenements;
		try {
			evenements = serviceEvenement.getEvenementsAbsences();
			assertTrue(evenements.size()>0);
		} catch (EvenementNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
	}
	@Test
	public void getCurrentAbsences(){
		List<Evenement> evenements = serviceEvenement.getNumberOfCurrentsAbsence();
		
	}
	@Test
	public void getCurrentRetards(){
		List<Evenement> evenements = serviceEvenement.getNumberOfCurrentsRetards();
		
	}

}
