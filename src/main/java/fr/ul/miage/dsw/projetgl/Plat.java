package fr.ul.miage.dsw.projetgl;

import java.util.ArrayList;
import java.util.List;

public class Plat extends Item{

	private String nom;
	private Categorie categorie;
	private List<MatierePremiere> matierePremiere;
	
	
	public Plat(String nom){
		this.nom = nom;
	}
	public Plat() {
		// TODO Auto-generated constructor stub
	}
	public List<Plat> trouverPlat(String nom){
		List<Plat> lp = new ArrayList<Plat>();
		while(this.nom == nom) {
			// insérer dans la liste
		}
		return lp;
	}
	
	
	public List<Plat> trouverPlat(Categorie categorie){
		List<Plat> lp = new ArrayList<Plat>();
		while(this.categorie == categorie) {
			// insérer dans le plat
			
			//lp.add();
		}
		return lp;
	}
	
	public Plat definirPlat(List<MatierePremiere> mp, String nom, Categorie categorie) {
		Plat p = new Plat();
		p.nom = nom;
		p.categorie = categorie;
		p.matierePremiere = mp;
		return p;
	}
	
}
