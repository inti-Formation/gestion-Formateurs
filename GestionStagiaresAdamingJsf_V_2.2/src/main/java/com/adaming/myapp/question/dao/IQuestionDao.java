package com.adaming.myapp.question.dao;

import java.util.List;

import com.adaming.myapp.entities.Question;
import com.adaming.myapp.exception.AddQuestionException;

public interface IQuestionDao {

	public Question addQuestion(Question q,Long idModule) throws AddQuestionException;
	
	public Question updateQuestion(Question q,Long idModule);
	
	public Question getQuestionById(Long idQuestion);
	
	public List<Question> getAllQuestions();
	
	public int nombreQuestionsByModule(Long idModule);
	
	public List<Question> getAllQuestionsByModule(Long idModule);
	
	
	
	
	
}
