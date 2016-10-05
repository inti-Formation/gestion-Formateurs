package com.adaming.myapp.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("RETARD")
public class Retard extends Evenement {

	public Retard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Retard(Date startDate, Date endDate) {
		super(startDate, endDate);
		// TODO Auto-generated constructor stub
	}

	
}
