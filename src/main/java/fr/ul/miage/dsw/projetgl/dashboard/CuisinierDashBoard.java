package fr.ul.miage.dsw.projetgl.dashboard;

import fr.ul.miage.dsw.projetgl.IncorrectParam;
import fr.ul.miage.dsw.projetgl.MatierePremiere;
import fr.ul.miage.dsw.projetgl.Tools;
import java.util.ArrayList;

import javax.tools.Tool;

import fr.ul.miage.dsw.projetgl.Commande;
import fr.ul.miage.dsw.projetgl.Plat;
import fr.ul.miage.dsw.projetgl.database.MatierePremiereCollection;
import fr.ul.miage.dsw.projetgl.database.PlatCollection;
import fr.ul.miage.dsw.projetgl.database.ReservationCollection;


public class CuisinierDashBoard {

	

	public static void showOrders() {
		ArrayList<Commande> commandes = ReservationCollection.getWaitingOrders();

		for(int i = 0; i < commandes.size(); i++) {
			Commande commande = commandes.get(i);
			System.out.println((i+1)+". "+Tools.format(commande.date) + " : " + commande.getPlats().size()+" plats");
		}
		System.out.println((commandes.size()+1)+". Retour");

		int input=0;
		try {
			input = Tools.getIntegerInput();
		} catch (IncorrectParam e) {
			System.out.println(e.getMessage());
			showOrders();
		}
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
		int i=0;
		try {
			i = Tools.getIntegerInput();
		} catch (IncorrectParam e) {
			System.out.println(e.getMessage());
			modifyOrder(commande);
		}
		switch(i) {
		case 1:
			if(commande.setToReady()) {
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
		System.out.println("1. Visualiser / envoyer commande");
		System.out.println("2. Consulter les stocks");
		System.out.println("3. Créer un plat");
		System.out.println("4. Quitter");

		int i=0;
		try {
			i = Tools.getIntegerInput();
		} catch (IncorrectParam e) {
			System.out.println(e.getMessage());
			readCommand();
		}

		switch(i) {

		case 1:
			CuisinierDashBoard.showOrders();
			break;
		case 2:
			CuisinierDashBoard.checkMatieresPremieres();
			break;
		case 3:
			CuisinierDashBoard.createPlat();
			break;
		case 4:
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
	}/*Double hours = (average % 60);
	Double minutes = average-(hours*60);
	return "Les réservations durent en moyenne "+hours+" heures et "+minutes+" minutes";*/


	
	public static void checkMatieresPremieres() {
		ArrayList<MatierePremiere> mp =MatierePremiereCollection.getMatieresPremieres();
		for(MatierePremiere matiere : mp) {
			System.out.println("Nom : "+matiere.nom+" \n "
					+ "Stock : " + MatierePremiereCollection.getStock(matiere.nom));
		}
	}

	public static void createPlat() {
		System.out.println("Entrez un nom:");
		String name = Tools.getStringInput();
		if(PlatCollection.exist(name)) {
			System.out.println("Le plat existe déjà");
			return;
		}
		
		Plat plat = new Plat(name);
		
		String input; 
		System.out.println("Entrez un nom de matiere première (exit pour arrêter):");
		while(!(input = Tools.getStringInput()).equalsIgnoreCase("exit")) {
			if(!"".equalsIgnoreCase(input)) {
				
			MatierePremiere mp = new MatierePremiere(input);
			if(!MatierePremiereCollection.exist(mp)) {
				CuisinierDashBoard.createMatierePremiere(mp);
			}
			plat.ajouterMatierePremiere(mp);
			System.out.println("Entrez un nom de matiere première (exit pour arrêter):");
			}
		}
		
		plat.save();
		
	}

	public static void createMatierePremiere(MatierePremiere mp) {
		System.out.println("La matière première n'existe pas, elle va être créer");
		System.out.println("1. quantité en poid");
		System.out.println("2. quantité en unité");
		
		try {
			int i = Tools.getIntegerInput();
			if(i == 1)
				mp.enPoids = true;
			
			mp.save();
		} catch (IncorrectParam e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
