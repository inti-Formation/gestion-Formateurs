package com.adaming.myapp.bean;

import java.io.Serializable;
import java.util.Collection;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.adaming.myapp.entities.User;
import com.adaming.myapp.exception.GetUserException;
import com.adaming.myapp.user.service.IUserService;

@Component("userAuthentification")
@Scope(value="session")
public class UserAuthentificationBean implements Serializable {
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private String name;
	private User user;
	
	public UserAuthentificationBean() throws GetUserException{
		user= new User();
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		System.out.println("User Details"+userDetails);
        name=userDetails.getUsername();
        System.out.println("name : "+name);
		if (context instanceof SecurityContext){
            Authentication authentication = context.getAuthentication();
            if (authentication instanceof Authentication){
                user.setName(((org.springframework.security.core.userdetails.User)authentication.getPrincipal()).getUsername());
            }
        }
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
