package com.adaming.myapp.bean;

import java.io.Serializable;
import java.util.Properties;

import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.adaming.myapp.entities.User;
import com.adaming.myapp.exception.GetUserException;
import com.adaming.myapp.tools.LoggerConfig;
import com.adaming.myapp.user.service.IUserService;
/**
 * 
 * @author adel
 * @date 10/10/2016
 * @version 1.0.0
 * */
@SuppressWarnings("serial")
@Component("passBean")
@Scope(value = "session")
public class ForgetPassBean implements Serializable {
    
	/**
	 * LOGGER LOG4j 
	 * @see org.apache.log4j.Logger
	 */
  
    
	
	@Inject
	private IUserService serviceUser;

	// attribut pour tester l'envoi
	private final String username = "nymraif.stark8623@gmail.com"; // adresse de
																	// l'administrateur
	private final String password = "krzmngkeebnkudvh"; // password pour
														// l'authentification
														// gmail
	//

	private String mail;
	private String error;

	public String sendPass() {

		User u = FactoryBean.getUserFactory().create("User");

		try {
			u = serviceUser.getUser(mail);
		} catch (GetUserException e) {
			setError(e.getMessage());
			LoggerConfig.logDebug(error);
			return null;
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
					mail));

			// Set Subject: header field
			message.setSubject("mot de passe oubli�");

			// Now set the actual message
			message.setText(u.getPassword());

			// Send message
			Transport.send(message);

			LoggerConfig.logInfo("Sent message successfully");

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
		return "mail_success?redirect=true";
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public ForgetPassBean() {
		super();
	}

}
