package fr.ul.miage.dsw.projetgl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class CommandeTest {
	
	@BeforeAll
	static void beforeAllTests() {
		InitTestBase.init();//charge les collections de test dans la bd de test
	}
	
	@Test
	public void assertTrueCommande() {
		
		Plat plat1 = new Plat("pizza");
		plat1.save();
		Commande cmd = new Commande("001",7);
		cmd.save();
		assertTrue(cmd.ajouterPlat(plat1));

	}
	
	
	
	

	
	
	
}