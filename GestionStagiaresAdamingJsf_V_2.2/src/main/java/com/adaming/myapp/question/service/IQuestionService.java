package com.adaming.myapp.question.service;

import java.util.List;
import java.util.Set;
import com.adaming.myapp.entities.Questions;
import com.adaming.myapp.entities.Reponses;
import com.adaming.myapp.exception.AddQuestionException;
import com.adaming.myapp.exception.VerificationInDataBaseException;

public interface IQuestionService {

	Questions addQuestions(final Questions q, final Long idModule,final List<Reponses> reponses)
			throws VerificationInDataBaseException;

	Set<Questions> getQuestionsByModule(final Long idModule);

	Questions verifyExistingQuestions(final String label);

	Set<Reponses> getAllReponsesByModule(final Long idModule);
}
