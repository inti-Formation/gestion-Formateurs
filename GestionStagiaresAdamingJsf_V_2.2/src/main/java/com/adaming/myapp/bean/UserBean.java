package com.adaming.myapp.bean;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
@Component("userBean")
@Scope(value="session")
public class UserBean implements Serializable{
	
	private String name =null;
	 
	public void getUserName(){
		 name = new String();
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	     String name = auth.getName();
	     System.out.println(name);
	 }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	 
	
     
     
}
