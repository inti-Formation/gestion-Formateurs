package com.adaming.myapp.question.dao;

import java.util.List;
import java.util.Set;
import com.adaming.myapp.entities.Questions;
import com.adaming.myapp.entities.Reponses;
import com.adaming.myapp.exception.AddQuestionException;

public class QuestionDaoImpl extends QuestionAbstractJpa implements
		IQuestionDao {

	@Override
	public Questions addQuestions(Questions q, Long idModule,
			List<Reponses> reponses) {
		// TODO Auto-generated method stub
		return addQuestionsAbstractJpa(q, idModule, reponses);
	}

	@Override
	public Set<Questions> getQuestionsByModule(Long idModule) {
		// TODO Auto-generated method stub
		return getQuestionsByModuleAbstracJpa(idModule);
	}

	@Override
	public Questions verifyExistingQuestions(String label) {
		// TODO Auto-generated method stub
		return verifyExistingQuestionsAbstractJpa(label);
	}

	@Override
	public Set<Reponses> getAllReponsesByModule(Long idModule) {
		// TODO Auto-generated method stub
		return getAllReponsesByModuleAbstractJpa(idModule);
	}

}
