package fr.ul.miage.dsw.projetgl;

import java.util.ArrayList;
import java.util.List;

import fr.ul.miage.dsw.projetgl.database.CategorieCollection;

public class Categorie {

	public List<Plat> plats;
	public String nom;
	
	public Categorie(String nom) {
		this.nom = nom;
		this.plats = new ArrayList<Plat>();
	}
	
	public void setPlats(List<Plat> plats) {
		this.plats = plats;
	}

	public boolean save() {
		return CategorieCollection.save(this);
	}

	public void addMeat(Plat plat) {
		this.plats.add(plat);
	}

}
