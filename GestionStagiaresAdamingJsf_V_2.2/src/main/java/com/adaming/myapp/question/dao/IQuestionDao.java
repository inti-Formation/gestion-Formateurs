package com.adaming.myapp.question.dao;

import java.util.List;
import java.util.Set;
import com.adaming.myapp.entities.Questions;
import com.adaming.myapp.entities.Reponses;

public interface IQuestionDao {

	 Questions addQuestions(Questions q,Long idModule,List<Reponses> reponses);
	
	 Set<Questions> getQuestionsByModule(Long idModule);
	
	 Questions verifyExistingQuestions(String label);
	 
	 Set<Reponses> getAllReponsesByModule(Long idModule);
	 
	
}
