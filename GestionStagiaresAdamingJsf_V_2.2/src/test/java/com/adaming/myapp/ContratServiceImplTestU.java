package com.adaming.myapp;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.AssertTrue;

import org.hibernate.validator.internal.util.Contracts;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adaming.myapp.contrat.service.IContratService;
import com.adaming.myapp.entities.Contrat;
import com.adaming.myapp.entities.Etudiant;
import com.adaming.myapp.etudiant.service.IEtudiantService;
import com.adaming.myapp.exception.AddEtudiantException;
import com.adaming.myapp.exception.VerificationInDataBaseException;

public class ContratServiceImplTestU {

	private static IContratService serviceContrat;
    private static ClassPathXmlApplicationContext context;
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		serviceContrat=(IContratService)context.getBean("ContratService");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}
	@Test
	public void addContrat(){
		Contrat c = new Contrat(new Date(), true);
		try {
			serviceContrat.addContrat(c, 1L);
			assertNotNull(c);
		} catch (VerificationInDataBaseException e) {
			System.out.println(e.getMessage());
		}
		
	}
	@Test
	public void getnumbre(){
		System.out.println("Nombre of contrat"+serviceContrat.nombreContrat());
	}
	
	@Test
	@Ignore
	public void reomveContrat(){
		serviceContrat.remove(3L);
	}
	@Test
	public void getAllContrat(){
		List<Contrat> contrats = serviceContrat.getAll();
	    System.out.println("get All Test "+contrats);
	    for(Contrat c:contrats){
	    	System.out.println("idEtudiant"+c.getEtudiant().getIdEtudiant());
	    	System.out.println("etudiant : "+c.getEtudiant().getNomEtudiant());
	    }
	    //Assert.assertTrue(contacts.size()>0);
	}
	@Test
	public void getContanct(){
		Contrat c= serviceContrat.getOne(2L);
		System.out.println(c);
	    assertNotNull(c);
	}

}
