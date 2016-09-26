package com.adaming.myapp;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.language.bm.Rule.RPattern;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adaming.myapp.entities.Examen;
import com.adaming.myapp.examen.service.IExamenService;
import com.adaming.myapp.exception.AddExamenException;
import com.adaming.myapp.module.service.IModuleService;

public class ExamenServiceTestU {

	private static IExamenService serviceExamen;
    private static ClassPathXmlApplicationContext context;
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		serviceExamen=(IExamenService)context.getBean("ExamenService");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}

	@Test
	@Ignore
	public void testAddExamen() {
		Examen e = new Examen(new Date(), 1D, "ff");
		serviceExamen.addExamenV2(e, 1L, 1L, 1L);
	}
	@Test
	public void testgetScore(){
		serviceExamen.getScoreExamen(1L,1L,1L);
	}
	

}
