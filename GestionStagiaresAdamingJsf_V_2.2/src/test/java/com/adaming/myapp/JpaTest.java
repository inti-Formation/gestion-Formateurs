package com.adaming.myapp;

import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adaming.myapp.contrat.service.IContratService;
import com.adaming.myapp.entities.Contrat;
import com.adaming.myapp.etudiant.service.IEtudiantService;
import com.adaming.myapp.exception.VerificationInDataBaseException;

public class JpaTest {

	public static void main(String[] args) {
		 IContratService serviceContrat;
		 ClassPathXmlApplicationContext context;
		 
		 context = new ClassPathXmlApplicationContext(new String[]{"app.xml"});
		/* serviceContrat=(IContratService)context.getBean("ContratService");
		 try {
			serviceContrat.addContrat(new Contrat(new Date(), true),3L);
		} catch (VerificationInDataBaseException e) {
			System.out.println(e.getMessage());
		}*/
	}
}
