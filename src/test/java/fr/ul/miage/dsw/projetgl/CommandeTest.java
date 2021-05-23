package fr.ul.miage.dsw.projetgl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CommandeTest {
	
	
	@Test
	@DisplayName("Vérifier nom plat dans la base")
	
	public void assertEqualsNomPlat() {
		
		Plat plat1 = new Plat("Pizza");
		Plat plat2 = new Plat("Hamburger");
		Plat [] plats = new Plat[2];
		plats[0] = plat1;
		plats[1] = plat2;
		Table t = new Table(5);
		
		Commande cmd = new Commande();
		cmd = cmd.saisirCommande(plats,t);	

		assertAll(() -> assertEquals(plat1.getNom(), Plat.getNom(), () ->  plat1.getNom()),
				() -> assertEquals(plat2.getNom(), Plat.getNom(), () ->  plat2.getNom()));
		
//		assertEquals(t.getNum(),Table.num);
//		assertEquals(plat1.getNom(), Plat.getNom());
//		assertEquals(plat2.getNom(), Plat.getNom());
	}
	
	@Test
	public void assertEqualsNumTable() {
		Plat plat1 = new Plat("Pizza");
		Plat plat2 = new Plat("Hamburger");
		Plat [] plats = new Plat[2];
		plats[0] = plat1;
		plats[1] = plat2;
		Table t = new Table(5);
		
		Commande cmd = new Commande();
		cmd = cmd.saisirCommande(plats,t);	
		assertEquals(t.getNum(),Table.num);
	}
	
	
	
}
