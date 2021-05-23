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
