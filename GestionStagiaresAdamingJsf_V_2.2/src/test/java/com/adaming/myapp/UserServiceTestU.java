package com.adaming.myapp;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adaming.myapp.entities.User;
import com.adaming.myapp.specialite.service.ISpecialiteService;
import com.adaming.myapp.user.service.IUserService;

public class UserServiceTestU {

	 private static IUserService serviceUser;
	    private static ClassPathXmlApplicationContext context;
	    
		@BeforeClass
		public static void setUpBeforeClass() throws Exception {
			context = new ClassPathXmlApplicationContext("app.xml");
			serviceUser=(IUserService)context.getBean("UserService");
		}

		@AfterClass
		public static void tearDownAfterClass() throws Exception {
			context.close();
		}
		
		@Test
		public void addUser() {
			String motDePass = serviceUser.generateSessionKey(8);
			User u = new User("adel",motDePass,true);
			serviceUser.saveUser(u);
		}

}
