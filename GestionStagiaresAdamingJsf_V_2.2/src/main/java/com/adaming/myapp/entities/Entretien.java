package com.adaming.myapp.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("ENTRETIENT")
public class Entretien extends Evenement {

	public Entretien() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Entretien(Date startDate, Date endDate, Date currentDate) {
		super(startDate, endDate, currentDate);
		// TODO Auto-generated constructor stub
	}



}
