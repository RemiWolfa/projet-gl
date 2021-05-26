package fr.ul.miage.dsw.projetgl.dashboard;

import fr.ul.miage.dsw.projetgl.IncorrectParam;
import fr.ul.miage.dsw.projetgl.Tools;
import java.util.ArrayList;

import fr.ul.miage.dsw.projetgl.Commande;
import fr.ul.miage.dsw.projetgl.Plat;
import fr.ul.miage.dsw.projetgl.Tools;
import fr.ul.miage.dsw.projetgl.database.ReservationCollection;


public class CuisinierDashBoard {
	
	/* public static void readCommand() {
		System.out.println("----------------");
		System.out.println("1. Visualiser/envoyer commandes");
		System.out.println("2. Quitter");

		int i = Tools.getIntegerInput();
		switch(i) {
		case 1:
			CuisinierDashBoard.showOrders();
			break;
		case 2:
			return;
		}
		readCommand();
	} */

	public static void showOrders() {
		ArrayList<Commande> commandes = ReservationCollection.getWaitingOrders();
		
		for(int i = 0; i < commandes.size(); i++) {
			Commande commande = commandes.get(i);
			System.out.println((i+1)+". "+Tools.format(commande.date) + " : " + commande.getPlats().size()+" plats");
		}
		System.out.println((commandes.size()+1)+". Retour");
		
		int input = Tools.getIntegerInput();
		if(input-1 < 0 && input > commandes.size())
			return;
		
		CuisinierDashBoard.modifyOrder(commandes.get(input-1));
		
	}
	
	public static void modifyOrder(Commande commande) {
		for(Plat plat : commande.getPlats()) {
			System.out.println(plat.nom);
		}
		
		System.out.println("----------------");
		System.out.println("1. Commande prête");
		System.out.println("2. Quitter");
		int i = Tools.getIntegerInput();
		switch(i) {
		case 1:
			if(commande.ready()) {
				System.out.println("La commande va être servie!");
			}else {
				Tools.error("Erreur lors de la modification de l'état");
			}
			break;
		case 2:
			return;
		}
	}

	public static void readCommand() {
		System.out.println("----------------");
		System.out.println("1. Regarder les commandes en attente");
		System.out.println("2. Prévenir d'une commande");
		System.out.println("3. Consulter les stocks");

		System.out.println("4. Quitter");

		int i = Tools.getIntegerInput();
		Boolean verif = false;
		String numero = "";

		switch(i) {

		case 1:
			System.out.println("Numéro :");	

			while(!verif) {
				try {
					numero = Tools.getStringInput();
					verif=checkParamCommand(numero);
				}
				catch(IncorrectParam e) {
					System.out.println(e.getMessage());
				}

			}

			CuisinierDashBoard.showOrders();
			break;
		case 2:
			//CuisinierDashBoard.modifyOrder(commande);
			break;
		case 3:
			return;
		}
		readCommand();
	}

	public static Boolean checkParamCommand(String numero) throws IncorrectParam {
		try{
			int num =Integer.parseInt(numero);
		}
		catch(NumberFormatException e) {
			throw new IncorrectParam("Numéro de commande faux : " + numero );
		}

		return true;
	}

	public static void seeCommand(String numero) {

	}

	public static void checkStocks() {


	}

	public static void creatMeat() {


	}


	
	
}
