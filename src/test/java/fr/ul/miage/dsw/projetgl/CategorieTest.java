package fr.ul.miage.dsw.projetgl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.ul.miage.dsw.projetgl.database.CategorieCollection;

public class CategorieTest {
	
	@BeforeAll
	static void beforeAllTests() {
		InitTestBase.init();//charge les collections de test dans la bd de test
	}
	
	
	@Test
	@DisplayName("Test: cas 1: Categorie existe")
	public void assertTrueCategorie() {
		Categorie categorie = new Categorie("poulet");
		categorie.save();
		assertTrue(CategorieCollection.exist(categorie));
		
	}
	
	@Test
	@DisplayName("Test: cas 2: Categorie n existe pas")
	public void assertFalseCategorie() {
		Categorie categorie = new Categorie("viande");
		assertFalse(CategorieCollection.exist(categorie));
		
	}

	
	
}
