package fr.ul.miage.dsw.projetgl;

import java.util.ArrayList;
import java.util.List;

public class Serveur extends Utilisateur{
	
	public List<Table> tables;

	public Serveur(String identifiant) {
		super(identifiant, Type.serveur);
		// TODO Auto-generated constructor stub
		this.tables = new ArrayList<Table>();
	}

	public Serveur(List<Table> table, String identifiant) {
		super(identifiant, Type.serveur);
		this.tables = table;
	}

	
	
	

	public List<Table> getTable(int numEtage) {
		
		return tables;
	}
	
	public List<Table> getTables(EtatReservation etatReservation){
		
		return tables;
		
	}
	
	public Boolean affecterTable(Table table) {
		this.tables.add(table);
		return true;
	}
	
	public void afficherTables() {
	
		List<Table> tableList = new ArrayList<>();
		if(Utilisateur.connectedUser != null) {
			for(int i=0; i<= tables.size(); i++) {
				
				tableList.add(tables.get(i));
			}
		}
		
		tableList.toString();
	}

	@Override
	public String toString() {
		return "Serveur [tables=" + tables + "]";
	}
	
	
}
