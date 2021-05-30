package fr.ul.miage.dsw.projetgl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import fr.ul.miage.dsw.projetgl.database.UserCollection;

public class ServeurTest {
	
	@BeforeAll
	static void beforeAllTests() {
		InitTestBase.init();//charge les collections de test dans la bd de test
	}
	
	@Test
	@DisplayName("Vérifier si la table se trouve dans la base")
	public void assertTrueTable() {
		Table t1 = new Table(2);
		t1.save();
		Serveur srv = new Serveur("0001","Alain");
		srv.save();
		assertTrue(srv.affecterTable(t1));
	}
	
	@Test
	public void assertFalseTable() {
		Table t2 = new Table(-1);	
		Serveur srv = new Serveur("0","Alain");
		assertFalse(srv.affecterTable(t2));
	}
	
	@Test
	@DisplayName("Vérifier si la table se trouve dans la base")
	public void assertTrueHasTable() {
		Table t1 = new Table(2);
		t1.save();
		Serveur srv = new Serveur("0001","Alain");
		srv.save();
		srv.affecterTable(t1);
		
		assertTrue(srv.hasTable(t1.num));
	}
	
	@Test
	public void assertFalseHasTable() {
		Table t2 = new Table(-1);	
		Serveur srv = new Serveur("0","Alain");
		assertFalse(srv.hasTable(t2.num));

	}
	
	

	
	@Test
	public void assertTrueServeur() {
		
		Serveur serveur = new Serveur("001","Tom");
		serveur.save();		
		assertTrue(UserCollection.exist(serveur));

	}
	
	@Test
	public void assertFalseServeur() {
		
		Serveur serveur = new Serveur("8888","ALAINHGYFFY");
		assertFalse(UserCollection.exist(serveur));

	}

}