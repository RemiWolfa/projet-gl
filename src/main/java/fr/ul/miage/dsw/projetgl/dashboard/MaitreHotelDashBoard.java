package fr.ul.miage.dsw.projetgl.dashboard;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import fr.ul.miage.dsw.projetgl.IncorrectParam;
import fr.ul.miage.dsw.projetgl.MatierePremiere;
import fr.ul.miage.dsw.projetgl.Reservation;
import fr.ul.miage.dsw.projetgl.Serveur;
import fr.ul.miage.dsw.projetgl.Table;
import fr.ul.miage.dsw.projetgl.Tools;
import fr.ul.miage.dsw.projetgl.Utilisateur;
import fr.ul.miage.dsw.projetgl.database.MatierePremiereCollection;
import fr.ul.miage.dsw.projetgl.database.TableCollection;
import fr.ul.miage.dsw.projetgl.database.UserCollection;

public class MaitreHotelDashBoard {


	public static void readCommand() {
		System.out.println("----------------");
		System.out.println("1. Prendre une reservation");
		System.out.println("2. Affecter une table à un serveur");
		System.out.println("3. Mettre à jour les stocks");
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
			MaitreHotelDashBoard.createReservation();
			break;
		case 2:
			MaitreHotelDashBoard.affectTableToServeur();
			break;
		case 3 :
			MaitreHotelDashBoard.updateStock();
			break;
		case 4:
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

		int input =0 ;
		try {
			input = Tools.getIntegerInput();
		} catch (IncorrectParam e) {
			System.out.println(e.getMessage());
			affectTableToServeur();
		}
		if(input-1 < 0 && input > serveurs.size())
			return;

		System.out.println("Ajouter une table à :"+serveurs.get(input-1).nom);
		MaitreHotelDashBoard.affectTable(serveurs.get(input-1));
	}

	private static void affectTable(Serveur serveur) {
		System.out.println("Numéro de table:");
		int num=0;
		try {
			num = Tools.getIntegerInput();
		} catch (IncorrectParam e) {
			System.out.println(e.getMessage());
			affectTable(serveur);
		}
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
	
	public static void updateStock() {
		ArrayList<MatierePremiere> mp =MatierePremiereCollection.getMatieresPremieres();
		int i=1;
		System.out.println("De quelle matière souhaitez-vous modifier le stock ?\n");
		
		for(MatierePremiere matiere : mp) {
			System.out.println(i+") "+matiere.nom+" \n ");
			i++;
		}
		
		try {
			int num = Tools.getIntegerInput();
			System.out.println("Quelle valeur de stock souhaitez vous entrer ?");
			int stock = Tools.getIntegerInput();
			MatierePremiereCollection.setStock(mp.get(num-1).nom, stock);
		}
		catch(IncorrectParam e) {
			System.out.println(e.getMessage());
			updateStock();
		}
		
		
	}
	
	public static void createReservation() {
		System.out.println("Date:");
		Date date = Tools.getDateInput();
		System.out.println("Numéro de table:");
		int numTable=0;;
		try {
			numTable = Tools.getIntegerInput();
		} catch (IncorrectParam e) {
			System.out.println(e.getMessage());
			createReservation();
		}
		Reservation reservation = new Reservation(date);
		reservation.table = new Table(numTable);//pas propre
		if(reservation.save()) {
			System.out.println("La reservation a été faite");
		}else {
			Tools.error("Problème lors de l'enregistrement");
		}
	}

}
