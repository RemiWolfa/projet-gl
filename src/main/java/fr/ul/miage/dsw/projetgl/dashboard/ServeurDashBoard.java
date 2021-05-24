package fr.ul.miage.dsw.projetgl.dashboard;

import fr.ul.miage.dsw.projetgl.Tools;

public class ServeurDashBoard {
	
	public static void readCommand() {
		System.out.println("----------------");
		System.out.println("1. Afficher les tables");
		System.out.println("2. Quitter");
		
		int i = Tools.getIntegerInput();
		switch(i) {
		case 1:
			ServeurDashBoard.showTables();
			break;
		case 2:
			return;
		}
	}
	
	public static void showTables() {
		
	}

}
