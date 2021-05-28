package fr.ul.miage.dsw.projetgl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class ReservationTest {
	
	@BeforeAll
	static void beforeAllTests() {
		InitTestBase.init();//charge les collections de test dans la bd de test
	}
	
	@Test
	public void assertEqualsReservation() {
		
		Reservation reservation = new Reservation(new Date());
		Commande cmd = new Commande("001",reservation.numReservation);
		cmd.save();
		reservation.ajouterCommande(cmd);
		
		assertEquals(cmd.reservationNum,reservation.numReservation);
		
 
	}
	
	@Test
	public void assertTrueReservation() {
	
		Reservation reservation = new Reservation(new Date());
		
		assertTrue(reservation.save());
		
 
	}


	
}
