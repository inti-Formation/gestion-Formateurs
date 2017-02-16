package com.adaming.myapp.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.persistence.Column;




import org.apache.commons.lang3.StringEscapeUtils;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.adaming.myapp.comment.service.ICommentService;
import com.adaming.myapp.entities.Comment;
/**
 * 
 * @author adel
 * @date 10/10/2016
 * @version 1.0.0
 * */
@Component("commentBean")
@Scope(value="session")
public class CommentBean {
	@Inject
    private ICommentService serviceComment;
	@Inject
	private UserAuthentificationBean userAuthentification;
	
	private String comment;
	private Date dateComment;
	private String pseudo;
	private Date dateSystem;
	private List<Comment> comments;
	private List<Comment> newComments= new ArrayList<Comment>();
	
	/*methode add Comment*/
	public void addComment(){
		dateSystem= new Date();
		Comment c = new Comment(comment, dateSystem, userAuthentification.getName());
	    serviceComment.addComment(c);
	    this.newComments.add(c);
	    comments=serviceComment.getAllComments();
	    notificarPush();
	}
	/*methode notificar push*/
	public void notificarPush(){
		String summary  = "nouveau message";
		String detail   = "ajouter";
		String channel  = "/notify";
		
		EventBus eventBus = EventBusFactory.getDefault().eventBus();
		eventBus.publish(channel, new FacesMessage(StringEscapeUtils.escapeHtml3(summary), StringEscapeUtils.escapeHtml3(detail)));
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getDateComment() {
		return dateComment;
	}
	public void setDateComment(Date dateComment) {
		this.dateComment = dateComment;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public Date getDateSystem() {
		return dateSystem;
	}
	public void setDateSystem(Date dateSystem) {
		this.dateSystem = dateSystem;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public List<Comment> getNewComments() {
		return newComments;
	}
	public void setNewComments(List<Comment> newComments) {
		this.newComments = newComments;
	}
	
	
	
	
}
