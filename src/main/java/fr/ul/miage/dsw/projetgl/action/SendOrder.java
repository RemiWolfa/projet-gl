package fr.ul.miage.dsw.projetgl.action;

import java.util.ArrayList;


import fr.ul.miage.dsw.projetgl.Commande;
import fr.ul.miage.dsw.projetgl.Plat;
import fr.ul.miage.dsw.projetgl.Text;
import fr.ul.miage.dsw.projetgl.Tools;
import fr.ul.miage.dsw.projetgl.dashboard.CuisinierDashBoard;
import fr.ul.miage.dsw.projetgl.database.ReservationCollection;

public class SendOrder implements UserAction{

	@Override
	public boolean execute() {
		ArrayList<Commande> commandes = ReservationCollection.getWaitingOrders();

		for(int i = 0; i < commandes.size(); i++) {
			Commande commande = commandes.get(i);
			System.out.println((i+1)+". "+Tools.format(commande.date) + " : " + commande.getPlats().size()+" plats");
		}
		System.out.println((commandes.size()+1)+". Retour");

		int input = Tools.getIntegerInput(1,commandes.size()+1,commandes.size()+1);

		if(input-1 < 0 || input > commandes.size())
			return false;

		modifyOrder(commandes.get(input-1));
		return true;
	}

	private void modifyOrder(Commande commande) {
		for(Plat plat : commande.getPlats()) {
			System.out.println(plat.nom);
		}

		System.out.println("----------------");
		System.out.println("1. Commande prête");
		System.out.println("2. Quitter");
		int i = Tools.getIntegerInput(1,2,2);
		switch(i) {
		case 1:
			if(commande.setToReady()) {
				System.out.println(Text.ORDER_WILL_SENDED);
			}else {
				Tools.error(Text.ERROR_SAVE);
				modifyOrder(commande);
			}
			break;
		case 2:
			return;
		}
	}

}
