package com.adaming.myapp.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Formateur {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idFormateur;
	private String civilite;
	private String nom;
	private String prenom;
	private String adresse;
	private String codePostal;
	private String telMobile;
	private String mail;
	private String nationalite;
	private Date dateDeNaissance;
	private String lieuDeNaissance;
	private String specialite;
	
	
	
}
