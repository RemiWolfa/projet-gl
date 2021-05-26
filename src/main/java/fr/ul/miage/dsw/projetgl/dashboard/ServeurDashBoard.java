package fr.ul.miage.dsw.projetgl.dashboard;

import java.util.ArrayList;
import java.util.List;

import fr.ul.miage.dsw.projetgl.Commande;
import fr.ul.miage.dsw.projetgl.IncorrectParam;
import fr.ul.miage.dsw.projetgl.Plat;
import fr.ul.miage.dsw.projetgl.Serveur;
import fr.ul.miage.dsw.projetgl.Table;
import fr.ul.miage.dsw.projetgl.Tools;
import fr.ul.miage.dsw.projetgl.Utilisateur;
import fr.ul.miage.dsw.projetgl.database.CarteCollection;
import fr.ul.miage.dsw.projetgl.database.PlatCollection;

public class ServeurDashBoard {

	public static void readCommand() {
		System.out.println("----------------");
		System.out.println("1. Afficher les tables");
		System.out.println("2. Afficher l'état des tables");
		System.out.println("3. Créer une commande");

		int i = Tools.getIntegerInput();
		switch(i) {
		case 1:
			ServeurDashBoard.showTables();
			break;
		case 2:
			ServeurDashBoard.showTableStates();
			break;
		case 3:
			ServeurDashBoard.createOrder();
			return;
		}
		readCommand();
	}

	public static void showTableStates() {
		Serveur serveur = (Serveur) Utilisateur.connectedUser;
		List<Table> tables = serveur.getTablesFromDB();
		for(Table table : tables) {
			System.out.println("Table numéro:"+table.num+" : "+table.etat);
		}
	}
	
	public static Plat readPlat() {
		System.out.println("Rechercher plats dans : ");
		System.out.println("1. Catégorie");
		System.out.println("2. Carte du jour");
		System.out.println("3. Par nom similaire");
		System.out.println("4. Par nom exact");
		System.out.println("5. Terminer");

		int i = Tools.getIntegerInput();

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
			int input = Tools.getIntegerInput();
			if(input-1 == plats.size()) {
				System.out.println("ici");
				return null;
			}else {
				System.out.println("là");
				return plats.get(input-1);
			}
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Nombre non valide");
		}
		return null;
	}


	public static void createOrder() {
		Commande commande = new Commande(1);//utiliser une reservation valide
		Plat plat = ServeurDashBoard.readPlat();

		while(plat != null) {
			System.out.println(plat.nom);
			plat = ServeurDashBoard.readPlat();
		}
	}
	public static void showTables() {

	}

}
