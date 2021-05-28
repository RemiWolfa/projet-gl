package fr.ul.miage.dsw.projetgl;

import fr.ul.miage.dsw.projetgl.database.TableCollection;
import fr.ul.miage.dsw.projetgl.enumeration.EtatTable;

public class Table {
	
	public int num;
	public EtatTable etat;
	public int etage;
	
	public Table(int num) {
		this.num = num;
		this.etat = EtatTable.propre;//TOFIX
		this.etage = 0;//TOFIX
	}
	
	public boolean setEtat(EtatTable etat) {
		this.etat = etat;
		return TableCollection.updateState(this);
	}
	
	public void save() {
		TableCollection.save(this);
	}

}
