package fr.ul.miage.dsw.projetgl;

import fr.ul.miage.dsw.projetgl.database.UserCollection;

public class Connexion {


	public boolean connexion(String identifiant) {
		if("".equalsIgnoreCase(identifiant))
			return false;
		
		Utilisateur.connectedUser  = UserCollection.getUser(identifiant);
		return Utilisateur.connectedUser != null;
	}


}
