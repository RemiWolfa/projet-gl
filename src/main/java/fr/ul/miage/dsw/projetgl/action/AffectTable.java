package fr.ul.miage.dsw.projetgl.action;

import java.util.ArrayList;

import fr.ul.miage.dsw.projetgl.IncorrectParam;
import fr.ul.miage.dsw.projetgl.Serveur;
import fr.ul.miage.dsw.projetgl.Table;
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

		int input =0 ;
		try {
			input = Tools.getIntegerInput();
		} catch (IncorrectParam e) {
			System.out.println(e.getMessage());
			execute();
		}
		if(input-1 < 0 && input > serveurs.size())
			return false;

		System.out.println("Ajouter une table à :"+serveurs.get(input-1).nom);
		return affectTable(serveurs.get(input-1));
	}

	private boolean affectTable(Serveur serveur) {
		System.out.println("Numéro de table:");
		int num=0;
		try {
			num = Tools.getIntegerInput();
		} catch (IncorrectParam e) {
			System.out.println(e.getMessage());
			affectTable(serveur);
		}

		if(!TableCollection.exist(new Table(num))) {
			System.out.println("La table n'existe pas!");
			//on peut reboucler ici
			return false;
		}

		if(serveur.hasTable(num)) {
			System.out.println("La table est déjà affectée au serveur!");
			//on peut reboucler ici
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
