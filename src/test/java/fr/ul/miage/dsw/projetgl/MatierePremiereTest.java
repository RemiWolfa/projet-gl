package fr.ul.miage.dsw.projetgl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import fr.ul.miage.dsw.projetgl.database.MatierePremiereCollection;


public class MatierePremiereTest {
		

		@BeforeAll
		static void beforeAllTests() {
			InitTestBase.init();//charge les collections de test dans la bd de test
		}
		
		
		@Test
		@DisplayName("Test: vérifier si matiere premiere dans stock, cas 1: elle existe")
		public void assertTrueMatierePremiere() {
			
			MatierePremiere matierePremiere = new MatierePremiere("poisson");
			matierePremiere.save();
			assertTrue(MatierePremiereCollection.exist(matierePremiere));

		}
		
		@Test
		@DisplayName("Test: vérifier si matiere premiere dans stock, cas 2: elle n existe pas")
		public void assertFalseMatierePremiere() {
			
			MatierePremiere matierePremiere = new MatierePremiere("passon");
			assertFalse(MatierePremiereCollection.exist(matierePremiere));
		}
		
		@Test
		@DisplayName("Test: vérifier ajout quantite de matiere premiere dans stock")
        public void assertEqualsStock() {
            MatierePremiereCollection.save(new MatierePremiere("stockTest2"));
            MatierePremiereCollection.setStock("stockTest2", 100);
            assertEquals(MatierePremiereCollection.getStock("stockTest2"), 100 );
        }
		
		@Test
        public void assertEqualsDecrementStock() {
            MatierePremiereCollection.save(new MatierePremiere("stockTest3"));
            MatierePremiereCollection.setStock("stockTest3", 100);
            MatierePremiereCollection.decrement("stockTest3", 10);
            assertEquals(MatierePremiereCollection.getStock("stockTest3"), 90 );
        }

	
		
		
		

	}

