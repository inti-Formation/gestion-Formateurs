package com.adaming.myapp.bean;

import java.util.Properties;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("mailBean")
@Scope(value="request")

public class SendMailBean {
	
	
	//attribut pour tester l'envoi
	final String username = "nymraif.stark8623@gmail.com";
	final String password = "krzmngkeebnkudvh";
	
	
	//
	
	private String msgSuccess;
    private UIComponent mybutton;
	private String to;
	private String from="nymraif.stark8623@gmail.com";
	
	private String objet;
	private String msg;
	
	public String sendMail() {
		
		
		System.out.println("destinataire : "+to +" from :"+from +" le message :"+msg +" obj : "+objet);
		 String host = "localhost";
		 Properties properties = System.getProperties();
		 properties.put("mail.smtp.auth", "true");
		 properties.put("mail.smtp.starttls.enable", "true");
		 properties.put("mail.smtp.host", "smtp.gmail.com");
		 properties.put("mail.smtp.port", "587");
		  msgSuccess=null;
	     //properties.setProperty("mail.smtp.host", host);
	    // Session session = Session.getDefaultInstance(properties);
	     
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
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	         // Set Subject: header field
	         message.setSubject(objet);

	         // Now set the actual message
	         message.setText(msg);

	         // Send message
	         Transport.send(message);
	         /*System.out.println("Sent message successfully....");
	         FacesMessage msgVal = new FacesMessage("Sent message successfully");
	         FacesContext context = FacesContext.getCurrentInstance();
	         context.addMessage(mybutton.getClientId(context), msgVal);*/
	         setMsgSuccess("Sent message successfully");
	         System.out.println(msgSuccess);
	         
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	     return "mail_success?redirect=true";
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getObjet() {
		return objet;
	}

	public void setObjet(String objet) {
		this.objet = objet;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	
	
	
	public String getMsgSuccess() {
		return msgSuccess;
	}

	public void setMsgSuccess(String msgSuccess) {
		this.msgSuccess = msgSuccess;
	}

	public UIComponent getMybutton() {
		return mybutton;
	}

	public void setMybutton(UIComponent mybutton) {
		this.mybutton = mybutton;
	}

	public SendMailBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
}
