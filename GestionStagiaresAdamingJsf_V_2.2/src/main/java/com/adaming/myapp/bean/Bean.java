package com.adaming.myapp.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adaming.myapp.entities.Question;
import com.adaming.myapp.exception.AddQuestionException;
import com.adaming.myapp.question.service.IQuestionService;

@Component("bean")
@ViewScoped
public class Bean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IQuestionService serviceQuestion;

	private List<Question> m_lFields;
	private Long idQuestion;
	private Long idModule;
	private String propositionquestion;
	private String premeiereReponse;
	private String douxiemeReponse;
	private String troisiemeReponse;
	private String quatriemeReponse;
	private int nombreQuestionsByModule;
	private List<Question> questions;
	private String premeiereBonneReponse;
	private String douxiemeBonneReponse;
	private String troisiemeBonneReponse;
	private String quatriemeBonneReponse;

	/*method add question*/
	public void addQuestionV2() {
		Question q = new Question(propositionquestion, premeiereReponse,
				douxiemeReponse, troisiemeReponse, quatriemeReponse,
				premeiereBonneReponse, douxiemeBonneReponse,
				troisiemeBonneReponse, quatriemeBonneReponse);
		q.setNumQuestion(nombreQuestionsByModule + 1);
		try {
			serviceQuestion.addQuestion(q, idModule);
			getQuestionByModule();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success",
					"La Question :" + q.getPropositionquestion()
					+ " Ajouter Avec Success dans le Module N °" + idModule));
			propositionquestion="";
			premeiereReponse="";
			troisiemeReponse="";
			quatriemeReponse="";
			douxiemeReponse="";
			premeiereBonneReponse="";
			douxiemeBonneReponse="";
			troisiemeBonneReponse="";
			quatriemeBonneReponse="";
		} catch (AddQuestionException e) {
			FacesContext context2 = FacesContext.getCurrentInstance();
			context2.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Warning",e.getMessage()));		
		}
	}
	/*reset*/
	public void reset(){
		propositionquestion="";
		premeiereReponse="";
		troisiemeReponse="";
		quatriemeReponse="";
		douxiemeReponse="";
		premeiereBonneReponse="";
		douxiemeBonneReponse="";
		troisiemeBonneReponse="";
		quatriemeBonneReponse="";
	}
    /* get question by module*/
	public void getQuestionByModule() {
		nombreQuestionsByModule = serviceQuestion
				.nombreQuestionsByModule(idModule);
		questions = serviceQuestion.getAllQuestionsByModule(idModule);
	}

	public Bean() {
		m_lFields = new ArrayList();

		m_lFields.add(new Question());
	}

	public void setFields(List<Question> p_lFields) {
		m_lFields = p_lFields;
	}

	public List<Question> getFields() {
		return m_lFields;
	}

	
	public List<Question> getM_lFields() {
		return m_lFields;
	}

	public void setM_lFields(List<Question> m_lFields) {
		this.m_lFields = m_lFields;
	}

	public Long getIdQuestion() {
		return idQuestion;
	}

	public void setIdQuestion(Long idQuestion) {
		this.idQuestion = idQuestion;
	}

	public String getPropositionquestion() {
		return propositionquestion;
	}

	public void setPropositionquestion(String propositionquestion) {
		this.propositionquestion = propositionquestion;
	}

	public String getPremeiereReponse() {
		return premeiereReponse;
	}

	public void setPremeiereReponse(String premeiereReponse) {
		this.premeiereReponse = premeiereReponse;
	}

	public String getDouxiemeReponse() {
		return douxiemeReponse;
	}

	public void setDouxiemeReponse(String douxiemeReponse) {
		this.douxiemeReponse = douxiemeReponse;
	}

	public String getTroisiemeReponse() {
		return troisiemeReponse;
	}

	public void setTroisiemeReponse(String troisiemeReponse) {
		this.troisiemeReponse = troisiemeReponse;
	}

	public String getQuatriemeReponse() {
		return quatriemeReponse;
	}

	public void setQuatriemeReponse(String quatriemeReponse) {
		this.quatriemeReponse = quatriemeReponse;
	}

	public Long getIdModule() {
		return idModule;
	}

	public void setIdModule(Long idModule) {
		this.idModule = idModule;
	}

	public IQuestionService getServiceQuestion() {
		return serviceQuestion;
	}

	public void setServiceQuestion(IQuestionService serviceQuestion) {
		this.serviceQuestion = serviceQuestion;
	}

	public int getNombreQuestionsByModule() {
		return nombreQuestionsByModule;
	}

	public void setNombreQuestionsByModule(int nombreQuestionsByModule) {
		this.nombreQuestionsByModule = nombreQuestionsByModule;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public String getPremeiereBonneReponse() {
		return premeiereBonneReponse;
	}

	public void setPremeiereBonneReponse(String premeiereBonneReponse) {
		this.premeiereBonneReponse = premeiereBonneReponse;
	}

	public String getDouxiemeBonneReponse() {
		return douxiemeBonneReponse;
	}

	public void setDouxiemeBonneReponse(String douxiemeBonneReponse) {
		this.douxiemeBonneReponse = douxiemeBonneReponse;
	}

	public String getTroisiemeBonneReponse() {
		return troisiemeBonneReponse;
	}

	public void setTroisiemeBonneReponse(String troisiemeBonneReponse) {
		this.troisiemeBonneReponse = troisiemeBonneReponse;
	}

	public String getQuatriemeBonneReponse() {
		return quatriemeBonneReponse;
	}

	public void setQuatriemeBonneReponse(String quatriemeBonneReponse) {
		this.quatriemeBonneReponse = quatriemeBonneReponse;
	}

}
