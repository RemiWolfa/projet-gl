package fr.ul.miage.dsw.projetgl;

import fr.ul.miage.dsw.projetgl.database.UserCollection;

public class Connexion {


	public boolean connexion(String identifiant) {
		Utilisateur.connectedUser  = UserCollection.getUser(identifiant);
		return Utilisateur.connectedUser != null;
	}

	public Boolean checkParamConnexion(String id) {//TODO est-ce qu'on veut forcement un int?
		try{
			int num =Integer.parseInt(id);
		}
		catch(NumberFormatException e) {
			System.out.println("Identifiant erroné : " + id) ;
		}
		return true;
	}


}
