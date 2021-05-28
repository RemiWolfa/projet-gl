package fr.ul.miage.dsw.projetgl;

import fr.ul.miage.dsw.projetgl.database.UserCollection;
import fr.ul.miage.dsw.projetgl.enumeration.TypeUtilisateur;

public class Utilisateur {
	
	public static Utilisateur connectedUser;
	
	public TypeUtilisateur typeUser;
	private String identifiant;
	public String nom;
	
	public Utilisateur(String identifiant, String nom, TypeUtilisateur typeUser) {
		this.typeUser = typeUser;
		this.nom = nom;
		this.identifiant = identifiant;
	}
	
	public Utilisateur(String identifiant, String nom, String typeString) {
		this.identifiant = identifiant;
		this.nom = nom;
		this.typeUser = TypeUtilisateur.valueOf(typeString);
	}
	

	public String getId() {
		return this.identifiant;
	}
	
	public boolean save() {
		return UserCollection.save(this);
	}


}
