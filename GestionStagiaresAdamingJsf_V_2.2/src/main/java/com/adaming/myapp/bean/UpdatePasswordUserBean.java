package com.adaming.myapp.bean;

import java.io.Serializable;
import java.util.Properties;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.FatalBeanException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.adaming.myapp.entities.User;
import com.adaming.myapp.exception.GetUserException;
import com.adaming.myapp.exception.VerificationInDataBaseException;
import com.adaming.myapp.tools.LoggerConfig;
import com.adaming.myapp.tools.Utilitaire;
import com.adaming.myapp.user.service.IUserService;
/**
 * 
 * @author adel
 * @date 10/10/2016
 * @version 1.0.0
 * */
@SuppressWarnings("serial")
@Component("updatePasswordUserBean")
@Scope(value="session")
public class UpdatePasswordUserBean implements Serializable{

	/**
	 * LOGGER LOG4j 
	 * @see org.apache.log4j.Logger
	 */
  
	@Inject
	private IUserService serviceUser;
	
	@Inject
	private UserAuthentificationBean autentificationBean;

	// attribut pour tester l'envoi
	private final String username = "nymraif.stark8623@gmail.com"; // adresse de
																	// l'administrateur
	private final String password = "krzmngkeebnkudvh"; // password pour
														// l'authentification
														// gmail
	//

	private String newPassword;
	private String passwordCrypted;

	public void updatePassword() {
      
		
        try {
			User user = serviceUser.getUserByMail(autentificationBean.getName());
			passwordCrypted = Utilitaire.passWordEncoderGenerator(newPassword);
			user.setPassword(passwordCrypted);
			serviceUser.customPassword(user);
            Utilitaire.displayMessageInfo(
					"Votre mot de passe à bien été enregistrer avec succès ");

		    
        } catch (VerificationInDataBaseException e) {
			Utilitaire.displayMessageWarning(e.getMessage());
		}
		

		Properties properties = System.getProperties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");

		Session session = Session.getInstance(properties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(username));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					autentificationBean.getName()));

			// Set Subject: header field
			message.setSubject("votre nouveau mot de passe");

			// Now set the actual message
			message.setText("Votre mot de passe INTI Formation a bien été modifié : " + newPassword);

			// Send message
			Transport.send(message);

			LoggerConfig.logInfo("Sent message successfully");
            reset();

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
	
	public void reset(){
		newPassword = "";
		passwordCrypted = "";
	}

	



	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public UserAuthentificationBean getAutentificationBean() {
		return autentificationBean;
	}

	public void setAutentificationBean(UserAuthentificationBean autentificationBean) {
		this.autentificationBean = autentificationBean;
	}

	

	
}
