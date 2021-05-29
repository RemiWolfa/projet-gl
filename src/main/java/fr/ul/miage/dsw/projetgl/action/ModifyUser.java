package fr.ul.miage.dsw.projetgl.action;

import java.util.ArrayList;


import fr.ul.miage.dsw.projetgl.Tools;
import fr.ul.miage.dsw.projetgl.Utilisateur;
import fr.ul.miage.dsw.projetgl.database.UserCollection;

public class ModifyUser implements UserAction{
	
	@Override
	public boolean execute() {
		ArrayList<Utilisateur> users = UserCollection.getAllUsers();
		for(int i = 0; i < users.size(); i++) {
			Utilisateur user = users.get(i);
			System.out.println((i+1)+". "+user.nom+" - "+user.typeUser);
		}
		System.out.println((users.size()+1)+". Retour");
		
		int input = Tools.getIntegerInput(1,users.size()+1,users.size()+1);

		if(input-1 < 0 && input > users.size())
			return false;
		
		System.out.println("modifier :"+users.get(input-1).nom);//TODO
		return true;
	}

}
