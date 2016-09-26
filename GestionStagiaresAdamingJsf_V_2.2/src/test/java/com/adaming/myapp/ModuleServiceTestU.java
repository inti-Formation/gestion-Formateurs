package com.adaming.myapp;

import static org.junit.Assert.*;

import java.util.List;

import org.hamcrest.core.IsEqual;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adaming.myapp.entities.Module;
import com.adaming.myapp.exception.AddModuleException;
import com.adaming.myapp.module.service.IModuleService;

public class ModuleServiceTestU {

	private static IModuleService serviceModule;
    private static ClassPathXmlApplicationContext context;
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		serviceModule=(IModuleService)context.getBean("ModuleService");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}

	@Test
	@Ignore
	public void testAddModule() {
		Module m= new Module("Java");
		try {
			serviceModule.addModule(m,2L);
			assertNotNull(m.getIdModule());
		} catch (AddModuleException e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Test
	@Ignore
	public void testGetModuleById() {
		Module m  =serviceModule.getModuleById(1L);
		assertNotNull(m.getIdModule());
	}

	@Test
	@Ignore
	public void testUpdateModule() {
		Module m  = serviceModule.getModuleById(1L);
		m.setNomModule("Hibernate");
		serviceModule.updateModule(m, 1L);
		Module m2 = serviceModule.getModuleById(1L);
		assertThat("Hibernate", IsEqual.equalTo(m2.getNomModule()));
	}

	@Test
	public void testGetModulesBySession() {
		List<Module> tabM= serviceModule.getModulesBySession(4L);
		assertTrue(tabM.size()>0);
		
	}

}
