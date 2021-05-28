package fr.ul.miage.dsw.projetgl.action;

import fr.ul.miage.dsw.projetgl.Tools;
import fr.ul.miage.dsw.projetgl.Utilisateur;
import fr.ul.miage.dsw.projetgl.database.UserCollection;
import fr.ul.miage.dsw.projetgl.enumeration.TypeUtilisateur;

public class CreateUser implements UserAction{

	@Override
	public boolean execute() {
		System.out.println("Identifiant:");
		String identifiant = Tools.getStringInput();
		if(UserCollection.exist(identifiant)) {
			Tools.error("L'identifiant existe déjà");
			return false;
		}
		System.out.println("Nom:");
		String nom = Tools.getStringInput();

		TypeUtilisateur type = ActionHelper.getSelectUserType();
		Utilisateur utilisateur = new Utilisateur(identifiant, nom, type);
		if(utilisateur.save()) {
			System.out.println("Utilisateur bien enregistré!");
			return true;
		}else {
			Tools.error("Problème lors de l'enregistrement");
		}
		return false;
	}


}
