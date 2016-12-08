package com.adaming.myapp.question.service;

import java.util.List;

import com.adaming.myapp.entities.Question;
import com.adaming.myapp.exception.AddQuestionException;

public interface IQuestionService {

	 Question addQuestion(Question q, Long idModule) throws AddQuestionException;

	 Question updateQuestion(Question q, Long idModule);
	
	 Question getQuestionById(Long idQuestion);
	
	 List<Question> getAllQuestions();
	
	 int nombreQuestionsByModule(Long idModule);
	
	 List<Question> getAllQuestionsByModule(Long idModule);

}
