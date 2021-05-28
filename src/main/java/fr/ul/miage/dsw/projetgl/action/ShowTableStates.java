package fr.ul.miage.dsw.projetgl.action;

import java.util.List;

import fr.ul.miage.dsw.projetgl.Serveur;
import fr.ul.miage.dsw.projetgl.Table;
import fr.ul.miage.dsw.projetgl.Utilisateur;

public class ShowTableStates implements UserAction{

	@Override
	public boolean execute() {
		if(!(Utilisateur.connectedUser instanceof Serveur))
			return false;
		
		Serveur serveur = (Serveur) Utilisateur.connectedUser;
		List<Table> tables = serveur.getTablesFromDB();

		for(Table table : tables) {
			System.out.println("Table numéro:"+table.num+" : "+table.etat);
		}
		return true;
	}

}
