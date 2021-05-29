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
import fr.ul.miage.dsw.projetgl.action.ManageBookings;
import fr.ul.miage.dsw.projetgl.action.ShowTableStates;
import fr.ul.miage.dsw.projetgl.database.CarteCollection;
import fr.ul.miage.dsw.projetgl.database.PlatCollection;
import fr.ul.miage.dsw.projetgl.database.ReservationCollection;

public class ServeurDashBoard {

	public static void readCommand() {
		System.out.println("----------------");
		System.out.println("1. Afficher l'état des tables");
		System.out.println("2. Afficher les clients");
		System.out.println("3. Quitter");

		int i=0;
		try {
			i = Tools.getIntegerInput(1,3,3);
		} catch (IncorrectParam e) {
			System.out.println(e.getMessage());
			readCommand();
		}
		switch(i) {
		case 1:
			new ShowTableStates().execute();
			break;
		case 2:
			new ManageBookings().execute();
			break;
		case 3:
			return;
		}
		readCommand();
	}


}
