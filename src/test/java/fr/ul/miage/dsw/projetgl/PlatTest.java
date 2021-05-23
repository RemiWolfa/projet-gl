package fr.ul.miage.dsw.projetgl;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlatTest {
	
	@Test
	@DisplayName("Vérification catégories")
	
	public void assertEqualsCategoriePlats() {
		Categorie cat1 = new Categorie("Poisson");
		Categorie cat2 = new Categorie("Viande");
		Plat p1 = new Plat("pizza fruit de mer");
		Plat p2 = new Plat("Hamburger");
		Plat p3 = new Plat("Lasagnes");

		p1.setCategorie(cat1);
		p2.setCategorie(cat2);
		p3.setCategorie(cat2);
		
		List<Plat> resultatAttendue = new ArrayList<>();
		resultatAttendue.add(p2);
		resultatAttendue.add(p3);

		
		List<Plat> resultatObtenue = new ArrayList<>();
		resultatObtenue = Plat.trouverPlat(cat2);
		
		assertEquals(resultatAttendue,resultatObtenue);

	}
	
	

}
