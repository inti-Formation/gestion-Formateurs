package com.adaming.myapp.socket;

import javax.faces.application.FacesMessage;

import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.impl.JSONEncoder;
/**
 * 
 * @author adel
 * @date 10/10/2016
 * @version 1.0.0
 * */
@PushEndpoint("/notify")
public class NotifyResource {
    @OnMessage(encoders={JSONEncoder.class})
	public FacesMessage OnMessage(FacesMessage message){
		return message;
	}
	
}
