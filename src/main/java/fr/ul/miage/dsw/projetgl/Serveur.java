package fr.ul.miage.dsw.projetgl;

import java.util.ArrayList;
import java.util.List;

import fr.ul.miage.dsw.projetgl.database.TableCollection;
import fr.ul.miage.dsw.projetgl.database.UserCollection;
import fr.ul.miage.dsw.projetgl.enumeration.EtatReservation;
import fr.ul.miage.dsw.projetgl.enumeration.TypeUtilisateur;

public class Serveur extends Utilisateur{
	
	public List<Table> tables;

	
	public Serveur(String identifiant, String nom) {
		super(identifiant, nom, TypeUtilisateur.serveur);
		// TODO Auto-generated constructor stub
		this.tables = new ArrayList<Table>();
	}

	public Serveur(List<Table> table, String nom, String identifiant) {
		super(identifiant, nom, TypeUtilisateur.serveur);
		this.tables = table;
	}

	
	
	public List<Table> getTable(int numEtage) {
		
		return tables;
	}
	
	public List<Table> getTables(EtatReservation etatReservation){
		
		return tables;
		
	}
	
	public List<Table> getTablesFromDB(){ //recupère les tables à jours
		this.tables = TableCollection.updateTables(this.tables);
		return this.tables;
	}
	
	public Boolean affecterTable(Table table) {
		this.tables.add(table);
		return true;
	}

	public boolean updateTables() {
		return UserCollection.updateTables(this);
	}

	public boolean hasTable(int num) {
		for(Table table : tables) {
			if(table.num == num)
				return true;
		}
		return false;
	}
	
}
