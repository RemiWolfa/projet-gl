package fr.ul.miage.dsw.projetgl;

import fr.ul.miage.dsw.projetgl.database.UserCollection;

public class Utilisateur {
	
	public Type typeUser;
	public String identifiant;
	
	public Utilisateur(String identifiant, Type typeUser) {
		this.typeUser = typeUser;
		this.identifiant = identifiant;
	}
	
	public Type getTypeUser() {
		return typeUser;
	}
	public void setTypeUser(Type typeUser) {
		this.typeUser = typeUser;
	}
	
	//la fonction de connexion
	public Utilisateur connexion(int identifiant) {
		return null;
		
	}
	
	public boolean save() {
		return UserCollection.save(this);
	}

}
