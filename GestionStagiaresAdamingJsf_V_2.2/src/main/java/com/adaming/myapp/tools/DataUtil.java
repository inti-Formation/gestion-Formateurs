package com.adaming.myapp.tools;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author adel
 * @date 10/10/2016
 * @version 1.0.0
 * */
public final class DataUtil {

	/**
	 * @Autocomplete pour faire l'autocomplétion remplir le tableau des
	 * spécialité affecter à chaque formateur
	 * 
	 **/
	public static final List<String> fillingSpecialites(String query) {
		List<String> specialites = new ArrayList<String>();
		specialites.add("Accompagnateur du changement");
		specialites.add("Administrateur de bases de données");
		specialites.add("Administrateur réseaux");
		specialites.add("Administrateur sécurité");
		specialites.add("Analyste d'affaires");
		specialites.add("Architecte de données");
		specialites.add("Architecte logiciel");
		specialites.add("Chief Data Officer");
		specialites.add("Concepteur");
		specialites.add("Concepteur développeur informatique");
		specialites.add("Développeur Java");
		specialites.add("Développeur .Net");
		specialites.add("Développeur Php");
		specialites.add("Développeur Cobol");
		specialites.add("Chief Data Officer");
		specialites.add("Exploitation informatique");
		specialites.add("Informaticien de gestion");
		specialites.add("Intégrateur");
		specialites.add("Mainframe");
		specialites.add("Maîtrise d'ouvrage");
		specialites.add("Métiers du web");
		specialites.add("Responsable d'exploitation");
		specialites
				.add("Responsable de la sécurité des systèmes d'information");
		return specialites;
	}

	public static final String[] fillingNation(String query) {
		String[] nations = new String[] { "Afghan", "Albanais", "Algérien",
				"Allemand", "Américain", "Angolais", "Angolais", "Argentin",
				"Arménien", "Arubais", "Australien", "Autrichien", "Bahaméen",
				"Bangladais", "Belge", "Benin", "Biélorusse", "Bolivien",
				"Bosniaque", "Botswanais", "Bouthan", "Brésilien",
				"Britannique", "Bulgare", "Burkinabè", "Cambodgien",
				"Camerounais", "Canadien", "Chilien", "Chinois", "Chypriote",
				"Colombien", "Congolais", "Croate", "Cubain", "Danois",
				"Dominicain", "Ecossais", "Egyptien", "Equatorien", "Espagnol",
				"Estonien", "Ethiopien", "Européen", "Finlandais", "Français",
				"Drapeau", "Gabonais", "Georgien", "Ghanéen", "Grec",
				"Guatemala", "Guinéen", "Haïtien", "Hollandais", "Hongrois",
				"Indien", "Indonésien", "Irakien", "Iranien", "Irlandais",
				"Islandais", "Israélien", "Italien", "Ivoirien", "Jamaïcain",
				"Japonais", "Jordanien", "Kazakh", "Kenyan", "Kirghiz",
				"Kosovar", "Koweïtien", "Kurde", "Letton", "Libanais",
				"Libyen", "Liechtenstein", "Lituanien", "Luxembourgeois",
				"Macédonien", "Madagascar", "Malaisien", "Malien", "Maltais","Malgache",
				"Marocain", "Mauritanien", "Mauritien", "Mexicain",
				"Monégasque", "Mongol", "Mozambique", "Néo-Zélandais",
				"Népalais", "Nigérien", "Nord Coréen", "Norvégien",
				"Pakistanais", "Palestinien", "Panaméen", "Paraguayen",
				"Péruvien", "Philippiens", "Polonais", "Portoricain",
				"Portugais", "Qatarien", "Roumain", "Russe", "Rwandais",
				"Saoudien", "Sénégalais", "Serbe", "Serbo-Croate", "Singapour",
				"Slovaque", "Soviétique", "Sri-Lankais", "Sud-Africain",
				"Sud-Coréen", "Suédois", "Suisse", "Syrien", "Tadjik",
				"Taïwanais", "Tanzanien", "Tchadien", "Tchèque", "Thaïlandais",
				"Tunisien", "Turc", "Ukranien", "Uruguayen", "Vénézuélien",
				"Vietnamien", "Yougoslave", "Zimbabwéen" };
		return nations;
	}
	
	public static final String [] fillingVilles(String query){
		String [] villes = new String []{
        "Bourg-en-Bresse -01","Laon -02","Moulins -03","Digne-les-Bains -04","Gap -05","Nice -06","Privas -07","Charleville-Mézières -08","Foix -09","Troyes -10","Carcassonne -11","Rodez -12",
        "Marseille -13","Caen -14","Aurillac -15","Angoulême -16","La Rochelle -17","Bourges -18","19 - Tulle","20 - Ajaccio","21 - Dijon","22 - Saint-Brieuc","23 - Guéret",
        "Périgueux -24","Besançon -25","Valence -26","Évreux -27","Chartres -28","Quimper -29","Nîmes -30","Toulouse -31","Auch -32","Bordeaux -33","Montpellier -34",
        "Rennes -35","Châteauroux -36","Tours -37","Grenoble -38","Lons-le-Saunier -39","Mont-de-Marsan -40","Blois -41","Saint-Étienne -42","Le Puy-en-Velay -43","Nantes -44","Orléans -45",
        "Cahors -46","Agen -47","Mende -48","Angers -49","Saint-Lô -50","Châlons-en-Champagne -51","Chaumont -52","Laval -53","Nancy -54","Bar-le-Duc -55","Vannes -56",
        "Metz -57","Nevers -58","Lille -59","Beauvais -60","Alençon -61","Arras -62","Clermont-Ferrand -63","Pau -64","Tarbes -65","Perpignan -66","Strasbourg -67",
        "Colmar -68","Lyon -69","Vesoul -70","Mâcon -71","Le Mans -72","Chambéry -73","Annecy -74","Paris -75","Rouen -76","Melun -77","Versailles -78",
        "Niort -79","Amiens -80","Albi -81","Montauban -82","Toulon -83","Avignon -84","La Roche-sur-Yon -85","86 - Poitiers","87 - Limoges","88 - Épinal","89 - Auxerre",
        "Belfort -90","Évry -91","Nanterre -92","Bobigny -93","Créteil -94","Cergy(chef-lieu à Pontoise) -95"
		};
		return villes;
	}
}
