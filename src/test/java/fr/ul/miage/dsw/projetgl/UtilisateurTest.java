package fr.ul.miage.dsw.projetgl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UtilisateurTest {
	@BeforeAll
	static void beforeAll() {
		
		System.out.println("Appel de Before All tests");
	}
	
	@BeforeEach
	public void before() {
		
		System.out.println("Appel de Before each");
	}
	
	
	@Test
	public void test1() {
		System.out.println("Appel de test 1");
	}
	 
	
	@Test
	public void test2() {
		System.out.println("Appel de test 2");
	}
	
	
	@AfterEach
	public void after() {
		
		System.out.println("Appel de After each");
	}
	
	
	@AfterAll
	static void afterAll() {
		
		System.out.println("Appel de After All tests");
	}
	

}
