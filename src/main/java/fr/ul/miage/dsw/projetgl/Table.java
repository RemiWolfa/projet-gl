package fr.ul.miage.dsw.projetgl;

import fr.ul.miage.dsw.projetgl.database.TableCollection;
import fr.ul.miage.dsw.projetgl.enumeration.EtatTable;

public class Table {
	
	public int num;
	public int nbCouverts;
	public EtatTable etat;
	public int etage;
	
	public Table(int num) {
		this.num = num;
		this.etat = EtatTable.propre;
		this.etage = 0;
		this.nbCouverts = 2;
	}
	
	public boolean setEtat(EtatTable etat) {
		this.etat = etat;
		return TableCollection.updateState(this);
	}
	
	public boolean save() {
		return TableCollection.save(this);
	}
	
	public boolean equals(Object o) {
		if(!(o instanceof Table))
			return false;
		Table table = (Table) o;
		return(table.num == this.num);
	}

}
