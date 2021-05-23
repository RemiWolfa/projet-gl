package fr.ul.miage.dsw.projetgl;

import java.util.ArrayList;
import java.util.List;

import fr.ul.miage.dsw.projetgl.database.EtageCollection;

public class Etage {

	public int numEtage;
	public List<Table> tables;
	
	public Etage() {
		this.tables = new ArrayList<Table>();
	}
	
	public Etage(int num) {
		this();
		this.numEtage = num;
	}


	public void addTable(Table table) {
		this.tables.add(table);
	}
	
	public boolean save() {
		return EtageCollection.save(this);
	}

	
}
