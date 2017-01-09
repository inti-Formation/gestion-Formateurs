package com.adaming.myapp.tools;

import java.util.ArrayList;
import java.util.List;

public class Filter {

	
	/**
	 * @param query
	 * @param nations
	 * @return
	 */
	public static  List<String> filterObject(String query, List<String> searchedObject) {
		List<String> filtred = new ArrayList<String>();
		for(int i=0;i<searchedObject.size();i++){
			String searche = searchedObject.get(i);
			if(searche.toLowerCase().startsWith(query)){
				filtred.add(searche);
			}
		}
		return filtred;
	}
}
