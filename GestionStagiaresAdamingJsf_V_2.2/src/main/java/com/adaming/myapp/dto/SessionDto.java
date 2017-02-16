package com.adaming.myapp.dto;

import java.util.Date;
/**
 * 
 * @author adel
 * @date 10/10/2016
 * @version 1.0.0
 * */
public class SessionDto {

	private Long idSession;
	private Date dateDebut;
	private Date dateFin;
	
	public SessionDto() {
		// TODO Auto-generated constructor stub
	}

	public SessionDto(Date dateDebut, Date dateFin) {
		super();
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}



	/**
	 * @return the idSession
	 */
	public Long getIdSession() {
		return idSession;
	}
	/**
	 * @param idSession the idSession to set
	 */
	public void setIdSession(Long idSession) {
		this.idSession = idSession;
	}
	/**
	 * @return the dateDebut
	 */
	public Date getDateDebut() {
		return dateDebut;
	}
	/**
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	/**
	 * @return the dateFin
	 */
	public Date getDateFin() {
		return dateFin;
	}
	/**
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	
	
}
