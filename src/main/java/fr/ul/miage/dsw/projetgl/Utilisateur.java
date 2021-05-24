package fr.ul.miage.dsw.projetgl;

import fr.ul.miage.dsw.projetgl.database.UserCollection;

public class Utilisateur {
	
	public static Utilisateur connectedUser;
	
	public Type typeUser;
	public String identifiant;
	public String nom;
	
	public Utilisateur(String identifiant, String nom, Type typeUser) {
		this.typeUser = typeUser;
		this.nom = nom;
		this.identifiant = identifiant;
	}
	
	public Utilisateur(String identifiant, String nom, String typeString) {
		this.identifiant = identifiant;
		this.nom = nom;
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
