package com.adaming.myapp.question.service;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.entities.Question;
import com.adaming.myapp.exception.AddQuestionException;
import com.adaming.myapp.question.dao.IQuestionDao;
@Transactional(readOnly=true)
public class QuestionServiceImpl implements IQuestionService{
    
	private IQuestionDao dao;
	
	Logger log = Logger.getLogger("QuestionServiceImpl");
	
	public void setDao(IQuestionDao dao) {
		this.dao = dao;
		log.info("<--------Dao Question Injected----->");
	}

	@Override
	@Transactional(readOnly=false)
	public Question addQuestion(Question q, Long idModule) throws AddQuestionException {
		List<Question> questions = null;
		questions = getAllQuestions();
		for(Question question:questions){
			if(q.getPropositionquestion().equals(question.getPropositionquestion())){
				throw new AddQuestionException("La Question "+q.getPropositionquestion()+" Existe Déja dans le Module "+question.getModule().getNomModule());
			}
			else if(q.getPremeiereReponse().equals(question.getPremeiereReponse())
					|| q.getPremeiereReponse().equals(question.getDouxiemeReponse())
					|| q.getPremeiereReponse().equals(question.getTroisiemeReponse())
					|| q.getPremeiereReponse().equals(question.getQuatriemeReponse())){
				throw new AddQuestionException("La réponse "+q.getPremeiereReponse()+" Existe Déja dans la Question "+question.getPropositionquestion()+"Veuillez changer la réponse..!");
			}
			else if(q.getDouxiemeReponse().equals(question.getDouxiemeReponse())
					|| q.getDouxiemeReponse().equals(question.getPremeiereReponse())
					|| q.getDouxiemeReponse().equals(question.getTroisiemeReponse())
					|| q.getDouxiemeReponse().equals(question.getQuatriemeReponse())){
				throw new AddQuestionException("La réponse "+q.getDouxiemeReponse()+" Existe Déja dans la Question "+question.getPropositionquestion()+"Veuillez changer la réponse..!");
			}
			else if(q.getTroisiemeReponse().equals(question.getTroisiemeReponse())
					|| q.getTroisiemeReponse().equals(question.getPremeiereReponse())
					|| q.getTroisiemeReponse().equals(question.getDouxiemeReponse())
					|| q.getTroisiemeReponse().equals(question.getQuatriemeReponse())){
				throw new AddQuestionException("La réponse "+q.getTroisiemeReponse()+" Existe Déja dans la Question "+question.getPropositionquestion()+"Veuillez changer la réponse..!");
			}
			else if(q.getQuatriemeReponse().equals(question.getQuatriemeReponse())
					|| q.getQuatriemeReponse().equals(question.getPremeiereReponse())
					|| q.getQuatriemeReponse().equals(question.getDouxiemeReponse())
					|| q.getQuatriemeReponse().equals(question.getTroisiemeReponse())){
				throw new AddQuestionException("La réponse "+q.getQuatriemeReponse()+" Existe Déja dans la Question "+question.getPropositionquestion()+"Veuillez changer la réponse..!");
			}
		}
		return dao.addQuestion(q, idModule);
	}

	@Override
	@Transactional(readOnly=false)
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
