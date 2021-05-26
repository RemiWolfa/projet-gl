package fr.ul.miage.dsw.projetgl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ServeurTest {
	
	@Test
	@DisplayName("Vérifier si la table se trouve dans la base")
	public void assertTableTrue() {
		Table t1 = new Table(1);
		Serveur srv = new Serveur("0001","Alain");
		assertTrue(srv.affecterTable(t1));
	}
	
	@Test
	public void assertTableFalse() {
		Table t2 = new Table(-1);	
		Serveur srv = new Serveur("0","Alain");
		assertFalse(srv.affecterTable(t2));
	}

}
