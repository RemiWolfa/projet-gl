package fr.ul.miage.dsw.projetgl.action;

import java.util.HashMap;
import java.util.List;

import fr.ul.miage.dsw.projetgl.Commande;
import fr.ul.miage.dsw.projetgl.IncorrectParam;
import fr.ul.miage.dsw.projetgl.MatierePremiere;
import fr.ul.miage.dsw.projetgl.Plat;
import fr.ul.miage.dsw.projetgl.Reservation;
import fr.ul.miage.dsw.projetgl.Serveur;
import fr.ul.miage.dsw.projetgl.Text;
import fr.ul.miage.dsw.projetgl.Tools;
import fr.ul.miage.dsw.projetgl.Utilisateur;
import fr.ul.miage.dsw.projetgl.database.ReservationCollection;

public class ManageBookings implements UserAction{

	@Override
	public boolean execute() {
			Serveur serveur = (Serveur) Utilisateur.connectedUser;
			List<Reservation> list = ReservationCollection.getCurrentReservations(serveur);

			int i = 1;
			for(Reservation reservation : list) {
				System.out.println(i+". Table : "+reservation.table.num+" état : "+reservation.etatReservation);
				i++;
			}

			try {
				i = Tools.getIntegerInput();
				Reservation reservation = list.get(i-1);
				return modifyReservation(reservation);

			} catch (IncorrectParam | ArrayIndexOutOfBoundsException  e) {
				e.printStackTrace();
				return false;
			}
		}
	
	private boolean modifyReservation(Reservation reservation) {
		System.out.println("----------------");
		System.out.println("1. Prendre une commande");
		System.out.println("2. Retour");

		int i=0;
		try {
			i = Tools.getIntegerInput();
		} catch (IncorrectParam e) {
			System.out.println(e.getMessage());
			return false;
		}
		switch(i) {
		case 1:
			return createOrder(reservation);
		default:
			return false;
		}
	}
	
	private boolean createOrder(Reservation reservation) {
		Commande commande = new Commande(Utilisateur.connectedUser.getId(), 
				reservation.numReservation);

		Plat plat ;

		HashMap<MatierePremiere, Integer> toUse = new HashMap<MatierePremiere, Integer>();

		while((plat = ActionHelper.readPlatWithMenu()) != null) {
			System.out.println(plat.nom);
			if(plat.testStock(toUse)) {
				commande.ajouterPlat(plat);
				putMp(plat, toUse);
			}
			else
				System.out.println(Text.MEAT_OUTOF_STOCK);
		}

		reservation.ajouterCommande(commande);
		return commande.save();
	}
	private static void putMp(Plat plat, HashMap<MatierePremiere, Integer> toUse) {
		for(MatierePremiere mp : plat.matierePremieres) {
			if(toUse.containsKey(mp)) {
				toUse.put(mp, 1+toUse.get(mp));
			}else {
				toUse.put(mp, 1);
			}
		}
	}

}
