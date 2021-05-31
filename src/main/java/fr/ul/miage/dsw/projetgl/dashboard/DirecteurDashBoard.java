package fr.ul.miage.dsw.projetgl.dashboard;

import fr.ul.miage.dsw.projetgl.Tools;
import fr.ul.miage.dsw.projetgl.action.CreateUser;
import fr.ul.miage.dsw.projetgl.action.GetBestMeats;
import fr.ul.miage.dsw.projetgl.action.GetCustomerAverageTime;
import fr.ul.miage.dsw.projetgl.action.ModifyMenu;
import fr.ul.miage.dsw.projetgl.action.ModifyUser;

public class DirecteurDashBoard {

	public static void readCommand() {
		System.out.println("----------------");
		System.out.println("1. Créer un utilisateur");
		System.out.println("2. Modifier un utilisateur");
		System.out.println("3. Modifier la carte du jour");
		System.out.println("4. Connaitre le temps moyen que restent les clients");
		System.out.println("5. Avoir la liste des plats les plus rentables");
		System.out.println("6. Utiliser le tableau de bord assistant de service");
		System.out.println("7. Utiliser le tableau de bord cuisinier");
		System.out.println("8. Utiliser le tableau de bord serveur");
		System.out.println("9. Utiliser le tableau de bord maître d'hôtel");
		System.out.println("10. Quitter");

		int i = Tools.getIntegerInput(1,10,10);

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
			AssistantServiceDashBoard.readCommand();
			break;
		case 7:
			CuisinierDashBoard.readCommand();
			break;
		case 8:
			ServeurDashBoard.readCommand();
			break;
		case 9:
			MaitreHotelDashBoard.readCommand();
			break;
		default:
			return;
		}
		readCommand();
	}
	
	
}
