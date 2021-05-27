package fr.ul.miage.dsw.projetgl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import fr.ul.miage.dsw.projetgl.database.TableCollection;

public class TableTest {
	
	@BeforeAll
	static void beforeAllTests() {
		InitTestBase.init();//charge les collections de test dans la bd de test
	}
	
	@Test
	public void assertFalseTable() {
		
		
		Table table = new Table(-1);
	
		assertFalse(TableCollection.exist(table));
		
	}
	
	@Test
	public void assertTrueTable() {
		
		Table table = new Table(1);
		table.save();
		assertTrue(TableCollection.exist(table));	
		
	}

}