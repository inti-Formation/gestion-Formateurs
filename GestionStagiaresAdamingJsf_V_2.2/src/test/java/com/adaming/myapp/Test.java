package com.adaming.myapp;

import java.util.Random;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adaming.myapp.entities.Etudiant;
import com.adaming.myapp.etudiant.service.IEtudiantService;

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
		Integer x = 5;
		Integer y = 5;
		Integer z = new Integer(5);
		System.out.println("x : "+x);
		System.out.println("y : "+y);
		System.out.println("z : "+z);
		System.out.println( "x @"+x.TYPE);
		System.out.println( "y @"+y.TYPE);
		System.out.println( "z @"+z.TYPE);
	}
	
	
}
