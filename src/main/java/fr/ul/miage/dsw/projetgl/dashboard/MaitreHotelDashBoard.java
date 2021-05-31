package fr.ul.miage.dsw.projetgl.dashboard;

import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;

import fr.ul.miage.dsw.projetgl.MatierePremiere;
import fr.ul.miage.dsw.projetgl.Reservation;
import fr.ul.miage.dsw.projetgl.Serveur;
import fr.ul.miage.dsw.projetgl.Table;
import fr.ul.miage.dsw.projetgl.Tools;
import fr.ul.miage.dsw.projetgl.Utilisateur;
import fr.ul.miage.dsw.projetgl.action.AddTable;
import fr.ul.miage.dsw.projetgl.action.AffectTable;
import fr.ul.miage.dsw.projetgl.action.BookingComming;
import fr.ul.miage.dsw.projetgl.action.CreateBooking;
import fr.ul.miage.dsw.projetgl.action.UpdateStock;
import fr.ul.miage.dsw.projetgl.database.MatierePremiereCollection;
import fr.ul.miage.dsw.projetgl.database.TableCollection;
import fr.ul.miage.dsw.projetgl.database.UserCollection;

public class MaitreHotelDashBoard {


	public static void readCommand() {
		System.out.println("----------------");
		System.out.println("1. Prendre une reservation");
		System.out.println("2. Marquer l'arrivé du client");
		System.out.println("3. Affecter une table à un serveur");
		System.out.println("4. Mettre à jour les stocks");
		System.out.println("5. Ajouter une table");
		System.out.println("6. Quitter");

		int i = Tools.getIntegerInput(1,6,6);

		switch(i) {
		case 1:
			new CreateBooking().execute();
			break;
		case 2:
			new BookingComming().execute();
		case 3:
			new AffectTable().execute();
			break;
		case 4 :
			new UpdateStock().execute();
			break;
		case 5 :
			new AddTable().execute();
			break;
		default:
			return;
		}
		readCommand();
	}

}
