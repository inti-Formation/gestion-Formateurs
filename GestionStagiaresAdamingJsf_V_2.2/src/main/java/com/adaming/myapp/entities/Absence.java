package com.adaming.myapp.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("ABSENCE")
public class Absence extends Evenement{

	public Absence() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Absence(Date startDate, Date endDate) {
		super(startDate, endDate);
		// TODO Auto-generated constructor stub
	}

}
