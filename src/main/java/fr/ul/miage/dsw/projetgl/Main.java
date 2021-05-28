package fr.ul.miage.dsw.projetgl;


import fr.ul.miage.dsw.projetgl.dashboard.DirecteurDashBoard;
import fr.ul.miage.dsw.projetgl.database.DataBase;
import fr.ul.miage.dsw.projetgl.database.ReservationCollection;



public class Main {

	public static void main(String[] args) {
		DataBase.connect();

		new Table(1).save();
		Serveur serveur = new Serveur("0001", "Robert");

		Connexion connexion = new Connexion();
		connexion.connexion("0001"); 
		
		ReservationCollection.bestProfitability();
		
		if(Utilisateur.connectedUser != null)
			System.out.println("Utilisateur de type:"+Utilisateur.connectedUser.typeUser);
		DirecteurDashBoard.readCommand();
		

	}

}
