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
import fr.ul.miage.dsw.projetgl.action.AffectTable;
import fr.ul.miage.dsw.projetgl.action.CreateBooking;
import fr.ul.miage.dsw.projetgl.action.UpdateStock;
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

		int i = Tools.getIntegerInput(1,4,4);

		switch(i) {
		case 1:
			new CreateBooking().execute();
			break;
		case 2:
			new AffectTable().execute();
			break;
		case 3 :
			new UpdateStock().execute();
			break;
		case 4:
			return;
		}
		readCommand();
	}

}
