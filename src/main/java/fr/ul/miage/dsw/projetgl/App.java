package fr.ul.miage.dsw.projetgl;

import java.util.Scanner;

import fr.ul.miage.dsw.projetgl.dashboard.AssistantServiceDashBoard;
import fr.ul.miage.dsw.projetgl.dashboard.CuisinierDashBoard;
import fr.ul.miage.dsw.projetgl.dashboard.DirecteurDashBoard;
import fr.ul.miage.dsw.projetgl.dashboard.MaitreHotelDashBoard;
import fr.ul.miage.dsw.projetgl.dashboard.ServeurDashBoard;

public class App {

	public static void main(String[] args) {

		Scanner scanner = new Scanner( System.in);
		System.out.print( "Bonjour ! Connectez vous avec votre identifiant pour commencer \n" );
	
		String  id = scanner.nextLine();
		Connexion co = new Connexion();
		
		Boolean verif = false;
		
		while(!verif || !co.connexion(id)) {
			try {
				verif=co.checkParamConnexion(id);
				
			}
			catch(IncorrectParam e) {
				System.out.println(e.getMessage());
			}
		}
		
		Utilisateur user = Utilisateur.connectedUser;

		
		switch(user.typeUser) {
		
		case maitre_hotel :
			
			MaitreHotelDashBoard maitre_hotel = new MaitreHotelDashBoard();
			
		case directeur :
			
			DirecteurDashBoard directeur = new DirecteurDashBoard();
			
		case serveur :
			ServeurDashBoard serveur = new ServeurDashBoard();
			
		case cuisinier :
			CuisinierDashBoard cuisinier = new CuisinierDashBoard();
			
		case assistant_Service :
			
			AssistantServiceDashBoard assistant = new AssistantServiceDashBoard();
			
		}
				
		

	}


}
