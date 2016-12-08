package com.adaming.myapp;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adaming.myapp.entities.Formateur;
import com.adaming.myapp.evenement.service.IEvenementService;
import com.adaming.myapp.exception.VerificationInDataBaseException;
import com.adaming.myapp.formateur.service.IFormateurService;

public class FormateurServiceTestU {

	private static IFormateurService serviceFormateur;
    private static ClassPathXmlApplicationContext context;
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		serviceFormateur=(IFormateurService)context.getBean("FormateurService");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}

	@Test
	public void addFormateur(){
		Formateur f = new Formateur("M", "nom", "prenom", "adresse", "codePostal", "telMobile", "mail", "nationalite", null, "lieuDeNaissance", "specialite");
		try {
			serviceFormateur.addFormateur(f);
		} catch (VerificationInDataBaseException e) {
			System.out.println(e.getMessage());
		}
	}
	@Test
	public void addFormateurToSession(){
		
		serviceFormateur.addFormateurToSession(1L, 1L);
	}
	@Test
	public void getAll(){
		serviceFormateur.getAllFormateurs();
	}
	
	@Test
	public void getOne(){
		serviceFormateur.getFormateurById(4L);
	}
}
