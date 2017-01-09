package com.adaming.myapp.bean;
import java.io.Serializable;

import com.adaming.myapp.entities.Etudiant;
import com.adaming.myapp.entities.Evenement;
import com.adaming.myapp.entities.Formateur;
import com.adaming.myapp.entities.Module;
import com.adaming.myapp.entities.Note;
import com.adaming.myapp.entities.Question;
import com.adaming.myapp.entities.Role;
import com.adaming.myapp.entities.Salle;
import com.adaming.myapp.entities.SessionEtudiant;
import com.adaming.myapp.entities.Site;
import com.adaming.myapp.entities.Specialite;
import com.adaming.myapp.entities.User;
import com.adaming.myapp.factory.manager.IFactory;
import com.adaming.myapp.factory.producer.EtudiantFactoryProducer;
import com.adaming.myapp.factory.producer.EvenementFactoryProducer;
import com.adaming.myapp.factory.producer.FormateurFactoryProducer;
import com.adaming.myapp.factory.producer.ModuleFactoryProducer;
import com.adaming.myapp.factory.producer.NoteFactoryProducer;
import com.adaming.myapp.factory.producer.QuestionFactoryProducer;
import com.adaming.myapp.factory.producer.RoleFactoryProducer;
import com.adaming.myapp.factory.producer.SalleFactoryProducer;
import com.adaming.myapp.factory.producer.SessionFactoryProducer;
import com.adaming.myapp.factory.producer.SiteFactoryProducer;
import com.adaming.myapp.factory.producer.SpecialiteFactoryProducer;
import com.adaming.myapp.factory.producer.UserFactoryProducer;


@SuppressWarnings("serial")
public class FactoryBean implements Serializable {

	private static final IFactory<Evenement> EVENEMENT_FACTORY 
	   = EvenementFactoryProducer.getFactoryImpl("EvenementFactoryImpl");
	
	private static final IFactory<Etudiant> ETUDIANT_FACTORY 
	   = EtudiantFactoryProducer.getFactoryImpl("EtudiantFactoryImpl");
	
	private static final IFactory<Formateur> FORMATEUR_FACTORY 
	   = FormateurFactoryProducer.getFactoryImpl("FormateurFactoryImpl");
	
	private static final IFactory<SessionEtudiant> SESSION_FACTORY 
	   = SessionFactoryProducer.getFactoryImpl("SessionFactoryImpl");
	
	private static final IFactory<Module> MODULE_FACTORY 
	   = ModuleFactoryProducer.getFactoryImpl("ModuleFactoryImpl");
	
	private static final IFactory<Question> QUESTION_FACTORY 
	   = QuestionFactoryProducer.getFactoryImpl("QuestionFactoryImpl");
	
	private static final IFactory<Role> ROLE_FACTORY 
	   = RoleFactoryProducer.getFactoryImpl("RoleFactoryImpl");
	
	private static final IFactory<User> USER_FACTORY 
	   = UserFactoryProducer.getFactoryImpl("UserFactoryImpl");
	
	private static final IFactory<Specialite> SEPCIALITE_FACTORY 
	   = SpecialiteFactoryProducer.getFactoryImpl("SpecialiteFactoryImpl");
    
	private static final IFactory<Note> NOTE_FACTORY 
	   = NoteFactoryProducer.getFactoryImpl("NoteFactoryImpl");
	
	private static final IFactory<Site> SITE_FACTORY 
	   = SiteFactoryProducer.getFactoryImpl("SiteFactoryImpl");
	
	private static final IFactory<Salle> SALLE_FACTORY 
	   = SalleFactoryProducer.getFactoryImpl("SalleFactoryImpl");
	/**
	 * @return the evenementFactory
	 */
	public static IFactory<Evenement> getEvenementFactory() {
		return EVENEMENT_FACTORY;
	}

	/**
	 * @return the etudiantFactory
	 */
	public static IFactory<Etudiant> getEtudiantFactory() {
		return ETUDIANT_FACTORY;
	}

	/**
	 * @return the formateurFactory
	 */
	public static IFactory<Formateur> getFormateurFactory() {
		return FORMATEUR_FACTORY;
	}

	/**
	 * @return the sessionFactory
	 */
	public static IFactory<SessionEtudiant> getSessionFactory() {
		return SESSION_FACTORY;
	}

	/**
	 * @return the moduleFactory
	 */
	public static IFactory<Module> getModuleFactory() {
		return MODULE_FACTORY;
	}

	/**
	 * @return the questionFactory
	 */
	public static IFactory<Question> getQuestionFactory() {
		return QUESTION_FACTORY;
	}

	/**
	 * @return the roleFactory
	 */
	public static IFactory<Role> getRoleFactory() {
		return ROLE_FACTORY;
	}

	/**
	 * @return the userFactory
	 */
	public static IFactory<User> getUserFactory() {
		return USER_FACTORY;
	}

	/**
	 * @return the sepcialiteFactory
	 */
	public static IFactory<Specialite> getSepcialiteFactory() {
		return SEPCIALITE_FACTORY;
	}

	/**
	 * @return the noteFactory
	 */
	public static IFactory<Note> getNoteFactory() {
		return NOTE_FACTORY;
	}

	/**
	 * @return the siteFactory
	 */
	public static IFactory<Site> getSiteFactory() {
		return SITE_FACTORY;
	}

	/**
	 * @return the salleFactory
	 */
	public static IFactory<Salle> getSalleFactory() {
		return SALLE_FACTORY;
	}

	
	
}
