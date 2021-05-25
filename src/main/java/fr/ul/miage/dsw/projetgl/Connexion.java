package fr.ul.miage.dsw.projetgl;

import fr.ul.miage.dsw.projetgl.IncorrectParam;
import fr.ul.miage.dsw.projetgl.database.UserCollection;

public class Connexion {
	
	//la fonction de connexion
		public boolean connexion(String identifiant) {
			Utilisateur.connectedUser  = UserCollection.getUser(identifiant);
			return Utilisateur.connectedUser != null;
				
			
		}




	public Boolean checkParamConnexion(String id) throws IncorrectParam {
		try{
			int num =Integer.parseInt(id);
		}
		catch(NumberFormatException e) {
			throw new IncorrectParam("Identifiant erroné : " + id );
		}

		return true;
	}



}
