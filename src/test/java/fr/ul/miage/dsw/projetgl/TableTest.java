package fr.ul.miage.dsw.projetgl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.ul.miage.dsw.projetgl.database.TableCollection;

public class TableTest {
	
	@BeforeAll
	static void beforeAllTests() {
		InitTestBase.init();//charge les collections de test dans la bd de test
	}
	
	@Test
	@DisplayName("Test: vérifier si table existe, cas 1: elle n existe pas")
	public void assertFalseTable() {
		
		
		Table table = new Table(-1);
	
		assertFalse(TableCollection.exist(table));
		
	}
	
	@Test
	@DisplayName("Test: vérifier si table existe, cas 2: elle existe")
	public void assertTrueTable() {
		
		Table table = new Table(1);
		table.save();
		assertTrue(TableCollection.exist(table));	
		
	}
	

	
	@Test
	@DisplayName("Test: vérifier si table existe, cas 2: elle existe")
	public void assertTrueAffectTable() {
		
		Table table = new Table(1);
		table.save();
		assertTrue(TableCollection.exist(table));	
		
	}
	
	
	@Test
	@DisplayName("Test: retourner numéro des tables")
    public void assertGetTableNumber() {
        Table table = new Table(1);
        Table table2 = new Table(2);
        Table table3 = new Table(3);

        ArrayList<Integer> num = new ArrayList<Integer>();
        num.add(3);
        num.add(2);
        num.add(1);

        ArrayList<Table> listTable = new ArrayList<Table>();
        listTable.add(table3);
        listTable.add(table2);
        listTable.add(table);
        assertEquals(num, TableCollection.getTableNumbers(listTable));
    }
	
	@Test
    public void assertGetFromTableNumber() {
        Table table = new Table(1);
        Table table2 = new Table(2);
        Table table3 = new Table(3);


        ArrayList<Integer> num = new ArrayList<Integer>();
        num.add(1);
        num.add(2);
        num.add(3);

        ArrayList<Table> listTable = new ArrayList<Table>();
        listTable.add(table);
        listTable.add(table2);
        listTable.add(table3);
        assertEquals(listTable,TableCollection.getTablesFromNumbers(num));


    }

}