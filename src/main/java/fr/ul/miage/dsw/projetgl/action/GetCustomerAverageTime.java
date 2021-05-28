package fr.ul.miage.dsw.projetgl.action;

import fr.ul.miage.dsw.projetgl.database.ReservationCollection;

public class GetCustomerAverageTime implements UserAction{
	

	@Override
	public boolean execute() {
		double time = ReservationCollection.averageReservationTime();
		double minutes = time % 60;
        int hours = (int) ((time-minutes)/60);
		System.out.println("Un client reste en moyenne "+hours+" heures et "+minutes+" minutes");
		return true;
	}


}
