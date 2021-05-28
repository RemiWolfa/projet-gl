package fr.ul.miage.dsw.projetgl.dashboard;

import java.util.ArrayList;
import java.util.HashMap;

import fr.ul.miage.dsw.projetgl.Carte;
import fr.ul.miage.dsw.projetgl.IncorrectParam;
import fr.ul.miage.dsw.projetgl.Plat;
import fr.ul.miage.dsw.projetgl.Tools;
import fr.ul.miage.dsw.projetgl.Utilisateur;
import fr.ul.miage.dsw.projetgl.action.CreateUser;
import fr.ul.miage.dsw.projetgl.action.GetBestMeats;
import fr.ul.miage.dsw.projetgl.action.GetCustomerAverageTime;
import fr.ul.miage.dsw.projetgl.action.ModifyMenu;
import fr.ul.miage.dsw.projetgl.action.ModifyUser;
import fr.ul.miage.dsw.projetgl.database.CarteCollection;
import fr.ul.miage.dsw.projetgl.database.PlatCollection;
import fr.ul.miage.dsw.projetgl.database.ReservationCollection;
import fr.ul.miage.dsw.projetgl.database.UserCollection;
import fr.ul.miage.dsw.projetgl.enumeration.TypeUtilisateur;

public class DirecteurDashBoard {

	public static void readCommand() {
		System.out.println("----------------");
		System.out.println("1. Créer un utilisateur");
		System.out.println("2. Modifier un utilisateur");
		System.out.println("3. Modifier la carte du jour");
		System.out.println("4. Connaitre le temps moyen que restent les clients");
		System.out.println("5. Avoir la liste des plats les plsu rentables");
		System.out.println("6. Quitter");

		int i=0;
		try {
			i = Tools.getIntegerInput();
		} catch (IncorrectParam e) {
			System.out.println(e.getMessage());
			readCommand();
		}
		switch(i) {
		case 1:
			new CreateUser().execute();
			break;
		case 2:
			new ModifyUser().execute();
			break;
		case 3:
			new ModifyMenu().execute();
			break;
		case 4:
			new GetCustomerAverageTime().execute();
			break;
		case 5:
			new GetBestMeats().execute();
			break;
		case 6:
			return;
		}
		readCommand();
	}
	
	
}
