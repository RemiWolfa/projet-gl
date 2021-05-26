package fr.ul.miage.dsw.projetgl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fr.ul.miage.dsw.projetgl.database.TableCollection;

public class TableTest {
	@Test
	public void assertFalseTable() {
		
		
		Table table = new Table(-1);
	
		assertFalse(TableCollection.exist(table));
		
	}
	
	@Test
	public void assertTrueTable() {
		
		Table table = new Table(1);
		
		assertTrue(TableCollection.exist(table));	
		
	}

}
