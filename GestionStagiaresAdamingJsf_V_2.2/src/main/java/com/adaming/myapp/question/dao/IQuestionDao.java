package com.adaming.myapp.question.dao;

import java.util.List;
import java.util.Set;
import com.adaming.myapp.entities.Questions;
import com.adaming.myapp.entities.Reponses;
/**
 * 
 * @author adel
 * @date 10/10/2016
 * @version 1.0.0
 * */
public interface IQuestionDao {

	 Questions addQuestions(final Questions q,final Long idModule,final List<Reponses> reponses);
	
	 Set<Questions> getQuestionsByModule(final Long idModule);
	
	 Questions verifyExistingQuestions(final String label);
	 
	 Set<Reponses> getAllReponsesByModule(final Long idModule);
	 
	
}
