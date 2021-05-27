package fr.ul.miage.dsw.projetgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.ul.miage.dsw.projetgl.database.MatierePremiereCollection;
import fr.ul.miage.dsw.projetgl.database.PlatCollection;

public class Plat extends Item{


	public String nom;
	public Categorie categorie;
	public List<MatierePremiere> matierePremieres;


	public Plat(String nom){
		this();
		this.nom = nom;
	}
	public Plat() {
		this.matierePremieres = new ArrayList<MatierePremiere>();
	}

	//retourne faux si la matiere n'existait pas (a été créée)
	public boolean ajouterMatierePremiere(MatierePremiere matierePremiere) {
		boolean b = !MatierePremiereCollection.exist(matierePremiere);
		MatierePremiereCollection.save(matierePremiere);//si elle n'est pas en bd
		this.matierePremieres.add(matierePremiere);
		return b;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public static Plat definirPlat(List<MatierePremiere> mp, String nom, Categorie categorie) {
		Plat plat = new Plat(nom);
		plat.categorie = categorie;
		plat.matierePremieres = mp;
		return plat;
	}

	public boolean save() {
		return PlatCollection.save(this);
	}

	public static Plat trouverPlat(String nom) {
		return PlatCollection.getPlatByName(nom);
	}
	
	
	public static Plat trouverPlat(Categorie categorie) {
		 return null;
	}

	
	public static Boolean exist(String nom)  {
		return PlatCollection.exist(new Plat(nom));
	}
	
	public boolean testStock(HashMap<MatierePremiere, Integer> fromCommande) {
		for(MatierePremiere mp : this.matierePremieres) {
			int min = 1;
			if(fromCommande.containsKey(mp))
				min+=fromCommande.get(mp);
			System.out.println("mp:"+mp.nom);
			if(MatierePremiereCollection.getStock(mp.nom) < min)
				return false;
		}
		return true;
	}
	
	public boolean equals(Object o) {
		if(!(o instanceof Plat))
			return false;
		
		Plat plat = (Plat)o;
		return plat.nom.equalsIgnoreCase(this.nom);
		
	}

}
