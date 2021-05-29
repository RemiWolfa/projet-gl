package fr.ul.miage.dsw.projetgl.dashboard;

import fr.ul.miage.dsw.projetgl.IncorrectParam;
import fr.ul.miage.dsw.projetgl.MatierePremiere;
import fr.ul.miage.dsw.projetgl.Tools;
import fr.ul.miage.dsw.projetgl.action.CreateMeat;
import fr.ul.miage.dsw.projetgl.action.SendOrder;
import fr.ul.miage.dsw.projetgl.action.ShowStuff;

import java.util.ArrayList;

import javax.tools.Tool;

import fr.ul.miage.dsw.projetgl.Commande;
import fr.ul.miage.dsw.projetgl.Plat;
import fr.ul.miage.dsw.projetgl.database.MatierePremiereCollection;
import fr.ul.miage.dsw.projetgl.database.PlatCollection;
import fr.ul.miage.dsw.projetgl.database.ReservationCollection;


public class CuisinierDashBoard {


	public static void readCommand() {
		System.out.println("----------------");
		System.out.println("1. Visualiser / envoyer commande");
		System.out.println("2. Consulter les stocks");
		System.out.println("3. Créer un plat");
		System.out.println("4. Quitter");

		int i=0;
		try {
			i = Tools.getIntegerInput(1,4,4);
		} catch (IncorrectParam e) {
			System.out.println(e.getMessage());
			readCommand();
		}

		switch(i) {

		case 1:
			new SendOrder().execute();
			break;
		case 2:
			new ShowStuff().execute();
			break;
		case 3:
			new CreateMeat().execute();
			break;
		case 4:
			return;
		}
		readCommand();
	}

}
