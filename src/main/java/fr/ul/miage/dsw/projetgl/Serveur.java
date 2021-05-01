package fr.ul.miage.dsw.projetgl;

import java.util.List;

public class Serveur extends Utilisateur{

	public Serveur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Serveur(List<Table> table) {
		super();
		this.table = table;
	}

	public List<Table> table;

	public List<Table> getTable() {
		return table;
	}

	public void setTable(List<Table> table) {
		this.table = table;
	}
	
	public List<Table> getTable(int numEtage) {
		
		return table;
	}
	
	public List<Table> getTables(EtatReservation etatReservation){
		
		return table;
		
	}
	
	public void affecterTable(Table table) {
		//
	}
	
}
