package fr.ul.miage.dsw.projetgl.dashboard;

import fr.ul.miage.dsw.projetgl.Tools;
import fr.ul.miage.dsw.projetgl.action.ShowTableStates;

public class AssistantServiceDashBoard {

	public static void readCommand() {
		System.out.println("----------------");
		System.out.println("1. Afficher les états des tables");
		System.out.println("2. Quitter");

		int i = Tools.getIntegerInput(1,2,2);

		switch(i) {
		case 1:
			new ShowTableStates().execute();
			break;
		case 2:
			return;
		}
		readCommand();
	}

}
