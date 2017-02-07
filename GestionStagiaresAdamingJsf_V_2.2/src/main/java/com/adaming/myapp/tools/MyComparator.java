package com.adaming.myapp.tools;

import java.util.Comparator;
import com.adaming.myapp.entities.Reponses;

public class MyComparator implements Comparator<Reponses> {

	/**
	 * compare la liste des réponses et rearange en crescendo
	 */
	@Override
	public int compare(Reponses r1, Reponses r2) {
		
		return r1.getQuestions().getNumeroQuestion() - r2.getQuestions().getNumeroQuestion();
	}

}