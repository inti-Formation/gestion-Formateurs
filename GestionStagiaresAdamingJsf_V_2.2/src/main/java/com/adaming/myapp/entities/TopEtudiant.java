package com.adaming.myapp.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("TOP")
public class TopEtudiant extends Evenement implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

	public TopEtudiant(String signaleur, Date curentDate) {
		super(signaleur, curentDate);
		// TODO Auto-generated constructor stub
	}



	public TopEtudiant() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	

}
