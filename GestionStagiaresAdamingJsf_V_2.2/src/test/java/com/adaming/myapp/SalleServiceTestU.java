package com.adaming.myapp;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adaming.myapp.entities.Salle;
import com.adaming.myapp.exception.VerificationInDataBaseException;
import com.adaming.myapp.salle.service.ISalleService;


public class SalleServiceTestU {

	private static ISalleService serviceSalle;
    private static ClassPathXmlApplicationContext context;
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		serviceSalle=(ISalleService)context.getBean("SalleService");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}

	@Test
	@Ignore
	public void testAdd() {
		Salle s = new Salle("505S", 18);
		try {
			serviceSalle.add(s, 2L);
		} catch (VerificationInDataBaseException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testGetSalleById() {
	  Salle s = serviceSalle.getOne(1L);
	  assertNotNull(s);
	}

	@Test
	public void testUpdateSalle() {
		 Salle s = serviceSalle.getOne(1L);
		 s.setNbPlace(50);
		 serviceSalle.update(s);
	}

	@Test
	public void testGetAllSalle() {
	
		List<Salle> salles = serviceSalle.getAll();
		assertTrue(salles.size()>0);
		
	}
	
	@Test
	public void testremoveSalle() {
		
	}
	
	
}
