
package fr.ul.miage.dsw.projetgl;

import fr.ul.miage.dsw.projetgl.database.TableCollection;

public class Table {
	
	public int num;
	public EtatTable etat;	//TOFIX (Type EtatTable) 
	public int etage;
	
	public Table(int num) {
		this.num = num;
		this.etage = 0;//TOFIX
	}
	public Table(int num, int etage) {
		this.num = num;
		this.etage = etage;
	}
	public Table(int num, int etage, EtatTable etat) {
		this.num = num;
		this.etage = etage;
		this.etat = etat;
	}
	
	@Override
	public String toString() {
		return "Table [num=" + num + ", etat=" + etat + ", etage=" + etage + "]";
	}
	public Table() {
		// TODO Auto-generated constructor stub
	}

	public void save() {
		TableCollection.save(this);
	}

}
