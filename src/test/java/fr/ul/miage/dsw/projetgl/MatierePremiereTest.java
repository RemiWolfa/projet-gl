package fr.ul.miage.dsw.projetgl;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import fr.ul.miage.dsw.projetgl.database.MatierePremiereCollection;


public class MatierePremiereTest {
		

		@BeforeAll
		static void beforeAllTests() {
			InitTestBase.init();//charge les collections de test dans la bd de test
		}
		
		
		@Test
		public void assertTrueMatierePremiere() {
			
			MatierePremiere matierePremiere = new MatierePremiere("poisson");
			matierePremiere.save();
			assertTrue(MatierePremiereCollection.exist(matierePremiere));

		}
		
		@Test
		public void assertFalseMatierePremiere() {
			
			MatierePremiere matierePremiere = new MatierePremiere("passon");
			assertFalse(MatierePremiereCollection.exist(matierePremiere));
		}
		
		
		
		

	}

