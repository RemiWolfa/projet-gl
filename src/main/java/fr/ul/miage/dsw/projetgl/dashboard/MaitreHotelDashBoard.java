package fr.ul.miage.dsw.projetgl.dashboard;

import java.util.Date;

import fr.ul.miage.dsw.projetgl.Reservation;
import fr.ul.miage.dsw.projetgl.Table;
import fr.ul.miage.dsw.projetgl.Tools;

public class MaitreHotelDashBoard {
	
	
	public static void readCommand() {
		System.out.println("----------------");
		System.out.println("1. Prendre une reservation");
		System.out.println("2. Quitter");

		int i = Tools.getIntegerInput();
		switch(i) {
		case 1:
			MaitreHotelDashBoard.createReservation();
			break;
		case 2:
			break;
		case 3:
			return;
		}
		readCommand();
	}
	
	public static void createReservation() {
		System.out.println("Date:");
		Date date = Tools.getDateInput();
		System.out.println("Numéro de table:");
		int numTable = Tools.getIntegerInput();
		Reservation reservation = new Reservation(date);
		reservation.table = new Table(numTable);//pas propre
		if(reservation.save()) {
			System.out.println("La reservation a été faite");
		}else {
			Tools.error("Problème lors de l'enregistrement");
		}
	}

}
