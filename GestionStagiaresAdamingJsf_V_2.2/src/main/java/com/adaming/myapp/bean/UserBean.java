package com.adaming.myapp.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.adaming.myapp.entities.User;
import com.adaming.myapp.exception.GetUserException;
import com.adaming.myapp.user.service.IUserService;
import com.fasterxml.jackson.databind.Module.SetupContext;

@Component("userBean")
@Scope(value = "session")
public class UserBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	

	@Inject
	private IUserService serviceUser;
    
	private String userException;
	private String userSuccess;
	private String mail;
	private String password;
	private String newPassword;
    
	/*update password*/
	
	public void updatePassword(){
		
		try {
			serviceUser.updatePassword(mail, password, newPassword);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info","Votre nouveau mot de passe a bien été enregistré"));
		} catch (GetUserException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!",e.getMessage()));
		}
	}
	
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getUserException() {
		return userException;
	}

	public void setUserException(String userException) {
		this.userException = userException;
	}

	public String getUserSuccess() {
		return userSuccess;
	}

	public void setUserSuccess(String userSuccess) {
		this.userSuccess = userSuccess;
	}

	
}
