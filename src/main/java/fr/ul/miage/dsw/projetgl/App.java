package fr.ul.miage.dsw.projetgl;

import fr.ul.miage.dsw.projetgl.dashboard.AssistantServiceDashBoard;
import fr.ul.miage.dsw.projetgl.dashboard.CuisinierDashBoard;
import fr.ul.miage.dsw.projetgl.dashboard.DirecteurDashBoard;
import fr.ul.miage.dsw.projetgl.dashboard.MaitreHotelDashBoard;
import fr.ul.miage.dsw.projetgl.dashboard.ServeurDashBoard;
import fr.ul.miage.dsw.projetgl.database.DataBase;

public class App {

	public static void main(String[] args) {
		DataBase.connect();
	}

	private static void connect() throws IncorrectParam {
		System.out.print( "Bonjour ! Connectez vous avec votre identifiant pour commencer \n" );

		String  id = Tools.getStringInput();
		Connexion co = new Connexion();
		if(!co.checkParamConnexion(id) || co.connexion(id)) {
			Tools.error("Erreur lors de la connexion, veuillez vérifier votre identifiant");
			App.connect();
			return;
		}

		Utilisateur user = Utilisateur.connectedUser;
		App.launchDashBoard(user);


	}

	private static void launchDashBoard(Utilisateur user) {
		switch(user.typeUser) {

		case maitre_hotel :
			MaitreHotelDashBoard.readCommand(); 
			break;
		case directeur :
			DirecteurDashBoard.readCommand(); 
			break;
		case serveur :
			ServeurDashBoard.readCommand(); 
			break;
		case cuisinier :
			CuisinierDashBoard.readCommand(); 
			break;
		case assistant_Service :
			AssistantServiceDashBoard.readCommand(); 
			break;
		}
	}





}
