package com.adaming.myapp.question.service;

import java.util.List;
import java.util.Set;
import com.adaming.myapp.entities.Questions;
import com.adaming.myapp.entities.Reponses;
import com.adaming.myapp.exception.AddQuestionException;
import com.adaming.myapp.exception.VerificationInDataBaseException;

public interface IQuestionService {

	Questions addQuestions(Questions q, Long idModule, List<Reponses> reponses)
			throws VerificationInDataBaseException;

	Set<Questions> getQuestionsByModule(Long idModule);

	Questions verifyExistingQuestions(String label);

	Set<Reponses> getAllReponsesByModule(Long idModule);
}
