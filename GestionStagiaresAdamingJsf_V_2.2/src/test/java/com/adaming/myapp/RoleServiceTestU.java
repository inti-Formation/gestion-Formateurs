package com.adaming.myapp;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adaming.myapp.entities.Role;
import com.adaming.myapp.etudiant.service.IEtudiantService;
import com.adaming.myapp.role.service.IRoleService;

public class RoleServiceTestU {

	private static IRoleService serviceRole;
    private static ClassPathXmlApplicationContext context;
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		serviceRole=(IRoleService)context.getBean("RoleService");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}
	@Test
	public void testAddRole(){
		Role r = new Role("ROLE_ADMIN1");
		serviceRole.saveRole(r,2L);
	}

}
