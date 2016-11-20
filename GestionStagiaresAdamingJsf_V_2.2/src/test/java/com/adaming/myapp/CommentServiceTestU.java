package com.adaming.myapp;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.hamcrest.core.IsEqual;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adaming.myapp.comment.service.ICommentService;
import com.adaming.myapp.entities.Comment;
import com.adaming.myapp.entities.Etudiant;
import com.adaming.myapp.etudiant.service.IEtudiantService;
import com.adaming.myapp.exception.AddEtudiantException;

public class CommentServiceTestU {

	private static ICommentService serviceComment;
    private static ClassPathXmlApplicationContext context;
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		serviceComment=(ICommentService)context.getBean("CommentService");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}
	
	@Test
	public void testAddComment() {
		Comment c = new Comment("dfghjk", new Date(), "ffff");
	    serviceComment.addComment(c);
	}
}
