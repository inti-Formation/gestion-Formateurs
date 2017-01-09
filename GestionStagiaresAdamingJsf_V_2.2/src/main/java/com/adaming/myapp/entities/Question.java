package com.adaming.myapp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Question implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idQuestion;
	@Column(length = 1000)
	private String propositionquestion;
	@Column(length = 500)
	private String premeiereReponse;
	@Column(length = 500)
	private String douxiemeReponse;
	@Column(length = 500)
	private String troisiemeReponse;
	@Column(length = 500)
	private String quatriemeReponse;
	@Column(length = 500)
	private String premeiereBonneReponse;
	@Column(length = 500)
	private String douxiemeBonneReponse;
	@Column(length = 500)
	private String troisiemeBonneReponse;
	@Column(length = 500)
	private String quatriemeBonneReponse;

	private int numQuestion;

	@Transient
	private int choixReponse;

	@Transient
	private String strReponse;

	@Transient
	private int point;

	/* Assoc */
	@ManyToOne
	@JoinColumn(name = "ID_MO_QUESTION")
	private Module module;

	/* construct */
	public Question(String propositionquestion, String premeiereReponse,
			String douxiemeReponse, String troisiemeReponse,
			String quatriemeReponse) {
		super();
		this.propositionquestion = propositionquestion;
		this.premeiereReponse = premeiereReponse;
		this.douxiemeReponse = douxiemeReponse;
		this.troisiemeReponse = troisiemeReponse;
		this.quatriemeReponse = quatriemeReponse;
	}

	public Question(String propositionquestion, String premeiereReponse,
			String douxiemeReponse, String troisiemeReponse,
			String quatriemeReponse, String premeiereBonneReponse,
			String douxiemeBonneReponse, String troisiemeBonneReponse,
			String quatriemeBonneReponse) {
		super();
		this.propositionquestion = propositionquestion;
		this.premeiereReponse = premeiereReponse;
		this.douxiemeReponse = douxiemeReponse;
		this.troisiemeReponse = troisiemeReponse;
		this.quatriemeReponse = quatriemeReponse;
		this.premeiereBonneReponse = premeiereBonneReponse;
		this.douxiemeBonneReponse = douxiemeBonneReponse;
		this.troisiemeBonneReponse = troisiemeBonneReponse;
		this.quatriemeBonneReponse = quatriemeBonneReponse;
	}

	// constructeur pour reponse
	public Question(int numQuestion, String propositionquestion,
			int choixReponse, String premeiereReponse, String douxiemeReponse,
			String troisiemeReponse, String quatriemeReponse,
			String premeiereBonneReponse, String douxiemeBonneReponse,
			String troisiemeBonneReponse, String quatriemeBonneReponse) {
		super();
		this.numQuestion = numQuestion;
		this.propositionquestion = propositionquestion;
		this.choixReponse = choixReponse;
		this.premeiereReponse = premeiereReponse;
		this.douxiemeReponse = douxiemeReponse;
		this.troisiemeReponse = troisiemeReponse;
		this.quatriemeReponse = quatriemeReponse;
		this.premeiereBonneReponse = premeiereBonneReponse;
		this.douxiemeBonneReponse = douxiemeBonneReponse;
		this.troisiemeBonneReponse = troisiemeBonneReponse;
		this.quatriemeBonneReponse = quatriemeBonneReponse;
	}

	public Question() {
		// TODO Auto-generated constructor stub
	}

	/* get and set */

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

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
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

	public int getNumQuestion() {
		return numQuestion;
	}

	public void setNumQuestion(int numQuestion) {
		this.numQuestion = numQuestion;
	}

	public int getChoixReponse() {
		return choixReponse;
	}

	public void setChoixReponse(int choixReponse) {
		this.choixReponse = choixReponse;
	}

	public String getStrReponse() {
		return strReponse;
	}

	public void setStrReponse(String strReponse) {
		this.strReponse = strReponse;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
	@Override
	public String toString() {
		return "Question [idQuestion=" + idQuestion + ", propositionquestion="
				+ propositionquestion + ", premeiereReponse="
				+ premeiereReponse + ", douxiemeReponse=" + douxiemeReponse
				+ ", troisiemeReponse=" + troisiemeReponse
				+ ", quatriemeReponse=" + quatriemeReponse + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + choixReponse;
		result = prime
				* result
				+ ((douxiemeBonneReponse == null) ? 0 : douxiemeBonneReponse
						.hashCode());
		result = prime * result
				+ ((douxiemeReponse == null) ? 0 : douxiemeReponse.hashCode());
		result = prime * result
				+ ((idQuestion == null) ? 0 : idQuestion.hashCode());
		result = prime * result + numQuestion;
		result = prime * result + point;
		result = prime
				* result
				+ ((premeiereBonneReponse == null) ? 0 : premeiereBonneReponse
						.hashCode());
		result = prime
				* result
				+ ((premeiereReponse == null) ? 0 : premeiereReponse.hashCode());
		result = prime
				* result
				+ ((propositionquestion == null) ? 0 : propositionquestion
						.hashCode());
		result = prime
				* result
				+ ((quatriemeBonneReponse == null) ? 0 : quatriemeBonneReponse
						.hashCode());
		result = prime
				* result
				+ ((quatriemeReponse == null) ? 0 : quatriemeReponse.hashCode());
		result = prime * result
				+ ((strReponse == null) ? 0 : strReponse.hashCode());
		result = prime
				* result
				+ ((troisiemeBonneReponse == null) ? 0 : troisiemeBonneReponse
						.hashCode());
		result = prime
				* result
				+ ((troisiemeReponse == null) ? 0 : troisiemeReponse.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (choixReponse != other.choixReponse)
			return false;
		if (douxiemeBonneReponse == null) {
			if (other.douxiemeBonneReponse != null)
				return false;
		} else if (!douxiemeBonneReponse.equals(other.douxiemeBonneReponse))
			return false;
		if (douxiemeReponse == null) {
			if (other.douxiemeReponse != null)
				return false;
		} else if (!douxiemeReponse.equals(other.douxiemeReponse))
			return false;
		if (idQuestion == null) {
			if (other.idQuestion != null)
				return false;
		} else if (!idQuestion.equals(other.idQuestion))
			return false;
		if (numQuestion != other.numQuestion)
			return false;
		if (point != other.point)
			return false;
		if (premeiereBonneReponse == null) {
			if (other.premeiereBonneReponse != null)
				return false;
		} else if (!premeiereBonneReponse.equals(other.premeiereBonneReponse))
			return false;
		if (premeiereReponse == null) {
			if (other.premeiereReponse != null)
				return false;
		} else if (!premeiereReponse.equals(other.premeiereReponse))
			return false;
		if (propositionquestion == null) {
			if (other.propositionquestion != null)
				return false;
		} else if (!propositionquestion.equals(other.propositionquestion))
			return false;
		if (quatriemeBonneReponse == null) {
			if (other.quatriemeBonneReponse != null)
				return false;
		} else if (!quatriemeBonneReponse.equals(other.quatriemeBonneReponse))
			return false;
		if (quatriemeReponse == null) {
			if (other.quatriemeReponse != null)
				return false;
		} else if (!quatriemeReponse.equals(other.quatriemeReponse))
			return false;
		if (strReponse == null) {
			if (other.strReponse != null)
				return false;
		} else if (!strReponse.equals(other.strReponse))
			return false;
		if (troisiemeBonneReponse == null) {
			if (other.troisiemeBonneReponse != null)
				return false;
		} else if (!troisiemeBonneReponse.equals(other.troisiemeBonneReponse))
			return false;
		if (troisiemeReponse == null) {
			if (other.troisiemeReponse != null)
				return false;
		} else if (!troisiemeReponse.equals(other.troisiemeReponse))
			return false;
		return true;
	}

	
}
