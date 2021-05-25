package fr.ul.miage.dsw.projetgl.dashboard;

import java.util.ArrayList;
import java.util.Date;

import fr.ul.miage.dsw.projetgl.Reservation;
import fr.ul.miage.dsw.projetgl.Serveur;
import fr.ul.miage.dsw.projetgl.Table;
import fr.ul.miage.dsw.projetgl.Tools;
import fr.ul.miage.dsw.projetgl.Utilisateur;
import fr.ul.miage.dsw.projetgl.database.TableCollection;
import fr.ul.miage.dsw.projetgl.database.UserCollection;

public class MaitreHotelDashBoard {


	public static void readCommand() {
		System.out.println("----------------");
		System.out.println("1. Prendre une reservation");
		System.out.println("2. Affecter une table à un serveur");
		System.out.println("3. Quitter");

		int i = Tools.getIntegerInput();
		switch(i) {
		case 1:
			MaitreHotelDashBoard.createReservation();
			break;
		case 2:
			MaitreHotelDashBoard.affectTableToServeur();
			break;
		case 3:
			return;
		}
		readCommand();
	}

	public static void affectTableToServeur() {
		ArrayList<Serveur> serveurs = UserCollection.getAllServeurs();
		for(int i = 0; i < serveurs.size(); i++) {
			Utilisateur user = serveurs.get(i);
			System.out.println((i+1)+". "+user.nom+" - "+user.typeUser);
		}
		System.out.println((serveurs.size()+1)+". Retour");

		int input = Tools.getIntegerInput();
		if(input-1 < 0 && input > serveurs.size())
			return;

		System.out.println("Ajouter une table à :"+serveurs.get(input-1).nom);
		MaitreHotelDashBoard.affectTable(serveurs.get(input-1));
	}

	private static void affectTable(Serveur serveur) {
		System.out.println("Numéro de table:");
		int num = Tools.getIntegerInput();
		if(serveur.hasTable(num)) {
			System.out.println("La table est déjà affectée au serveur!");
			//on peut reboucler ici
			return;
		}

		if(!TableCollection.exist(new Table(num))) {
			System.out.println("La table n'existe pas!");
			//on peut reboucler ici
			return;
		}

		serveur.affecterTable(new Table(num));
		if(serveur.updateTables()) {
			System.out.println("La table a été affectée à "+serveur.nom);
		}
			

	}

	public static void createReservation() {
		System.out.println("Date:");
		Date date = Tools.getDateInput();
		System.out.println("Numéro de table:");
		int numTable = Tools.getIntegerInput();
		Reservation reservation = new Reservation(date);
		reservation.table = new Table(numTable);//pas propre
		if(reservation.save()) {
			System.out.println("La reservation a été faite");
		}else {
			Tools.error("Problème lors de l'enregistrement");
		}
	}

}
