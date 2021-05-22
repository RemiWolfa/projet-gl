package fr.ul.miage.dsw.projetgl;

import java.util.ArrayList;
import java.util.List;

public class Plat extends Item{

	
	private static String nom;
	private Categorie categorie;
	private List<MatierePremiere> matierePremiere;
	
	
	public Plat(String nom){
		this.nom = nom;
	}
	public Plat() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Plat> trouverPlat(String nom){
//		List<Plat> lp = new ArrayList<Plat>();
//		while(this.nom == nom) {
//			// ins�rer dans la liste
//		}
		return null;
	}
	
	public List<Plat> trouverPlat(Categorie categorie){
		
//		List<Plat> lp = new ArrayList<Plat>();
//		while(this.categorie == categorie) {
			// ins�rer dans le plat
			
			//lp.add();
	//}
		return null;

		
	}
	
	public Plat definirPlat(List<MatierePremiere> mp, String nom, Categorie categorie) {
//		Plat p = new Plat();
//		p.nom = nom;
//		p.categorie = categorie;
//		p.matierePremiere = mp;
		return null;
	}
	
	
	public static String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	public List<MatierePremiere> getMatierePremiere() {
		return matierePremiere;
	}
	public void setMatierePremiere(List<MatierePremiere> matierePremiere) {
		this.matierePremiere = matierePremiere;
	}
	
	

	
}
