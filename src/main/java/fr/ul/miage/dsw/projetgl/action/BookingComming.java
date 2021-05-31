package fr.ul.miage.dsw.projetgl.action;

import java.util.List;

import fr.ul.miage.dsw.projetgl.Reservation;
import fr.ul.miage.dsw.projetgl.Text;
import fr.ul.miage.dsw.projetgl.Tools;
import fr.ul.miage.dsw.projetgl.database.ReservationCollection;
import fr.ul.miage.dsw.projetgl.enumeration.EtatReservation;

public class BookingComming implements UserAction{

	@Override
	public boolean execute() {
		System.out.println(Text.SELECT_BOOKING);
		List<Reservation> reservations = ReservationCollection.getCommingBooking();
		int i = 1;
		for(Reservation reservation : reservations) {
			System.out.println(i+". "+reservation.dateArrivee+" "+reservation.table);
			i++;
		}
		
		System.out.println(i+". Annuler");
		
		int input = Tools.getIntegerInput(1, i, i);
		if(input == i)
			return false;
		
		modifyBooking(reservations.get(input-1));
		
		return true;
	}

	private void modifyBooking(Reservation reservation) {
		System.out.println("Modifier : réservation N°"+reservation.numReservation);
		System.out.println("1. Marquer comme arriver");
		System.out.println("2. Supprimer");
		System.out.println("3. Retour");
		int input = Tools.getIntegerInput(1, 3, 3);
		
		switch(input) {
		case 1:
			come(reservation);
			break;
		case 2:
			delete(reservation);
			break;
		case 3:
			return;
		}
		
		
	}
	
	private void come(Reservation reservation) {
		reservation.setEtat(EtatReservation.arrive);
		reservation.updateState();
		System.out.println("OK");
	}
	
	private void delete(Reservation reservation) {
		System.out.println("Voulez vous vraiment supprimer la réservation N°"+reservation.numReservation);
		System.out.println("1. Oui");
		System.out.println("2. Non");
		
		if(Tools.getIntegerInput(1, 2,2) == 2)
			return;
		
		reservation.delete();
		System.out.println("Supprimé");
	}

}
