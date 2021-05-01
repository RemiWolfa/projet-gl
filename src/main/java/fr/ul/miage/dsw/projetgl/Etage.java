package fr.ul.miage.dsw.projetgl;

import java.util.Arrays;

public class Etage {

	public int numEtage;
	public Table[] tables;
	
	
	public Table[] listerTables(EtatTable etatTable) {
		
		return tables;
		
	}
	
	public Table[] listerTables(EtatReservation etatReservation) {
		
		return tables;
		
	}

	@Override
	public String toString() {
		return "Etage [numEtage=" + numEtage + ", tables=" + Arrays.toString(tables) + "]";
	}

	public int getNumEtage() {
		return numEtage;
	}

	public void setNumEtage(int numEtage) {
		this.numEtage = numEtage;
	}

	public Table[] getTables() {
		return tables;
	}

	public void setTables(Table[] tables) {
		this.tables = tables;
	}
	
}
