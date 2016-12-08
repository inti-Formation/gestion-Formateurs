package com.adaming.myapp.question.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.adaming.myapp.entities.Module;
import com.adaming.myapp.entities.Question;
import com.adaming.myapp.exception.AddQuestionException;

public abstract class QuestionAbstractJpa {

	@PersistenceContext
	private EntityManager em;
	
	Logger log = Logger.getLogger("QuestionAbstractJpa");

	public Question addQuestionAbstractJpa(Question q, Long idModule) throws AddQuestionException {
		Module      m = em.find(Module.class,idModule);
		q.setModule(m);
		em.persist(q);
		log.info("la quesion :"+q.getIdQuestion()+" a bien été ajoute");
		return q;
	}

	public Question updateQuestionAbstractJpa(Question q, Long idModule) {
		Module m = em.find(Module.class, idModule);
		q.setModule(m);
		em.merge(q);
		log.info("la question"+q.getIdQuestion()+" a bien été modifier");
		return q;
	}

	public Question getQuestionByIdAbstractJpa(Long idQuestion) {
		Question q = em.find(Question.class,idQuestion);
		log.info("la question "+q.getIdQuestion()+"a bien été recupérer");
		return q;
	}


	@SuppressWarnings("unchecked")
	public List<Question> getAllQuestionsAbstractJpa() {
		Query query = em.createQuery("from Question");
		log.info("il existe"+query.getResultList().size()+" Questions");
		return query.getResultList();
	}


	public int nombreQuestionsByModuleAbstractJpa(Long idModule) {
		int nombreQuestions = 0;
		Module m = em.find(Module.class,idModule);
		List<Question> tabQuestions = m.getQuestions();
	    nombreQuestions = tabQuestions.size();
	    log.info("il existe "+nombreQuestions+" Questions"+" dans le module "+m.getNomModule());
		return nombreQuestions;
	}


	public List<Question> getAllQuestionsByModuleAbstractJpa(Long idModule) {
		Module m = em.find(Module.class,idModule);
		List<Question> questions = m.getQuestions();
		log.info("la liste des Questions par Module");
		return questions;
	}

}
