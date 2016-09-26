package com.adaming.myapp.question.service;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.entities.Question;
import com.adaming.myapp.exception.AddQuestionException;
import com.adaming.myapp.question.dao.IQuestionDao;
@Transactional
public class QuestionServiceImpl implements IQuestionService{
    
	private IQuestionDao dao;
	
	Logger log = Logger.getLogger("QuestionServiceImpl");
	
	public void setDao(IQuestionDao dao) {
		this.dao = dao;
		log.info("<--------Dao Question Injected----->");
	}

	@Override
	public Question addQuestion(Question q, Long idModule) throws AddQuestionException {
		// TODO Auto-generated method stub
		return dao.addQuestion(q, idModule);
	}

	@Override
	public Question updateQuestion(Question q,  Long idModule) {
		// TODO Auto-generated method stub
		return dao.updateQuestion(q, idModule);
	}

	@Override
	public Question getQuestionById(Long idQuestion) {
		// TODO Auto-generated method stub
		return dao.getQuestionById(idQuestion);
	}

	@Override
	public List<Question> getAllQuestions() {
		// TODO Auto-generated method stub
		return dao.getAllQuestions();
	}

	@Override
	public int nombreQuestionsByModule(Long idModule) {
		// TODO Auto-generated method stub
		return dao.nombreQuestionsByModule(idModule);
	}

	@Override
	public List<Question> getAllQuestionsByModule(Long idModule) {
		// TODO Auto-generated method stub
		return dao.getAllQuestionsByModule(idModule);
	}

}
