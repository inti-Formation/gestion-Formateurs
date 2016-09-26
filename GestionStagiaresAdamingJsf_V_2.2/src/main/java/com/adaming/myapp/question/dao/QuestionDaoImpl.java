package com.adaming.myapp.question.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.QueryHint;

import com.adaming.myapp.entities.Module;
import com.adaming.myapp.entities.Question;
import com.adaming.myapp.exception.AddQuestionException;

public class QuestionDaoImpl implements IQuestionDao{
    
	@PersistenceContext
	private EntityManager em;
	
	Logger log = Logger.getLogger("QuestionDaoImpl");
	
	@Override
	public Question addQuestion(Question q, Long idModule) throws AddQuestionException {
		Module      m = em.find(Module.class,idModule);
		q.setModule(m);
		List<Question> questions = getAllQuestions();
		for(Question question:questions){
			if(q.getPropositionquestion().equals(question.getPropositionquestion())){
				throw new AddQuestionException("La Question "+q.getPropositionquestion()+" Existe Déja dans le Module "+question.getModule().getNomModule());
			}
		}
		em.persist(q);
		log.info("la quesion :"+q.getIdQuestion()+" a bien été ajoute");
		return q;
	}

	@Override
	public Question updateQuestion(Question q, Long idModule) {
		Module m = em.find(Module.class, idModule);
		q.setModule(m);
		em.merge(q);
		log.info("la question"+q.getIdQuestion()+" a bien été modifier");
		return q;
	}

	@Override
	public Question getQuestionById(Long idQuestion) {
		Question q = em.find(Question.class,idQuestion);
		log.info("la question "+q.getIdQuestion()+"a bien été recupérer");
		return q;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Question> getAllQuestions() {
		Query query = em.createQuery("from Question");
		log.info("il existe"+query.getResultList().size()+" Questions");
		return query.getResultList();
	}

	@Override
	public int nombreQuestionsByModule(Long idModule) {
		int nombreQuestions = 0;
		Module m = em.find(Module.class,idModule);
		List<Question> tabQuestions = m.getQuestions();
	    nombreQuestions = tabQuestions.size();
	    log.info("il existe "+nombreQuestions+" Questions"+" dans le module "+m.getNomModule());
		return nombreQuestions;
	}

	@Override
	public List<Question> getAllQuestionsByModule(Long idModule) {
		Module m = em.find(Module.class,idModule);
		List<Question> questions = m.getQuestions();
		log.info("la liste des Questions par Module");
		return questions;
	}

}
