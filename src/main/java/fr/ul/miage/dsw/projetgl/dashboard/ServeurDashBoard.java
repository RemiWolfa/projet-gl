package fr.ul.miage.dsw.projetgl.dashboard;

import java.util.List;

import fr.ul.miage.dsw.projetgl.IncorrectParam;
import fr.ul.miage.dsw.projetgl.Plat;
import fr.ul.miage.dsw.projetgl.Serveur;
import fr.ul.miage.dsw.projetgl.Table;
import fr.ul.miage.dsw.projetgl.Tools;
import fr.ul.miage.dsw.projetgl.Utilisateur;

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


	public static void createCommand() {
		System.out.println("Voici les différents plats : \n" + Plat.listePlats());
		String nom="";

		while(nom!="1") {
			System.out.println("Quel est le nom du plat à ajouter à la commande ?\n Ecrivez 1 pour quitter.");
			nom = Tools.getStringInput();
			Boolean verif = false;
			while(!verif) {
				
				try {
					verif = Plat.checkNomPlat(nom);
				}
				catch(IncorrectParam e) {
					System.out.println(e.getMessage());
					nom = Tools.getStringInput();
				}
			}
			Plat plat =
		}

	}
	public static void showTables() {

	}

}
