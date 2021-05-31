package fr.ul.miage.dsw.projetgl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CarteTest {
	

	@Test
	@DisplayName("Test: cas 1: carte du jour existe")
	
	public void assertTrueCarte() {
		Carte carte = Carte.getTodayCarte();
		Plat plat = new Plat("eau",50.0);
		plat.save();
		carte.addPlat(plat);
		carte.save();
		Carte carteSaved = Carte.getTodayCarte();

		assertTrue(carteSaved.hasPlat(plat));
	}

}
