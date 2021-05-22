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

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	public void save() {
		TableCollection.save(this);
	}

}
