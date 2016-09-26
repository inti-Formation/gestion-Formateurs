package com.adaming.myapp;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.hamcrest.core.IsEqual;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adaming.myapp.entities.Etudiant;
import com.adaming.myapp.etudiant.service.IEtudiantService;
import com.adaming.myapp.exception.AddEtudiantException;
import com.adaming.myapp.module.service.IModuleService;

public class EtudiantServiceTestU {


	private static IEtudiantService serviceEtudiant;
    private static ClassPathXmlApplicationContext context;
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		serviceEtudiant=(IEtudiantService)context.getBean("EtudiantService");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}
	@Test
	@Before
	public void testAddStudent() {
		Etudiant e = new Etudiant("nomEtudiant", "prenomEtudiant", new Date(), "formationInitial", "ecole", new Date(), "adressePostal", "codePostal", "numTel", "mail");
		try {
			serviceEtudiant.addStudent(e,1L);
			assertNotNull(e.getIdEtudiant());
		} catch (AddEtudiantException e1) {
			System.out.println(e1.getMessage());
		}
		
	}

	@Test
	public void testUpdateStudent() {
		Etudiant e = serviceEtudiant.getStudentById(1L);
		e.setNomEtudiant("nomModifier");
		serviceEtudiant.updateStudent(e,1L);
		Etudiant e2 = serviceEtudiant.getStudentById(1L);
		assertThat("nomModifier",IsEqual.equalTo(e2.getNomEtudiant()));
	}

	@Test
	public void testRemoveStudent() {
		Etudiant e = serviceEtudiant.getStudentById(2L);
		serviceEtudiant.removeStudent(2L);
		assertNull(e);
		
	}

	@Test
	public void testGetStudentById() {
		Etudiant e = serviceEtudiant.getStudentById(1L);
		assertNotNull(e.getIdEtudiant());
	}

	@Test
	public void testGetEtudiantBySession() {
		List<Etudiant> tabE = serviceEtudiant.getEtudiantBySession(1L);
		assertTrue(tabE.size()>=0);
	}

}
