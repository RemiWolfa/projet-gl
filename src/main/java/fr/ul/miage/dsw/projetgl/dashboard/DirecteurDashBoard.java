package fr.ul.miage.dsw.projetgl.dashboard;

import java.util.ArrayList;
import java.util.HashMap;

import fr.ul.miage.dsw.projetgl.Carte;
import fr.ul.miage.dsw.projetgl.IncorrectParam;
import fr.ul.miage.dsw.projetgl.Plat;
import fr.ul.miage.dsw.projetgl.Tools;
import fr.ul.miage.dsw.projetgl.Utilisateur;
import fr.ul.miage.dsw.projetgl.database.CarteCollection;
import fr.ul.miage.dsw.projetgl.database.PlatCollection;
import fr.ul.miage.dsw.projetgl.database.ReservationCollection;
import fr.ul.miage.dsw.projetgl.database.UserCollection;
import fr.ul.miage.dsw.projetgl.enumeration.TypeUtilisateur;

public class DirecteurDashBoard {

	public static void readCommand() {
		System.out.println("----------------");
		System.out.println("1. Créer un utilisateur");
		System.out.println("2. Modifier un utilisateur");
		System.out.println("3. Modifier la carte du jour");
		System.out.println("4. Connaitre le temps moyen que restent les clients");
		System.out.println("5. Avoir la liste des plats les plsu rentables");
		System.out.println("6. Quitter");

		int i=0;
		try {
			i = Tools.getIntegerInput();
		} catch (IncorrectParam e) {
			System.out.println(e.getMessage());
			readCommand();
		}
		switch(i) {
		case 1:
			DirecteurDashBoard.createUser();
			break;
		case 2:
			DirecteurDashBoard.modifyUser();
			break;
		case 3:
			DirecteurDashBoard.modifyCarte();
			break;
		case 4:
			DirecteurDashBoard.averageTime();
			break;
		case 5:
			break;
		case 6:
			return;
		}
		readCommand();
	}

	public static void modifyCarte() {
		Carte carte = Carte.getTodayCarte();
		System.out.println("Plats de la carte:");
		for(Plat plat : carte.getPlats()) {
			System.out.println(plat.nom);
		}
		DirecteurDashBoard.fillCarte(carte);
		carte.save();
	}
	
	public static void fillCarte(Carte carte) {
		Plat plat;
		while((plat = DirecteurDashBoard.readPlat()) != null) {
			if(carte.hasPlat(plat))
				System.out.println("Le plat est déjà à la carte!");
			else
				carte.addPlat(plat);
		}
	}

	public static void modifyUser() {
		ArrayList<Utilisateur> users = UserCollection.getAllUsers();
		for(int i = 0; i < users.size(); i++) {
			Utilisateur user = users.get(i);
			System.out.println((i+1)+". "+user.nom+" - "+user.typeUser);
		}
		System.out.println((users.size()+1)+". Retour");
		
		int input=0;
		try {
			input = Tools.getIntegerInput();
		} catch (IncorrectParam e) {
			System.out.println(e.getMessage());
			modifyUser();
		}
		if(input-1 < 0 && input > users.size())
			return;
		
		System.out.println("modifier :"+users.get(input-1).nom);
	}
	
	public static void bestMeats() {
		HashMap<String,Double> map = new HashMap<String, Double>();
		map= ReservationCollection.bestProfitability();
		int i=0;
		
		map.forEach((name, money)-> {
			int k=i+1;
			System.out.println(k+")Le plat "+name+" a rapporté : "+money+" euros.\n");
		});

	}
	
	public static void averageTime() {
		double time = ReservationCollection.averageReservationTime();
		double hours = time % 60;
		double minutes = time-(hours*60);
		System.out.println("Un client reste en moyenne "+hours+" heures et "+minutes+" minutes");
	}
	
	public static void createUser() {
		System.out.println("Identifiant:");
		String identifiant = Tools.getStringInput();
		if(UserCollection.exist(identifiant)) {
			Tools.error("L'identifiant existe déjà");
			return;
		}
		System.out.println("Nom:");
		String nom = Tools.getStringInput();

		TypeUtilisateur type = DirecteurDashBoard.getSelectUserType();
		Utilisateur utilisateur = new Utilisateur(identifiant, nom, type);
		if(utilisateur.save()) {
			System.out.println("Utilisateur bien enregistré!");
		}else {
			Tools.error("Problème lors de l'enregistrement");
		}
	}

	public static TypeUtilisateur getSelectUserType() {
		System.out.println("Sélectionnez le type d'utilisateur:");
		System.out.println("1. Directeur");
		System.out.println("2. Maître d'hôtel");
		System.out.println("3. Assistant de service");
		System.out.println("4. Cuisinier");
		System.out.println("5. Serveur");
		int i=0;
		try {
			i = Tools.getIntegerInput();
		} catch (IncorrectParam e) {
			System.out.println(e.getMessage());
			getSelectUserType();
		}

		switch(i) {
		case 1:
			return TypeUtilisateur.directeur;
		case 2:
			return TypeUtilisateur.maitre_hotel;
		case 3:
			return TypeUtilisateur.assistant_Service;
		case 4:
			return TypeUtilisateur.cuisinier;
		case 5:
			return TypeUtilisateur.serveur;
		default:
			System.out.println("invalide");
			return DirecteurDashBoard.getSelectUserType();
		}
	}

	
	public static Plat readPlat() {
		System.out.println("Rechercher plats dans : ");
		System.out.println("1. Catégorie");
		System.out.println("2. Par nom similaire");
		System.out.println("3. Terminer");

		int i=0;
		try {
			i = Tools.getIntegerInput();
		} catch (IncorrectParam e) {
			System.out.println(e.getMessage());
		}

		ArrayList<Plat> plats = new ArrayList<Plat>();

		switch(i) {
		case 1:
			//TODO
			break;
		case 2:
			System.out.println("Entrez un nom à rechercher:");
			String name = Tools.getStringInput();
			plats = PlatCollection.getPlatsByName(name);
			break;
		
		case 3:
			return null;
		}

		for(int j = 0; j < plats.size(); j++) {//j+1 pour l'utilisateur
			System.out.println((j+1)+". "+plats.get(j).nom);
		}

		System.out.println((plats.size()+1)+". Retour");

		try {
			int input=Tools.getIntegerInput();

			if(input-1 == plats.size()) {
				return null;
			}else {
				return plats.get(input-1);
			}
		}catch(ArrayIndexOutOfBoundsException | IncorrectParam e) {
			System.out.println("Nombre non valide");
			System.out.println(e.getMessage());
			readPlat();

		}
		return null;
	}
	
}
