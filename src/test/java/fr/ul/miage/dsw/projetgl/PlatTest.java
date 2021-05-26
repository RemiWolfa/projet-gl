package fr.ul.miage.dsw.projetgl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;



import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.ul.miage.dsw.projetgl.database.PlatCollection;

public class PlatTest {
	
	@Test
	@DisplayName("Vérifier plat dans la base")
	public void assertTruePlat() {
		
		Plat plat1 = new Plat("pizza");
		 assertTrue(PlatCollection.exist(plat1));

	}
	
	@Test
	public void assertFalsePlat() {
		
		Plat plat2 = new Plat("Hamburger");
		
		assertFalse(PlatCollection.exist(plat2));

	}
	
	

}
