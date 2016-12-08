package com.adaming.myapp.question.dao;

import java.util.List;
import com.adaming.myapp.entities.Question;
import com.adaming.myapp.exception.AddQuestionException;

public class QuestionDaoImpl extends QuestionAbstractJpa implements IQuestionDao{
    

	
	@Override
	public Question addQuestion(Question q, Long idModule) throws AddQuestionException {
		return addQuestionAbstractJpa(q, idModule);
	}

	@Override
	public Question updateQuestion(Question q, Long idModule) {
		return updateQuestionAbstractJpa(q, idModule);
	}

	@Override
	public Question getQuestionById(Long idQuestion) {
		return getQuestionByIdAbstractJpa(idQuestion);
	}

	@Override
	public List<Question> getAllQuestions() {
		return getAllQuestionsAbstractJpa();
	}

	@Override
	public int nombreQuestionsByModule(Long idModule) {
		return nombreQuestionsByModuleAbstractJpa(idModule);
	}

	@Override
	public List<Question> getAllQuestionsByModule(Long idModule) {
		return getAllQuestionsByModuleAbstractJpa(idModule);
	}

}
