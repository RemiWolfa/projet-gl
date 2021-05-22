package fr.ul.miage.dsw.projetgl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CommandeTest {
	
	
	@SuppressWarnings("null")
	@Test
	public void saisirCommandeTest() {
		Plat plat1 = new Plat("Pizza");
		Plat plat2 = new Plat("Hamburger");
		Plat [] plats = new Plat[2];
		plats[0] = plat1;
		plats[1] = plat2;
		Table t = new Table(5);
		Commande cmd = new Commande();
		
		cmd = cmd.saisirCommande(plats,t);	
		
		assertEquals(t.getNum(),Table.getNum());
		assertEquals(plat1.getNom(), Plat.getNom());
		assertEquals(plat2.getNom(), Plat.getNom());
	}
	
	
	
}
