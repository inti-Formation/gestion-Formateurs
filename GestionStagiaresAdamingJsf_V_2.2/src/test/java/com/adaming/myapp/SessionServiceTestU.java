package com.adaming.myapp;

import static org.junit.Assert.*;

import java.util.Date;

import junit.framework.Assert;

import org.hamcrest.core.IsEqual;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adaming.myapp.entities.SessionEtudiant;
import com.adaming.myapp.exception.AddSessionException;
import com.adaming.myapp.session.service.ISessionService;
import com.adaming.myapp.specialite.service.ISpecialiteService;

public class SessionServiceTestU {

	private static ISessionService serviceSession;
	private static ClassPathXmlApplicationContext context;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		serviceSession = (ISessionService) context.getBean("SessionService");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}

	@Before
	@Test
	public void testAddSessionStudent() throws AddSessionException {
		SessionEtudiant s = new SessionEtudiant(new Date(), new Date(), "Paris");
		serviceSession.addSessionStudent(s, 1L);
		SessionEtudiant s2 = new SessionEtudiant(new Date(), new Date(), "Toulouse");
		serviceSession.addSessionStudent(s2, 2L);
		assertNotNull(s.getIdSession());
		assertNotNull(s2.getIdSession());
	}

	@Test
	public void testUpdateSessionEtudian() {
		SessionEtudiant se = serviceSession.getSessionEtudiantById(1L);
		se.setLieu("Nantes");
		serviceSession.updateSessionEtudian(se, 1L);
		SessionEtudiant se2 = serviceSession.getSessionEtudiantById(1L);
		assertThat("Nantes", IsEqual.equalTo(se2.getLieu()));
	}

	@Test
	public void testGetSessionEtudiantById() {
		SessionEtudiant se = serviceSession.getSessionEtudiantById(2L);
		assertNotNull(se.getIdSession());
	}

}
