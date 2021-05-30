package fr.ul.miage.dsw.projetgl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.ul.miage.dsw.projetgl.database.PlatCollection;

public class PlatTest {
	
	@BeforeAll
	static void beforeAllTests() {
		InitTestBase.init();//charge les collections de test dans la bd de test
	}
	@Test
	@DisplayName("Vérifier Matiere première dans la base")
	public void assertTrueMatierePremiere() {
		Plat plat1 = new Plat("soupe",2.0);
		plat1.save();
		MatierePremiere matierePremiere = new MatierePremiere("poisson");
		matierePremiere.save();
		 assertTrue(plat1.ajouterMatierePremiere(matierePremiere,10));

	}
	
	@Test
	@DisplayName("Vérifier plat dans la base")
	public void assertTruePlat() {
		
		Plat plat1 = new Plat("pizza",3.0);
		plat1.save();
		 assertTrue(PlatCollection.exist(plat1));

	}
	
	@Test
	public void assertFalsePlat() {
		
		Plat plat2 = new Plat("Hamburger",10.0);
		
		assertFalse(PlatCollection.exist(plat2));

	}
	
	@Test
	public void assertEqualsPlat() {
		
		Plat plat = new Plat("pizza",3.0);
		plat.save();
		assertEquals(plat,Plat.trouverPlat("pizza"));

	}
	
	
	
	 
	

}