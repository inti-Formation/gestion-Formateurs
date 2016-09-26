package com.adaming.myapp;

import static org.junit.Assert.*;

import java.util.List;

import org.hamcrest.core.IsEqual;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adaming.myapp.entities.Question;
import com.adaming.myapp.exception.AddQuestionException;
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
	@Before
	@Ignore
	public void testAddQuestion() {
		Question q = new Question("c'est quoi jpa?","Class","Interface","specification", "implementation");
		try {
			serviceqQuestion.addQuestion(q, 1L);
			assertNotNull(q.getIdQuestion());
		} catch (AddQuestionException e) {
		System.out.println(e.getMessage());
		}
		
	}

	@Test
	@Ignore
	public void testUpdateQuestion() {
		Question q = serviceqQuestion.getQuestionById(1L);
		q.setPropositionquestion("Quelle est la version actuelle du jpa");
		serviceqQuestion.updateQuestion(q,1L);
		Question q2 = serviceqQuestion.getQuestionById(1L);
		assertThat("Quelle est la version actuelle du jpa",IsEqual.equalTo(q2.getPropositionquestion()));
	}
    @Test
    @Ignore
	public void testGetQuestionById(){
    	Question q2 = serviceqQuestion.getQuestionById(1L);
    	assertNotNull(q2.getIdQuestion());
	}
    
    @Test
    @Ignore
    public void getAllQuestions(){
    	serviceqQuestion.getAllQuestions();
    	
    }
    @Test
    public void getQuestionByModule(){
    	serviceqQuestion.nombreQuestionsByModule(1L);
    }
}
