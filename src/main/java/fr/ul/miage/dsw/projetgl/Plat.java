package fr.ul.miage.dsw.projetgl;

import java.util.ArrayList;
import java.util.List;

import fr.ul.miage.dsw.projetgl.database.PlatCollection;

public class Plat extends Item{

	
	public String nom;
	public Categorie categorie;
	public List<MatierePremiere> matierePremiere;
	
	
	public Plat(String nom){
		this();
		this.nom = nom;
	}
	public Plat() {

		this.matierePremiere = new ArrayList<MatierePremiere>();
	}
	
	public Plat trouverPlat(String nom){
		return null;
	}
	
	public static List<Plat> trouverPlat(Categorie categorie){
		return null;	
	}
	
	public Plat definirPlat(List<MatierePremiere> mp, String nom, Categorie categorie) {
		return null;
	}
	
	public boolean save() {
		return PlatCollection.save(this);
	}

	
}
