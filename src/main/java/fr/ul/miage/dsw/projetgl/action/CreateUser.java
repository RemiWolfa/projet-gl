package fr.ul.miage.dsw.projetgl.action;

import fr.ul.miage.dsw.projetgl.Text;
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
			Tools.error(Text.USER_EXIST);
			return false;
		}
		System.out.println("Nom:");
		String nom = Tools.getStringInput();

		TypeUtilisateur type = ActionHelper.getSelectUserType();
		Utilisateur utilisateur = new Utilisateur(identifiant, nom, type);
		if(utilisateur.save()) {
			System.out.println(Text.USER_SAVED);
			return true;
		}else {
			Tools.error(Text.ERROR_SAVE);
		}
		return false;
	}


}
