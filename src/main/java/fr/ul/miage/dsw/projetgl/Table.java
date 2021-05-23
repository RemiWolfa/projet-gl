package fr.ul.miage.dsw.projetgl;

import fr.ul.miage.dsw.projetgl.database.TableCollection;

public class Table {
	
	public int num;
	public String etat;
	public int etage;
	
	public Table(int num) {
		this.num = num;
		this.etat = "Tempo";//TOFIX
		this.etage = 0;//TOFIX
	}
	
	public Table() {
		// TODO Auto-generated constructor stub
	}

	public void save() {
		TableCollection.save(this);
	}

}
