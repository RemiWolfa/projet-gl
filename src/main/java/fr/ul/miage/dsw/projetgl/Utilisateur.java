package fr.ul.miage.dsw.projetgl;

import fr.ul.miage.dsw.projetgl.database.UserCollection;

public class Utilisateur {
	
	public static Utilisateur connectedUser;
	
	public Type typeUser;
	public String identifiant;
	
	public Utilisateur(String identifiant, Type typeUser) {
		this.typeUser = typeUser;
		this.identifiant = identifiant;
	}
	
	public Utilisateur(String identifiant, String typeString) {
		this.identifiant = identifiant;
		this.typeUser = Type.valueOf(typeString);
	}
	
	//la fonction de connexion
	public static boolean connexion(String identifiant) {
		Utilisateur.connectedUser  = UserCollection.getUser(identifiant);
		return Utilisateur.connectedUser != null;
			
		
	}
	
	public boolean save() {
		return UserCollection.save(this);
	}

}
