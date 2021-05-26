package fr.ul.miage.dsw.projetgl.dashboard;

import java.util.ArrayList;


import fr.ul.miage.dsw.projetgl.Tools;
import fr.ul.miage.dsw.projetgl.Utilisateur;
import fr.ul.miage.dsw.projetgl.database.UserCollection;
import fr.ul.miage.dsw.projetgl.enumeration.TypeUtilisateur;

public class DirecteurDashBoard {

	public static void readCommand() {
		System.out.println("----------------");
		System.out.println("1. Créer un utilisateur");
		System.out.println("2. Modifier un utilisateur");
		System.out.println("3. Quitter");

		int i = Tools.getIntegerInput();
		switch(i) {
		case 1:
			DirecteurDashBoard.createUser();
			break;
		case 2:
			DirecteurDashBoard.modifyUser();
			break;
		case 3:
			return;
		}
		readCommand();
	}

	public static void modifyUser() {
		ArrayList<Utilisateur> users = UserCollection.getAllUsers();
		for(int i = 0; i < users.size(); i++) {
			Utilisateur user = users.get(i);
			System.out.println((i+1)+". "+user.nom+" - "+user.typeUser);
		}
		System.out.println((users.size()+1)+". Retour");
		
		int input = Tools.getIntegerInput();
		if(input-1 < 0 && input > users.size())
			return;
		
		System.out.println("modifier :"+users.get(input-1).nom);
	}
	
	public static void createUser() {
		System.out.println("Identifiant:");
		String identifiant = Tools.getStringInput();
		if(UserCollection.exist(identifiant)) {
			Tools.error("L'identifiant existe déjà");
			return;
		}
		System.out.println("Nom:");
		String nom = Tools.getStringInput();

		TypeUtilisateur type = DirecteurDashBoard.getSelectUserType();
		Utilisateur utilisateur = new Utilisateur(identifiant, nom, type);
		if(utilisateur.save()) {
			System.out.println("Utilisateur bien enregistré!");
		}else {
			Tools.error("Problème lors de l'enregistrement");
		}
	}

	public static TypeUtilisateur getSelectUserType() {
		System.out.println("Sélectionnez le type d'utilisateur:");
		System.out.println("1. Directeur");
		System.out.println("2. Maître d'hôtel");
		System.out.println("3. Assistant de service");
		System.out.println("4. Cuisinier");
		System.out.println("5. Serveur");
		int i = Tools.getIntegerInput();

		switch(i) {
		case 1:
			return TypeUtilisateur.directeur;
		case 2:
			return TypeUtilisateur.maitre_hotel;
		case 3:
			return TypeUtilisateur.assistant_Service;
		case 4:
			return TypeUtilisateur.cuisinier;
		case 5:
			return TypeUtilisateur.serveur;
		default:
			System.out.println("invalide");
			return DirecteurDashBoard.getSelectUserType();
		}
	}

}
