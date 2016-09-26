package com.adaming.myapp;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adaming.myapp.etudiant.service.IEtudiantService;

public class JpaTest {

	public static void main(String[] args) {
		
		 ClassPathXmlApplicationContext context;
		 
		 context = new ClassPathXmlApplicationContext(new String[]{"app.xml"});
	}
}
