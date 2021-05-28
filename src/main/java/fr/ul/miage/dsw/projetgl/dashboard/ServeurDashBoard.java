package fr.ul.miage.dsw.projetgl.dashboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.ul.miage.dsw.projetgl.Commande;
import fr.ul.miage.dsw.projetgl.IncorrectParam;
import fr.ul.miage.dsw.projetgl.MatierePremiere;
import fr.ul.miage.dsw.projetgl.Plat;
import fr.ul.miage.dsw.projetgl.Reservation;
import fr.ul.miage.dsw.projetgl.Serveur;
import fr.ul.miage.dsw.projetgl.Table;
import fr.ul.miage.dsw.projetgl.Tools;
import fr.ul.miage.dsw.projetgl.Utilisateur;
import fr.ul.miage.dsw.projetgl.database.CarteCollection;
import fr.ul.miage.dsw.projetgl.database.PlatCollection;
import fr.ul.miage.dsw.projetgl.database.ReservationCollection;

public class ServeurDashBoard {

	public static void readCommand() {
		System.out.println("----------------");
		System.out.println("1. Afficher les tables");
		System.out.println("2. Afficher l'état des tables");
		System.out.println("3. Afficher les clients");
		System.out.println("4. Quitter");

		int i=0;
		try {
			i = Tools.getIntegerInput();
		} catch (IncorrectParam e) {
			System.out.println(e.getMessage());
			readCommand();
		}
		switch(i) {
		case 1:
			ServeurDashBoard.showTables();
			break;
		case 2:
			ServeurDashBoard.showTableStates();
			break;
		case 3:
			ServeurDashBoard.showCurrentReservations();
			break;
		case 4:
			return;
		}
		readCommand();
	}

	private static void showCurrentReservations() {
		Serveur serveur = (Serveur) Utilisateur.connectedUser;
		List<Reservation> list = ReservationCollection.getCurrentReservations(serveur);

		int i = 1;
		for(Reservation reservation : list) {
			System.out.println(i+". Table : "+reservation.table.num+" état : "+reservation.etatReservation);
			i++;
		}

		try {
			i = Tools.getIntegerInput();
			Reservation reservation = list.get(i-1);
			ServeurDashBoard.modifyReservation(reservation);

		} catch (IncorrectParam | ArrayIndexOutOfBoundsException  e) {
			e.printStackTrace();
		}
	}

	private static void modifyReservation(Reservation reservation) {
		System.out.println("----------------");
		System.out.println("1. Prendre une commande");
		System.out.println("2. Retour");

		int i=0;
		try {
			i = Tools.getIntegerInput();
		} catch (IncorrectParam e) {
			System.out.println(e.getMessage());
			readCommand();
		}
		switch(i) {
		case 1:
			ServeurDashBoard.createOrder(reservation);
			break;
		case 2:
			return;
		}
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


	public static void createOrder(Reservation reservation) {
		Commande commande = new Commande(Utilisateur.connectedUser.getId(), 
				reservation.numReservation);

		Plat plat ;

		HashMap<MatierePremiere, Integer> toUse = new HashMap<MatierePremiere, Integer>();

		while((plat = ServeurDashBoard.readPlat()) != null) {
			System.out.println(plat.nom);
			if(plat.testStock(toUse)) {
				commande.ajouterPlat(plat);
				ServeurDashBoard.putMp(plat, toUse);
			}
			else
				System.out.println("Ce plat n'est plus disponible!");
		}

		reservation.ajouterCommande(commande);
		commande.save();
	}
	private static void putMp(Plat plat, HashMap<MatierePremiere, Integer> toUse) {
		for(MatierePremiere mp : plat.matierePremieres) {
			if(toUse.containsKey(mp)) {
				toUse.put(mp, 1+toUse.get(mp));
			}else {
				toUse.put(mp, 1);
			}
		}
	}

	public static void showTables() {

	}

}
