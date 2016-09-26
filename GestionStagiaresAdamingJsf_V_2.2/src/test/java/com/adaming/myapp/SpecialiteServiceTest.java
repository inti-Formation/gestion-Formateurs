package com.adaming.myapp;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;


import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsEqual;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adaming.myapp.entities.Specialite;
import com.adaming.myapp.exception.AddSpecialiteException;
import com.adaming.myapp.specialite.service.ISpecialiteService;

public class SpecialiteServiceTest {


    private static ISpecialiteService serviceSpecialite;
    private static ClassPathXmlApplicationContext context;
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		serviceSpecialite=(ISpecialiteService)context.getBean("SpecialiteService");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}
	
	@Test
	@Before
	public void testAddSpecialite() throws AddSpecialiteException {
		Specialite sp = new Specialite("Développement Java");
	    serviceSpecialite.addSpecialite(sp);
	    assertNotNull(sp.getIdSpecialite());
	}
   
	@Test
	public void testUpdateSpecialite() {
		Specialite sp = serviceSpecialite.getSpecialiteById(1L);
		sp.setDesignation("Informatique");
		serviceSpecialite.updateSpecialite(sp);
		Specialite sp2= serviceSpecialite.getSpecialiteById(1L);
		assertThat("Informatique", IsEqual.equalTo(sp2.getDesignation()));
	}
    @Test
	public void testGetSpecialiteById() {
    	Specialite sp = serviceSpecialite.getSpecialiteById(1L);
    	Assert.assertTrue(sp.getIdSpecialite() ==1L);
	}
	
}
