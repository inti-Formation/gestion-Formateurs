package com.adaming.myapp.tools;

import java.util.Comparator;

import com.adaming.myapp.entities.Question;

public class MyComparator implements Comparator<Question> {

	/**
	 * compare la liste des réponses et rearange en crescendo
	 */
	@Override
	public int compare(Question q1, Question q2) {
		if (q1.getNumQuestion() > q2.getNumQuestion()) {
			return 1;
		} else if (q1.getNumQuestion() < q2.getNumQuestion()) {
			return -1;
		}
		return 0;
	}

}