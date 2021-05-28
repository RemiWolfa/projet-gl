package fr.ul.miage.dsw.projetgl.action;

import java.util.ArrayList;

import fr.ul.miage.dsw.projetgl.IncorrectParam;
import fr.ul.miage.dsw.projetgl.Plat;
import fr.ul.miage.dsw.projetgl.Tools;
import fr.ul.miage.dsw.projetgl.dashboard.DirecteurDashBoard;
import fr.ul.miage.dsw.projetgl.database.CarteCollection;
import fr.ul.miage.dsw.projetgl.database.PlatCollection;
import fr.ul.miage.dsw.projetgl.enumeration.TypeUtilisateur;

public class ActionHelper {
	
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
	
	public static Plat readPlatWithMenu() {
		System.out.println("Rechercher plats dans : ");
		System.out.println("1. Catégorie");
		System.out.println("2. Carte du jour");
		System.out.println("3. Par nom similaire");
		System.out.println("4. Par nom exact");
		System.out.println("5. Terminer");

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
			plats = CarteCollection.getToDayPlats();
			break;
		case 3:
			System.out.println("Entrez un nom à rechercher:");
			String name = Tools.getStringInput();
			plats = PlatCollection.getPlatsByName(name);
			break;
		case 4:
			System.out.println("Entrez un nom exact à rechercher:");
			String nom = Tools.getStringInput();
			Plat plat = Plat.trouverPlat(nom);
			return plat;
		case 5:
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
			return ActionHelper.getSelectUserType();
		}
	}

}
