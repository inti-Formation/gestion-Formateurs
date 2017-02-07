package com.adaming.myapp;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hamcrest.core.IsEqual;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.adaming.myapp.entities.Questions;
import com.adaming.myapp.entities.Reponses;
import com.adaming.myapp.exception.AddQuestionException;
import com.adaming.myapp.exception.VerificationInDataBaseException;
import com.adaming.myapp.module.service.IModuleService;
import com.adaming.myapp.question.service.IQuestionService;

public class QuestionServiceTestU {

	private static IQuestionService serviceqQuestion;
    private static ClassPathXmlApplicationContext context;
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		serviceqQuestion=(IQuestionService)context.getBean("QuestionService");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}

    
  
  
    @Test
    @Ignore
    public void addQuestion() throws VerificationInDataBaseException{
    	List<Reponses> reponses = new ArrayList<Reponses>();
    	Reponses r1 = new Reponses("4",false);
    	Reponses r2 = new Reponses("2.2",true);
    	Reponses r3 = new Reponses("6",false);
    	Reponses r4 = new Reponses("5",false);
    	reponses.add(r1);
    	reponses.add(r2);
    	reponses.add(r3);
    	reponses.add(r4);
    	Questions q = new Questions("Quelle est la version actuelle du java",1);
    	try {
			serviceqQuestion.addQuestions(q,1L,reponses);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @Test
    @Ignore
    public void getQu(){
    	Set<Questions> questions = (Set<Questions>) serviceqQuestion.getQuestionsByModule(1L);
    	Long idReponseSelected = 5L;
    	for(Questions q:questions)
    	{
    		for(Reponses r :q.getReponses()){
    			if(r.getIdReponse() == idReponseSelected && r.isEtat()){
    				System.out.println("Ok");
    			}
    		}
        }
    
    }
    @Test
    public void getAllReponses(){
    	Long ReponseSelected = 72L;
    	List<Reponses> resultats = new ArrayList<Reponses>();
    	Set<Reponses> getAllReponse = serviceqQuestion.getAllReponsesByModule(3L);
    	Reponses reponse = null;
    	for(Reponses r:getAllReponse){
    		if(ReponseSelected.equals(r.getIdReponse()) && r.isEtat())
    		{
    			reponse =r;
    			System.out.println( "OK "+ r + "" + r.getQuestions().getIdQuestions());
    		}
 
    	}
    	resultats.add(reponse);
    	System.out.println("resultats"+resultats);
    	
    }
}

