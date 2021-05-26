package fr.ul.miage.dsw.projetgl;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.Date;

import org.junit.jupiter.api.Test;


public class ReservationTest {
	
	@Test
	public void assertEqualsReservation() {
		
		Reservation reservation = new Reservation(new Date());
		Commande cmd = new Commande("001",reservation.numReservation);
		reservation.ajouterCommande(cmd);
		
		assertEquals(cmd.reservationNum,reservation.numReservation);
		

	}


	
}


