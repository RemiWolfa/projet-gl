package fr.ul.miage.dsw.projetgl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.ul.miage.dsw.projetgl.database.ReservationCollection;
import fr.ul.miage.dsw.projetgl.enumeration.EtatCommande;


public class CommandeTest {
	
	@BeforeAll
	static void beforeAllTests() {
		InitTestBase.init();//charge les collections de test dans la bd de test
	}
	
	@Test
	@DisplayName("Test: vérifier si plat est ajouté dans commande, cas 1: ajouté")
	public void assertTrueCommande() {
		
		Plat plat1 = new Plat("pizza",5.0);
		plat1.save();
		Commande cmd = new Commande("001",7);
		cmd.save();
		assertTrue(cmd.ajouterPlat(plat1));

	}
	
	
	
	@Test
	@DisplayName("Test: vérifier si plat est ajouté dans commande, cas 1: ajouté")
	public void assertFalseCommandePrete() {
		Commande cmd = new Commande("001",7);
		cmd.etatCommande = EtatCommande.prete;
		assertFalse(cmd.setToReady());
	}
	
	@Test
	public void assertTrueCommandePrete() {
		Reservation reservation = new Reservation(new Date());
		reservation.save();
		Commande cmd = new Commande("001",reservation.numReservation);
		cmd.etatCommande = EtatCommande.passee;
		cmd.save();
		assertTrue(cmd.setToReady());
	}

	
	
	
	

	
	
	
}