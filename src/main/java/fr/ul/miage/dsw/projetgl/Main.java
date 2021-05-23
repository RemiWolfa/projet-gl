package fr.ul.miage.dsw.projetgl;

import java.util.Scanner;

public class Main {

	 public static void main(String[] args) {
	      Scanner scanner = new Scanner( System.in);
	     
	      while(true){
	        System.out.print( "Bonjour ! Que souhaitez vous faire ? \n" );
	        String  line = scanner.nextLine();
	        Commandes cmd = new Commandes();
	        cmd.parser(line);
	      }   
	            
	     }
	  
	}
