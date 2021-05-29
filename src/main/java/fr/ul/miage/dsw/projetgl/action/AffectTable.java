package fr.ul.miage.dsw.projetgl.action;

import java.util.ArrayList;


import fr.ul.miage.dsw.projetgl.Serveur;
import fr.ul.miage.dsw.projetgl.Table;
import fr.ul.miage.dsw.projetgl.Text;
import fr.ul.miage.dsw.projetgl.Tools;
import fr.ul.miage.dsw.projetgl.Utilisateur;
import fr.ul.miage.dsw.projetgl.dashboard.MaitreHotelDashBoard;
import fr.ul.miage.dsw.projetgl.database.TableCollection;
import fr.ul.miage.dsw.projetgl.database.UserCollection;

public class AffectTable implements UserAction{

	@Override
	public boolean execute() {
		ArrayList<Serveur> serveurs = UserCollection.getAllServeurs();
		for(int i = 0; i < serveurs.size(); i++) {
			Utilisateur user = serveurs.get(i);
			System.out.println((i+1)+". "+user.nom+" - "+user.typeUser);
		}
		System.out.println((serveurs.size()+1)+". Retour");

		int input = Tools.getIntegerInput(1, serveurs.size()+1,serveurs.size()+1);

		if(input-1 < 0 && input > serveurs.size())
			return false;

		System.out.println("Ajouter une table à :"+serveurs.get(input-1).nom);
		return affectTable(serveurs.get(input-1));
	}

	private boolean affectTable(Serveur serveur) {
		System.out.println(Text.ENTER_TABLE_NUMBER);
		int num = Tools.getIntegerInput(1, Integer.MAX_VALUE,Integer.MAX_VALUE);


		if(!TableCollection.exist(new Table(num))) {
			System.out.println(Text.TABLE_NOT_EXIST);
			return false;
		}

		if(serveur.hasTable(num)) {
			System.out.println(Text.TABLE_ALREADY_AFFECT);
			return false;
		}

		serveur.affecterTable(new Table(num));
		if(serveur.updateTables()) {
			System.out.println("La table a été affectée à "+serveur.nom);
			return true;
		}
		return false;

	}

}
