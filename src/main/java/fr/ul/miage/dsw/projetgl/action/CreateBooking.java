package fr.ul.miage.dsw.projetgl.action;

import java.util.Date;

import fr.ul.miage.dsw.projetgl.IncorrectParam;
import fr.ul.miage.dsw.projetgl.Reservation;
import fr.ul.miage.dsw.projetgl.Table;
import fr.ul.miage.dsw.projetgl.Tools;

public class CreateBooking implements UserAction{

	@Override
	public boolean execute() {
		System.out.println("Date:");
		Date date = Tools.getDateInput();
		System.out.println("Numéro de table:");
		int numTable=0;;
		try {
			numTable = Tools.getIntegerInput();
		} catch (IncorrectParam e) {
			System.out.println(e.getMessage());
			execute();
		}
		Reservation reservation = new Reservation(date);
		reservation.table = new Table(numTable);//pas propre
		if(reservation.save()) {
			System.out.println("La reservation a été faite");
			return true;
		}else {
			Tools.error("Problème lors de l'enregistrement");
		}
		return false;
	}

}
