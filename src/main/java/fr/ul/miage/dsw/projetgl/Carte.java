package fr.ul.miage.dsw.projetgl;

import java.util.Date;
import java.util.List;

import fr.ul.miage.dsw.projetgl.database.CarteCollection;

public class Carte {

	private Date dateCarte;
	public List<Plat> plats;

	public Carte() {
		this(new Date());
	}

	public Carte(Date date) {
		this.dateCarte = Tools.skipTime(date);
	}
	
	public Date getDate() {
		return this.dateCarte;
	}

	@Override
	public String toString() {
		return "Carte [dateCarte=" + dateCarte + ", plats=" + plats + "]";
	}

	public boolean save() {
		return CarteCollection.save(this);
	}


}
