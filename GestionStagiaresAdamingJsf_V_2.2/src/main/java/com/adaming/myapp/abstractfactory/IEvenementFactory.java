package com.adaming.myapp.abstractfactory;

import com.adaming.myapp.entities.Evenement;

public interface IEvenementFactory {

	Evenement createEvent(String typeEvent);

}
