package fr.ul.miage.dsw.projetgl.dashboard;

import fr.ul.miage.dsw.projetgl.Tools;
import fr.ul.miage.dsw.projetgl.action.ManageBookings;
public class ServeurDashBoard {

	public static void readCommand() {
		System.out.println("----------------");
		System.out.println("1. Afficher les tables");
		System.out.println("2. Quitter");

		int i = Tools.getIntegerInput(1,2,2);

		switch(i) {
		case 1:
			new ManageBookings().execute();
			break;
		case 2:
			return;
		}
		readCommand();
	}


}
