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
		App.connect();
	}

	//connexion jusqu'à ce que l'identifiant est bon
	private static void connect(){
		System.out.println( Text.WELCOME );

		String  id = Tools.getStringInput();
		Connexion co = new Connexion();
		if(!co.connexion(id)) {
			Tools.error(Text.LOGIN_ERROR);
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
