package fr.ul.miage.dsw.projetgl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ServeurTest {
	
	@Test
	public void affecterTableTest() {
		Table t1 = new Table(10);
		Table t2 = new Table(300);
		Serveur serveur = new Serveur();
		
		assertTrue(serveur.affecterTable(t1));
		assertFalse(serveur.affecterTable(t2));
	}

}
