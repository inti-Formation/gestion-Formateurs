package com.adaming.myapp;

import java.util.Random;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adaming.myapp.entities.Etudiant;
import com.adaming.myapp.entities.Formateur;
import com.adaming.myapp.entities.Module;
import com.adaming.myapp.entities.Note;
import com.adaming.myapp.entities.Question;
import com.adaming.myapp.entities.Role;
import com.adaming.myapp.entities.SessionEtudiant;
import com.adaming.myapp.entities.Specialite;
import com.adaming.myapp.entities.User;
import com.adaming.myapp.etudiant.service.IEtudiantService;
import com.adaming.myapp.factory.manager.IFactory;
import com.adaming.myapp.factory.producer.EtudiantFactoryProducer;
import com.adaming.myapp.factory.producer.FormateurFactoryProducer;
import com.adaming.myapp.factory.producer.ModuleFactoryProducer;
import com.adaming.myapp.factory.producer.NoteFactoryProducer;
import com.adaming.myapp.factory.producer.QuestionFactoryProducer;
import com.adaming.myapp.factory.producer.RoleFactoryProducer;
import com.adaming.myapp.factory.producer.SessionFactoryProducer;
import com.adaming.myapp.factory.producer.SpecialiteFactoryProducer;
import com.adaming.myapp.factory.producer.UserFactoryProducer;

public class Test {
    
	public static String generateSessionKey(int length){
		String alphabet = 
		        new String("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"); //9
		int n = alphabet.length(); //10

		String result = new String(); 
		Random r = new Random(); //11

		for (int i=0; i<length; i++) //12
		    result = result + alphabet.charAt(r.nextInt(n)); //13

		return result;
	}
	public static void main(String[] args) {
		//System.out.println(generateSessionKey(8));
		
		

	}
	
	
}
